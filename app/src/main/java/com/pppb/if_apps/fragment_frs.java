package com.pppb.if_apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.if_apps.databinding.FragmentFrsBinding;

public class fragment_frs extends Fragment {
    FragmentFrsBinding binding;

    public fragment_frs(){

    }
    public static fragment_frs newInstance(){
        fragment_frs fragment = new fragment_frs();
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentFrsBinding.inflate(inflater);
        return binding.getRoot();
    }

}

