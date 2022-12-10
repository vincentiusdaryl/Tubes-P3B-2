package com.pppb.if_apps.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private FragmentHome fragmentHome;
    private FragmentTransaction fragmentTransaction;
    private Fragment[] fragments;
    private int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        super.onCreate(savedInstanceState);

        // Fragment Initiation
        this.fragmentHome = FragmentHome.newInstance();

        // Gathering all fragments in one array
        this.fragments = new Fragment[]{
            this.fragmentHome
        };

        // Fragment Mover
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();

        // Add FragmentHome as the first fragment to show
        this.fragmentTransaction.add(this.binding.fragmentContainer.getId(), this.fragmentHome).addToBackStack(null);
        this.currentFragment = Key.FRAGMENT_HOME;

        // Add drawer layout

        // Change page listener
        this.getSupportFragmentManager().setFragmentResultListener(
                Key.CHANGE_PAGE, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int page = result.getInt(Key.CHANGE_PAGE_NUMBER);
                        changePage(page);
                    }
                }
        );
    }

    private void changePage (int page) {
        if (page == Key.PAGE_EXIT){
            this.exitApplication();
        }
        else {
            this.fragmentTransaction = this.fragmentManager.beginTransaction();
            this.fragmentTransaction.hide(this.fragments[this.currentFragment]);
            if (this.fragments[page].isAdded()){
                this.fragmentTransaction.show(this.fragments[page]);
            }
            else {
                this.fragmentTransaction.add(this.binding.fragmentContainer.getId(), this.fragments[page]).addToBackStack(null);
            }
            this.fragmentTransaction.commit();
            this.currentFragment = page;
        }
    }

    private void exitApplication () {
        this.moveTaskToBack(true);
        this.finish();
    }
}


