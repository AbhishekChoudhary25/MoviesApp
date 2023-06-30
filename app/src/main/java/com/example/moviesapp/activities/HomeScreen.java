package com.example.moviesapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.moviesapp.Adapters.HomeScreenViewPagerAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.fragments.FravouriteFragment;
import com.example.moviesapp.fragments.MoviesScreenFragment;
import com.example.moviesapp.fragments.ProfileFragment;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.util.NetworkCallBack;
import com.example.moviesapp.util.NetworkReciever;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity{

    TabLayout tabLayout;

    ViewPager2 viewPager;

    ConnectivityManager connectivityManager;

    Boolean isConnected;

    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;


    NetworkReciever networkChangeReceiver;
    List<Fragment> fragments;

    NetworkCallBack networkCallBack;

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHILD_ACTIVITY) {
            if (resultCode == RESULT_OK) {

                FravouriteFragment fragment = (FravouriteFragment)fragments.get(1);
                fragment.receiveDataFromParent();

            } else if (resultCode == RESULT_CANCELED) {
                System.out.println("Result Cancelled");
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        tabLayout = findViewById(R.id.moviesHomeScreenTabLayout);

        viewPager = findViewById(R.id.homeScreenViewPager);

        getWindow().setStatusBarColor(getResources().getColor(R.color.main_colour_theme));

        fragments = new ArrayList<>();
        fragments.add(new MoviesScreenFragment());
        fragments.add(new FravouriteFragment());
        fragments.add(new ProfileFragment());


        viewPager.setAdapter(new HomeScreenViewPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments));


        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if(position == 0){
                        tab.setText("Movies");
                        tab.setIcon(R.drawable.tv);
                    }
                    else if(position == 1){
                        tab.setText("Favourites");
                        tab.setIcon(R.drawable.fav);
                    }
                    else{
                        tab.setText("Profile");
                        tab.setIcon(R.drawable.profile);
                    }
                }
        ).attach();





    }

    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkCallback();

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterNetworkCallback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void registerNetworkCallback(){


        try {

            connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){

                @Override
                public void onAvailable(@NonNull Network network) {
                    isConnected = true;
                }

                @Override
                public void onLost(@NonNull Network network) {
                    isConnected = false;
                }
            });




        }catch (Exception e){

            isConnected = false;

        }

    }

    private void unregisterNetworkCallback(){

        connectivityManager.unregisterNetworkCallback(new ConnectivityManager.NetworkCallback());

    }

}