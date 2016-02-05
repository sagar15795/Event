package com.dukhabar.youthutsav;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_frame, WebViewGen.newInstance("http://delhifest.a2nkart.com/index.html"));
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        switch (id){

            case R.id.nav_Home:
                ft.replace(R.id.content_frame, WebViewGen.newInstance("http://delhifest.a2nkart.com/index.html"));
                ft.commit();
                break;
            case R.id.nav_about_fest:
                ft.replace(R.id.content_frame, WebViewGen.newInstance("http://delhifest.a2nkart.com/about_delhi_fest.html"));
                ft.commit();
                break;
            case R.id.nav_activity:
                ft.replace(R.id.content_frame, WebViewGen.newInstance("http://delhifest.a2nkart.com/activities_20_feb.html"));
                ft.commit();
                break;
            case R.id.nav_gallery:
                ft.replace(R.id.content_frame, WebViewGen.newInstance("http://delhifest.a2nkart.com/gallery.html"));
                ft.commit();
                break;
            case R.id.nav_submit:
                ft.replace(R.id.content_frame, WebViewGen.newInstance("http://delhifest.a2nkart.com/submit_your_activity.html"));
                ft.commit();
                break;
            case R.id.nav_event:
                ft.replace(R.id.content_frame, new ANshu());
                ft.commit();
                break;
            case R.id.nav_android:
                ft.replace(R.id.content_frame, new Android_Team_viewpager());
                ft.commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
