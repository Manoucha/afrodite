package com.imene.afrodite.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.imene.afrodite.R;
import com.imene.afrodite.adapters.CadeauAdapter;
import com.imene.afrodite.models.Cadeau;
import com.imene.afrodite.models.Client;
import com.imene.afrodite.models.myApp;
import com.imene.afrodite.retrofit.INodeJS;
import com.imene.afrodite.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PanierFragment extends Fragment {

    ArrayList<Cadeau> contacts;
    INodeJS myAPI;
    List<Cadeau> liste;
    List<Cadeau> cadx=new ArrayList<>();
    RecyclerView rvContacts;
    Button btngetit,btndiscover ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_panier,container,false);
        Client user  = ((myApp) getActivity().getApplication()).getUser();
        TextView pointClients = view.findViewById(R.id.pointClientTv);
        ImageView membershipImage = (ImageView)view.findViewById(R.id.membership);
        pointClients.setText("you have "+user.getNbPoint()+" points");
        if(user.getNbPoint()>500)
        {
        membershipImage.setImageDrawable(getResources().getDrawable(R.drawable.golden));
        }else if(user.getNbPoint()<500)
        {
            membershipImage.setImageDrawable(getResources().getDrawable(R.drawable.silver));

        }
        rvContacts  = (RecyclerView) view.findViewById(R.id.rvContacts);
        btngetit = view.findViewById(R.id.btngetitnow);
        btndiscover = view.findViewById(R.id.shop);
        btngetit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = RetrofitClient.getInstance();

                myAPI = retrofit.create(INodeJS.class);


                Call<Void> callApp = myAPI.echanger("13","1",1,100);
                callApp.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> callApp, Response<Void> response) {
                        Log.d("echange effectuer : ","DONNNNNNNNNE");

                        new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Done ! ")
                                .setContentText("Your can get you product from our store whenever you want ! ")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.cancel();
                                        Fragment myFragment = new HomeFragment();

                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,  myFragment).commit();

                                    }
                                }).show();
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
        // Lookup the recyclerview in activity layout

        Retrofit retrofit = RetrofitClient.getInstance();

        myAPI = retrofit.create(INodeJS.class);


        Call<List<Cadeau>> call = myAPI.getCadeauDansLePanier("13");

        call.enqueue(new Callback<List<Cadeau>>() {
            @Override
            public void onResponse(Call<List<Cadeau>> call, Response<List<Cadeau>> response) {
                liste = response.body();
                if(liste.size()!=0)
                {
                    btngetit.setVisibility(View.VISIBLE);
                    btndiscover.setVisibility(View.INVISIBLE);
                }
                for(Cadeau p : liste)
                {

                    Cadeau cad = new Cadeau();
                    cad.setNom(p.getNom());
                    cad.setDescription(p.getDescription());
                    cad.setNombreDePointsCad(p.getNombreDePointsCad());
                    cad.setNiveau(p.getNiveau());
                    cad.setQuantite(p.getQuantite());
                    cad.setNumCadeau(p.getNumCadeau());
                    cad.setPrixCad(p.getPrixCad());

                    Log.d("nom cadeau +",""+cad.getNom());
                    Log.d("desc cadeau +",""+cad.getDescription());

                    Log.d("nb points cadeau +",""+cad.getNombreDePointsCad());

                    Log.d("prix  cadeau +",""+p.getPrixCad());

                    cadx.add(cad);
                    Log.d("taille de possts ",""+cadx.size());


                    // Initialize contacts
                 //   contacts = Cadeau.createContactsList(20);
                    // Create adapter passing in the sample user data
                    CadeauAdapter adapter = new CadeauAdapter(cadx);
                    // Attach the adapter to the recyclerview to populate items
                    rvContacts.setAdapter(adapter);
                    // Set layout manager to position the items
                    rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));

                }
            }
            @Override
            public void onFailure(Call<List<Cadeau>> call, Throwable t) {
                // Log error here since request failed
                Log.d("fail ","fail");
                Log.e("erreur",t.toString());
            }

        });

            if(cadx.size()==0)

             {
                btngetit.setVisibility(View.INVISIBLE);
                btndiscover.setVisibility(View.VISIBLE);

            }
        return view;    }
}