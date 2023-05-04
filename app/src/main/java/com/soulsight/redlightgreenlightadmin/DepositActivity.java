package com.soulsight.redlightgreenlightadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.soulsight.redlightgreenlightadmin.Adapter.DepositAdapter;
import com.soulsight.redlightgreenlightadmin.Modle.DepositModle;
import com.soulsight.redlightgreenlightadmin.databinding.ActivityDepositBinding;

public class DepositActivity extends AppCompatActivity {
    ActivityDepositBinding bindingl;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    DepositAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingl=ActivityDepositBinding.inflate(getLayoutInflater());
        setContentView(bindingl.getRoot());

        Query query=databaseReference.child(getString(R.string.allDeposit)).limitToLast(100);

        FirebaseRecyclerOptions<DepositModle> options=new FirebaseRecyclerOptions.Builder<DepositModle>().setQuery(query, DepositModle.class).build();
        adapter=new DepositAdapter(options,this);
        bindingl.recylerView.setAdapter(adapter);

    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}