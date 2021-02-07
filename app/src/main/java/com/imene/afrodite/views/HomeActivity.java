package com.imene.afrodite.views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.imene.afrodite.R;
import com.imene.afrodite.fragments.CatalogueFragment;
import com.imene.afrodite.fragments.HomeFragment;
import com.imene.afrodite.fragments.PanierFragment;

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
                            selectedFragment = new CatalogueFragment() ;
                            break;
                        case R.id.nav_location:
                            selectedFragment = new PanierFragment() ;
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