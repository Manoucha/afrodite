package com.imene.afrodite.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imene.afrodite.R;
import com.imene.afrodite.views.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login (View v)
    {
        Intent iinent= new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(iinent);
    }
}