package com.pppb.if_apps;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.databinding.SplashscreenBinding;

public class fragment_ss extends Fragment{

    private SplashscreenBinding binding;
    private FragmentManager fragmentManager;
    private UIHandler handler;
    public int counter = 0;
    private String dots = ".";
    private TextView textView;

    public fragment_ss(){

    }
    public static fragment_ss newInstance(){
        fragment_ss fragment= new fragment_ss();
        return fragment;
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = SplashscreenBinding.inflate(inflater);
        return binding.getRoot();
    }

    // MENAMPILKAN COUNTER KE LAYAR
    public void showCounting (int counter) {
        if (counter % 4 == 0) {
            this.dots += " .";
            if (this.dots.equals(". . . . . .")) {
                this.dots = ".";
            }

        }
        this.counter = counter;
    }

    // METHOD UNTUK GANTI HALAMAN DGN KIRIM BUNDLE
    public void changePage (int page) {
        Bundle result = new Bundle();
        result.putInt("page", page);
        this.fragmentManager.setFragmentResult("CHANGE_PAGE", result);
    }
}