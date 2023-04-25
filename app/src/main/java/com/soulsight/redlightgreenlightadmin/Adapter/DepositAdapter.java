package com.soulsight.redlightgreenlightadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.soulsight.redlightgreenlightadmin.Helper.PrograssHandler;
import com.soulsight.redlightgreenlightadmin.Modle.DepositModle;
import com.soulsight.redlightgreenlightadmin.Modle.UserModle;
import com.soulsight.redlightgreenlightadmin.R;
import com.soulsight.redlightgreenlightadmin.databinding.DepositItemBinding;

public class DepositAdapter extends FirebaseRecyclerAdapter<DepositModle,DepositAdapter.Holder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public DepositAdapter(@NonNull FirebaseRecyclerOptions<DepositModle> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull DepositModle model) {

        DepositItemBinding binding=holder.binding;
        Glide.with(context).load(model.getSs()).into(binding.ss);
        binding.time.setText(model.getTime());
        binding.status.setText(model.getStatus());

        binding.reject.setOnClickListener(v -> {
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
            model.setStatus("Rejected");
            databaseReference.child(context.getString(R.string.allDeposit)).child(model.getId()).setValue(null);
            databaseReference.child(context.getString(R.string.myDeposit)).child(model.getUserID()).child(model.getId()).setValue(model);

            notifyDataSetChanged();
        });

        binding.approve.setOnClickListener(v -> {
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

            String amount=binding.amount.getText().toString();
            if (amount.isEmpty())
                binding.amount.setError("enter amount");
            else {
                PrograssHandler.showProgras(context);
                databaseReference.child(context.getString(R.string.user)).child(model.getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PrograssHandler.hidePrograss();
                        UserModle userModle=snapshot.getValue(UserModle.class);
                        if (userModle!=null)
                        {
                            double bal= Double.parseDouble(amount);
                            userModle.setBalance(userModle.getBalance()+bal);
                            model.setStatus("Approve");
                            databaseReference.child(context.getString(R.string.allDeposit)).child(model.getId()).setValue(null);
                            databaseReference.child(context.getString(R.string.myDeposit)).child(model.getUserID()).child(model.getId()).setValue(model);

                            databaseReference.child(context.getString(R.string.user)).child(model.getUserID()).setValue(userModle);
                            loadParnt(databaseReference,model.getUserID(),bal);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


        });


    }

    private void loadParnt(DatabaseReference databaseReference, String id, double reward) {
        databaseReference.child(context.getString(R.string.parent)).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    UserModle userModle=dataSnapshot.getValue(UserModle.class);
                    if (userModle!=null)
                    {
                        rewardParnt(databaseReference,reward,userModle.getId());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void rewardParnt(DatabaseReference databaseReference, double reward, String id) {
        databaseReference.child(context.getString(R.string.user)).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModle userModle=snapshot.getValue(UserModle.class);
                if (userModle!=null)
                {

                    double bal=reward*3;
                    bal=bal/100;

                    userModle.setBalance(userModle.getBalance()+bal);
                    databaseReference.child(context.getString(R.string.user)).child(userModle.getId()).setValue(userModle);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DepositItemBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    public class Holder extends RecyclerView.ViewHolder {
        DepositItemBinding binding;
        public Holder(DepositItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
