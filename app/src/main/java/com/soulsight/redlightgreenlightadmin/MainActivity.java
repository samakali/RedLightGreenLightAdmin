package com.soulsight.redlightgreenlightadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void deposit(View view) {
        startActivity(new Intent(getApplicationContext(),DepositActivity.class));
    }

    public void withdraw(View view) {
        startActivity(new Intent(getApplicationContext(),WithdrawACtivity.class));
    }
}