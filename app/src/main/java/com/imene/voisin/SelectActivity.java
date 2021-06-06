package com.imene.voisin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.uikit.ui_components.calls.call_manager.listener.CometChatCallListener;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


public class SelectActivity extends AppCompatActivity {



    private MaterialButton logout;

    private MaterialButton unifiedLaunch;


    String receiverTypeStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);













        logout = findViewById(R.id.logout);
        unifiedLaunch = findViewById(R.id.directLaunch);

        unifiedLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this, CometChatUI.class));
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        });


    }

    private void initiateCall(String type) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectActivity.this);
        View vw = LayoutInflater.from(SelectActivity.this).inflate(R.layout.user_id_layout,null);

        ImageView closeIcon = vw.findViewById(R.id.close_dialog);
        TextView title = vw.findViewById(R.id.title);
        title.setText("Make "+type+" call");
        EditText userID = vw.findViewById(R.id.userID);
        MaterialButton submit = vw.findViewById(R.id.submit);
        RadioGroup receiverType = vw.findViewById(R.id.receiver_type);
        RadioButton typeUser = vw.findViewById(R.id.type_user);
        RadioButton typeGroup = vw.findViewById(R.id.type_group);

        receiverType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (typeUser.isChecked())
                    receiverTypeStr = CometChatConstants.RECEIVER_TYPE_USER;
                else if (typeGroup.isChecked())
                    receiverTypeStr = CometChatConstants.RECEIVER_TYPE_GROUP;
            }
        });

        alertDialog.setView(vw);
        Dialog dialog = alertDialog.create();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userID.getText().toString().trim().isEmpty())
                    userID.setError("Fill ths field");
                else
                    CometChatCallListener.makeCall(SelectActivity.this, userID.getText().toString(), receiverTypeStr,type);
                dialog.dismiss();
            }
        });
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void logoutUser(View view) {
        CometChat.logout(new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String s) {
                startActivity(new Intent(SelectActivity.this, ChatActivity.class));
            }

            @Override
            public void onError(CometChatException e) {
                Snackbar.make(view,"Login Error:"+e.getCode(),Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CometChat.getLoggedInUser()==null)
        {
            startActivity(new Intent(SelectActivity.this, ChatActivity.class));
        }
    }

}
