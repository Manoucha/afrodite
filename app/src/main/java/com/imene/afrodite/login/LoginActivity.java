package com.imene.afrodite.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imene.afrodite.R;
import com.imene.afrodite.fragments.HomeFragment;
import com.imene.afrodite.models.Client;
import com.imene.afrodite.models.myApp;
import com.imene.afrodite.retrofit.INodeJS;
import com.imene.afrodite.retrofit.RetrofitClient;
import com.imene.afrodite.views.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
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

        Call<Client> callIdCourseur = myAPI.loginUser(email,password);
        callIdCourseur.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                Client cl= response.body();

                Log.d("LOGIN : ","succes");
                Client client = new Client(cl.getCodeClient(),cl.getPrenom(),cl.getNbPoint(),cl.getMemberShip_id());
                Log.d("nb points : ",""+client.getNbPoint());
               ((myApp) getApplication()).setUser(client);

                Intent iinent= new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(iinent);


            }


            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                // Log error here since request failed
                Log.d("fail ", "fail");
                Log.e("erreur", t.toString());

                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("BAD CREDENTIALS")
                        .setContentText("please verify your username or password ! ")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();

                            }
                        }).show();
            }

        });
    }
}