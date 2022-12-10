package com.pppb.if_apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.if_apps.databinding.FragmentPertemuanBinding;

public class fragment_pertemuan extends Fragment {

    FragmentPertemuanBinding binding;

    public fragment_pertemuan(){

    }
    public static fragment_pertemuan newInstance(){
        fragment_pertemuan fragment = new fragment_pertemuan();
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentPertemuanBinding.inflate(inflater);
        return binding.getRoot();
    }
}
