package com.imene.afrodite.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.imene.afrodite.R;
import com.imene.afrodite.adapters.HomeAdapter;
import com.imene.afrodite.models.Cadeau;
import com.imene.afrodite.models.Item;
import com.imene.afrodite.retrofit.INodeJS;
import com.imene.afrodite.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CatalogueFragment extends Fragment implements HomeAdapter.ItemListener {

LinearLayout perfumeLayout ;
    private RecyclerView recyclerView;
    private ArrayList<Cadeau> arrayList;
    INodeJS myAPI;
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogue,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        Retrofit retrofit = RetrofitClient.getInstance();

        myAPI = retrofit.create(INodeJS.class);
        Call<List<Cadeau>> call = myAPI.getCadeaux();
        call.enqueue(new Callback<List<Cadeau>>() {
            @Override
            public void onResponse(Call<List<Cadeau>> call, Response<List<Cadeau>> response) {
                List<Cadeau> liste = response.body();
                int i= 0 ;
                for(Cadeau p : liste) {

                    Cadeau cad = new Cadeau();
                    cad.setNom(p.getNom());
                    cad.setDescription(p.getDescription());
                    cad.setNumCadeau(p.getNumCadeau());
                    cad.setNiveau(p.getNiveau());
                    cad.setNombreDePointsCad(p.getNombreDePointsCad());
                    cad.setPrixCad(p.getPrixCad()) ;
                    cad.setDrawable(i);

                    arrayList.add(cad);
                     adapter = new HomeAdapter(getContext(), arrayList);
                    recyclerView.setAdapter(adapter);

                    GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
            i++;
                }

            }

            @Override
            public void onFailure(Call<List<Cadeau>> call, Throwable t) {
                // Log error here since request failed
                Log.d("fail ", "fail");
                Log.e("erreur", t.toString());
            }

        });




        perfumeLayout = view.findViewById(R.id.perfume);
        perfumeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment myFragment = new CadeauDetailFragment();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,  myFragment).commit();

            }
        });
        return view;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //https://www.journaldev.com/13792/android-gridlayoutmanager-example
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Cadeau item) {
        Toast.makeText(getContext(), item.getNom() + " is clicked", Toast.LENGTH_SHORT).show();

    }
}