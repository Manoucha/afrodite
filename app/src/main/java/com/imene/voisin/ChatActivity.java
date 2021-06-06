package com.imene.voisin;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.cometchat.pro.uikit.ui_resources.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.imene.voisin.constants.AppConfig;


public class ChatActivity extends AppCompatActivity {

    private String TAG = "ChatActivity";

    private MaterialButton loginBtn;


    private MaterialCardView superhero2;



    private AppCompatImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        superhero2 = findViewById(R.id.superhero2);
        ivLogo = findViewById(R.id.ivLogo);


        superhero2.setOnClickListener(view -> {
            findViewById(R.id.superhero2_progressbar).setVisibility(View.VISIBLE);
            login("superhero2");
        });


        if(Utils.isDarkMode(this)) {
            ivLogo.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.textColorWhite)));
        }
        else {
            ivLogo.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryTextColor)));
        }
        if (CometChat.getLoggedInUser()!=null)
        {
            startActivity(new Intent(ChatActivity.this,SelectActivity.class));
        }

    }

    private void login(String uid) {
        CometChat.login(uid, AppConfig.AppDetails.AUTH_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                startActivity(new Intent(ChatActivity.this, SelectActivity.class));
                finish();
            }

            @Override
            public void onError(CometChatException e) {
                String str = uid+"_progressbar";
                int id = getResources().getIdentifier(str,"id",getPackageName());
                findViewById(id).setVisibility(View.GONE);
                Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
