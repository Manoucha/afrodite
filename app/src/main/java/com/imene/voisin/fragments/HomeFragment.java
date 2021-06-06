 package com.imene.voisin.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.imene.voisin.R;
import com.imene.voisin.constants.AppConfig;


 public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    CardView card;
    Button buttonfiltre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        card = view.findViewById(R.id.cardtoclick);


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailFragment nextFrag= new DetailFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        buttonfiltre= view.findViewById(R.id.buttonfiltre);
        buttonfiltre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CometChat.login("superhero2", AppConfig.AppDetails.AUTH_KEY, new CometChat.CallbackListener<User>() {
                    @Override
                    public void onSuccess(User user) {


                        Intent intent = new Intent(getActivity(), CometChatUI.class);
                        getActivity().startActivity(intent);
                    }

                    @Override
                    public void onError(CometChatException e) {

                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        return view;
    }


 }