package com.imene.voisin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.imene.voisin.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SendMsgFragment extends Fragment {


    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_send_msg, container, false);


        btn = view.findViewById(R.id.btn_msg_sent);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Thank you !")
                        .setContentText("Your message has been sent to Anna ! ")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                HomeFragment nextFrag= new HomeFragment();
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                                        .addToBackStack(null)
                                        .commit();
                            }
                        })
                        .show();
            }
        });
        return  view;
    }
}