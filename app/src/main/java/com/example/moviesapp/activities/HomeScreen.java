package com.example.moviesapp.activities;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.Bundle;

import com.example.moviesapp.Adapters.HomeScreenViewPagerAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.fragments.FravouriteFragment;
import com.example.moviesapp.fragments.MoviesScreenFragment;
import com.example.moviesapp.fragments.ProfileFragment;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class HomeScreen extends AppCompatActivity{

    TabLayout tabLayout;

    ViewPager2 viewPager;



    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;

    List<Fragment> fragments;


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHILD_ACTIVITY) {
            if (resultCode == RESULT_OK) {

                FravouriteFragment fragment = (FravouriteFragment)fragments.get(1);

                try{
                    fragment.receiveDataFromParent();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

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
//        registerNetworkCallback();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterNetworkCallback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void changeFragment(Fragment fragment,MovieDetailsPojo movieDetailsPojo){
        Bundle movieDetailsBundle = new Bundle();
        movieDetailsBundle.putSerializable("movie_det",movieDetailsPojo);
        fragment.setArguments(movieDetailsBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.moviesScreen,fragment)
                .addToBackStack("view").commit();
    }

    public void changeInFavourites(){
        FravouriteFragment fragment = (FravouriteFragment)fragments.get(1);
        fragment.receiveDataFromParent();
    }

}