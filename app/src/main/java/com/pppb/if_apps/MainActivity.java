package com.pppb.if_apps;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.R;
import com.pppb.if_apps.View.FragmentFRS;
import com.pppb.if_apps.View.FragmentHome;
import com.pppb.if_apps.View.FragmentLogin;
import com.pppb.if_apps.View.FragmentPengumuman;
import com.pppb.if_apps.View.FragmentPertemuan;
import com.pppb.if_apps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private FragmentLogin fragmentLogin;
    private FragmentHome fragmentHome;
    private FragmentPengumuman fragmentPengumuman;
    private FragmentTransaction fragmentTransaction;
    private FragmentPertemuan fragmentPertemuan;
    private FragmentFRS fragmentFRS;
    private Fragment[] fragments;
    private int currentFragment;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        // Fragment Initiation
        this.fragmentHome = FragmentHome.newInstance();
        this.fragmentLogin = FragmentLogin.newInstance();
        this.fragmentPengumuman = FragmentPengumuman.newInstance();
        this.fragmentPertemuan = FragmentPertemuan.newInstance();
        this.fragmentFRS = FragmentFRS.newInstance();

        // Gathering all fragments in one array
        this.fragments = new Fragment[]{
                this.fragmentLogin,
                this.fragmentHome,
                this.fragmentPengumuman,
                this.fragmentPertemuan,
                this.fragmentFRS
        };

        // Fragment Mover
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();

        // Add FragmentHome as the first fragment to show
        this.fragmentTransaction.add(R.id.fragmentContainer, this.fragmentLogin).addToBackStack(null).commit();
        this.currentFragment = Key.FRAGMENT_LOGIN;

        // Add drawer layout
//        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar, R.string.open, R.string.close);
//        this.drawer.addDrawerListener(abdt);
//        abdt.syncState();

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


