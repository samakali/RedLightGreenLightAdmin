package com.soulsight.redlightgreenlightadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.soulsight.redlightgreenlightadmin.Helper.PrograssHandler;
import com.soulsight.redlightgreenlightadmin.Modle.UserModle;
import com.soulsight.redlightgreenlightadmin.Modle.WithdrawModle;
import com.soulsight.redlightgreenlightadmin.R;
import com.soulsight.redlightgreenlightadmin.databinding.WithdrawItemBinding;

public class WithdrawAdapter extends FirebaseRecyclerAdapter<WithdrawModle, WithdrawAdapter.Holder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public WithdrawAdapter(@NonNull FirebaseRecyclerOptions<WithdrawModle> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull WithdrawModle model) {

        WithdrawItemBinding binding=holder.binding;

        binding.amount.setText(model.getAmount());
        binding.acciount.setText(model.getAccountInfo());
        binding.time.setText(model.getTime());
        binding.status.setText(model.getStatus());

        binding.approve.setOnClickListener(v -> {
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
            model.setStatus("approve");
            databaseReference.child(context.getString(R.string.allWithdraw)).child(model.getId()).setValue(null);
            databaseReference.child(context.getString(R.string.myWithdraw)).child(model.getUserID()).child(model.getId()).setValue(model);

            notifyDataSetChanged();
        });

        binding.reject.setOnClickListener(v -> {
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

            PrograssHandler.showProgras(context);
            databaseReference.child(context.getString(R.string.user)).child(model.getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    PrograssHandler.hidePrograss();
                    UserModle userModle=snapshot.getValue(UserModle.class);
                    if (userModle!=null)
                    {
                        double bal= Double.parseDouble(model.getAmount());
                        userModle.setBalance(userModle.getBalance()+bal);
                        model.setStatus("rejected");
                        databaseReference.child(context.getString(R.string.allWithdraw)).child(model.getId()).setValue(null);
                        databaseReference.child(context.getString(R.string.myWithdraw)).child(model.getUserID()).child(model.getId()).setValue(model);

                        databaseReference.child(context.getString(R.string.user)).child(model.getUserID()).setValue(userModle);
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        });

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(WithdrawItemBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    public class Holder extends RecyclerView.ViewHolder {
        WithdrawItemBinding binding;
        public Holder(WithdrawItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
