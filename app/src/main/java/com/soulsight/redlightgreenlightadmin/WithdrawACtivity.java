package com.soulsight.redlightgreenlightadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.soulsight.redlightgreenlightadmin.Adapter.WithdrawAdapter;
import com.soulsight.redlightgreenlightadmin.Modle.WithdrawModle;
import com.soulsight.redlightgreenlightadmin.databinding.ActivityWithdrawBinding;

public class WithdrawACtivity extends AppCompatActivity {
    ActivityWithdrawBinding binding;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    WithdrawAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWithdrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Query query=databaseReference.child(getString(R.string.allWithdraw)).limitToLast(100);

        FirebaseRecyclerOptions<WithdrawModle> options=new FirebaseRecyclerOptions.Builder<WithdrawModle>().setQuery(query,WithdrawModle.class).build();
        adapter=new WithdrawAdapter(options,this);
        binding.recylerView.setAdapter(adapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}