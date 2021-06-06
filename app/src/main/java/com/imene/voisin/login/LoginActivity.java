package com.imene.voisin.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.imene.voisin.R;
import com.imene.voisin.views.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edt_email,edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = (EditText) findViewById(R.id.username);
        edt_password = (EditText) findViewById(R.id.password);
        //Init the API





    }
    public void login (View v)
    {
        loginUser(edt_email.getText().toString(),edt_password.getText().toString());


    }
    public void loginUser(String email, String password)
    {



                Intent iinent= new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(iinent);





    }
}