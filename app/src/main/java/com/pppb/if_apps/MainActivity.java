package com.pppb.if_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.pppb.if_apps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    protected FragmentManager fragmentManager;
    protected fragment_left fragmentLeft;
    protected fragment_home fragmentHome;
    protected fragment_frs fragmentFrs;
    protected fragment_pertemuan fragmentPertemuan;
    protected fragment_pengumuman fragmentPengumuman;
    protected fragment_tambahpengumuman fragmentTambahpengumuman;
    protected fragment_pengumumandosen fragmentPengumumandosen;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.fragmentLeft = fragment_left.newInstance();
        this.fragmentHome = fragment_home.newInstance();
        this.fragmentFrs = fragment_frs.newInstance();
        this.fragmentPertemuan = fragment_pertemuan.newInstance();
        this.fragmentPengumuman = fragment_pengumuman.newInstance();
        this.fragmentTambahpengumuman = fragment_tambahpengumuman.newInstance();
        this.fragmentPengumumandosen = fragment_pengumumandosen.newInstance();
        drawer = binding.drawerLayout;

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(binding.fragmentContainer.getId(), fragmentHome, "main").setReorderingAllowed(true).commit();
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(abdt);
        abdt.syncState();


        this.getSupportFragmentManager().setFragmentResultListener(
                "changePage", this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int page = result.getInt("page");
                        changePage(page);
                    }
                }
        );
    }

    public void changePage(int page) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(page==1){
            ft.replace(binding.fragmentContainer.getId(),this.fragmentHome).addToBackStack(null).setReorderingAllowed(true);
        }
        else if(page==2){
            ft.replace(binding.fragmentContainer.getId(),this.fragmentFrs).addToBackStack(null).setReorderingAllowed(true);
        }
        else if (page==3){
            ft.replace(binding.fragmentContainer.getId(),this.fragmentPengumumandosen).addToBackStack(null).setReorderingAllowed(true);
        }
        else if (page==4){
            ft.replace(binding.fragmentContainer.getId(),this.fragmentPertemuan).addToBackStack(null).setReorderingAllowed(true);
        }
        else if (page==5){
            ft.replace(binding.fragmentContainer.getId(),this.fragmentTambahpengumuman).addToBackStack(null).setReorderingAllowed(true);
        }
        else if (page==6){
            ft.replace(binding.fragmentContainer.getId(),this.fragmentTambahpengumuman).addToBackStack(null).setReorderingAllowed(true);
        }
        else if(page==0){
            this.moveTaskToBack(true);
            this.finish();
        }
        ft.commit();
        drawer.closeDrawers();
    }
}


