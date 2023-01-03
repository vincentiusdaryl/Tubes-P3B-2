package com.pppb.if_apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.if_apps.databinding.FragmentPengumumanBinding;
import com.pppb.if_apps.databinding.FragmentPertemuanBinding;

public class fragment_pengumuman extends Fragment {
    FragmentPengumumanBinding binding;

    public fragment_pengumuman(){

    }
    public static fragment_pengumuman newInstance(){
        fragment_pengumuman fragment = new fragment_pengumuman();
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentPengumumanBinding.inflate(inflater);
        return binding.getRoot();
    }

    public static fragment_pengumuman newInstance(String title) {
        fragment_pengumuman fragment = new fragment_pengumuman();
        Bundle args = new Bundle();
        args.putString("pengumuman", title);
        fragment.setArguments(args);
        return fragment;
    }
}
