package com.imene.afrodite.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imene.afrodite.R;
import com.imene.afrodite.retrofit.INodeJS;
import com.imene.afrodite.retrofit.RetrofitClient;
import com.imene.afrodite.views.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    INodeJS myAPI;
    EditText edt_email,edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = (EditText) findViewById(R.id.username);
        edt_password = (EditText) findViewById(R.id.password);
        //Init the API
        Retrofit retrofit = RetrofitClient.getInstance();

        myAPI = retrofit.create(INodeJS.class);


    }
    public void login (View v)
    {
        loginUser(edt_email.getText().toString(),edt_password.getText().toString());


    }
    public void loginUser(String email, String password)
    {

        Call<String> callIdCourseur = myAPI.loginUser(email,password);
        callIdCourseur.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String idCourseur= response.body();
                JSONObject teste = null;

                Log.d("LOGIN : ","succes");

                Intent iinent= new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(iinent);


            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Log error here since request failed
                Log.d("fail ", "fail");
                Log.e("erreur", t.toString());
            }

        });
    }
}