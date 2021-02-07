package com.imene.afrodite.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.imene.afrodite.R;

public class CatalogueFragment extends Fragment {

LinearLayout perfumeLayout ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogue,container,false);

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
}