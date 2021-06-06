package com.imene.voisin.fragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.imene.voisin.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddFragment extends Fragment  {

    Button btnpublier;
    ImageButton imagebtn;
    private final int GALLERY_REQUEST_CODE=24;
    private final int STORAGE_PERMISSION_CODE = 23;
    TextView tv,adresse ;
    Boolean test ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] types = { "    Type","    Proposer de l'aide", "    Demander de l'aide"};
        String[] categoris = { "    Catégories","    Bricolage", "    Jardin","    Cuisine","    Electroniques"};


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        test  =false;
        btnpublier = view.findViewById(R.id.publier);
        imagebtn = view.findViewById(R.id.imagebtn);
        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        Spinner cat = (Spinner) view.findViewById(R.id.categorie);
        tv = view.findViewById(R.id.adr);
        adresse = view.findViewById(R.id.adresse);

        ArrayAdapter  adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_item_text, types);
        spin.setAdapter(adapter);
        ArrayAdapter  adapter1 = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_item_text, categoris);
        cat.setAdapter(adapter1);

        //Creating the ArrayAdapter instance having the country list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(adapter);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adresse.setText("1 Rue Aristote, 72000 Le Mans");
                }
            });



        btnpublier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Confirmer votre publication")
                        .setContentText("Vous êtes sur de vouloir publier cette annonce ? ")
                        .setConfirmText("OUI")
                        .setCancelText("NON")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {




                                sDialog.dismissWithAnimation();
                                HomeFragment nextFrag= new HomeFragment();
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                                        .addToBackStack(null)
                                        .commit();


                                ;
                            }
                        })


                        .show();




            }
        });
        // Autorisation d'accès au stockage externe
        ActivityCompat.requestPermissions(getActivity(),new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);


        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test = true;
                //Créer un Intent avec une action  ACTION_PICK
                Intent intent=new Intent(Intent.ACTION_PICK);
                //Définissez le type comme image/*.
                //Cela garantit que seuls les composants de type image sont sélectionnés
                intent.setType("image/*");
                //Nous passons un tableau supplémentaire avec les types MIME acceptés.
                //Cela garantira que seuls les composants avec ces types MIME sont ciblés.
                String[] mimeTypes = {"image/jpeg", "image/png","image/jpg"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                //Lancer l'Intent
                startActivityForResult(intent,GALLERY_REQUEST_CODE);

                Drawable d = getResources().getDrawable(R.drawable.done);
                imagebtn.setBackground(d);



            }

        });

        
        return view;
    }
}