package com.imene.afrodite.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.imene.afrodite.R;
import com.imene.afrodite.retrofit.INodeJS;
import com.imene.afrodite.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CadeauDetailFragment extends Fragment {

    INodeJS myAPI;

    Button btnexchange;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadeau_detail,container,false);
        btnexchange = view.findViewById(R.id.btn_exchange);
        btnexchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ajouter le produit dans le panier
                Retrofit retrofit = RetrofitClient.getInstance();

                myAPI = retrofit.create(INodeJS.class);

                Call<Void> callApp = myAPI.ajoutercadeaupanier( "13","1");
                callApp.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> callApp, Response<Void> response) {
                        Log.d("cadeau ajouter panier","DONNNNNNNNNE");





                        Fragment myFragment = new PanierFragment();

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,  myFragment).commit();

                    }

                    @Override
                    public void onFailure(Call<Void> callApp, Throwable t) {
                        // Log error here since request failed
                        Log.d("fail ", "fail");
                        Log.e("erreur", t.toString());
                    }

                });







            }
        });
        return view;
    }
}