package com.imene.voisin.views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.imene.voisin.R;
import com.imene.voisin.constants.AppConfig;
import com.imene.voisin.fragments.AddFragment;
import com.imene.voisin.fragments.HomeFragment;
import com.imene.voisin.fragments.profUserFragment;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_home);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("                                  HOME");

        //  getSupportActionBar().setTitle(user.getNom());
        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.chat:



                        CometChat.login("superhero2", AppConfig.AppDetails.AUTH_KEY, new CometChat.CallbackListener<User>() {
                            @Override
                            public void onSuccess(User user) {


                                Intent intent = new Intent(HomeActivity.this, CometChatUI.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onError(CometChatException e) {

                                Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });



                        break;



                }
                return  true;

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null ;
                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment() ;
                            break;
                        case R.id.nav_dashboard:
                            selectedFragment = new AddFragment();
                            break;
                        case R.id.nav_location:
                            selectedFragment = new profUserFragment() ;
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectedFragment).commit();
                    return  true;
                }
            };
    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

}