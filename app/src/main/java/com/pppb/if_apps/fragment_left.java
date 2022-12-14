package com.pppb.if_apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pppb.if_apps.databinding.FragmentLeftBinding;

public class fragment_left extends Fragment {
    FragmentLeftBinding binding;

    public fragment_left() {

    }

    public static fragment_left newInstance() {
        fragment_left fragment = new fragment_left();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLeftBinding.inflate(inflater);
        binding.buttonHome.setOnClickListener(this::onClick);
        binding.buttonFRS.setOnClickListener(this::onClick);
        binding.buttonPertemuan.setOnClickListener(this::onClick);
        binding.buttonPengumuman.setOnClickListener(this::onClick);
        binding.buttonKeluar.setOnClickListener(this::onClick);
        return binding.getRoot();
    }

    private void onClick(View view) {
        if(view == binding.buttonHome){
            Bundle result = new Bundle();
            result.putInt("page",1);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
        else if(view == binding.buttonFRS){
            Bundle result = new Bundle();
            result.putInt("page",2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
        else if(view == binding.buttonPengumuman){
            Bundle result = new Bundle();
            result.putInt("page",3);
            getParentFragmentManager().setFragmentResult("changePage",result);
        }
        else if(view == binding.buttonPertemuan){
            Bundle result = new Bundle();
            result.putInt("page",4);
            getParentFragmentManager().setFragmentResult("changePage",result);
        }
        else if(view == binding.buttonKeluar){
            Bundle result = new Bundle();
            result.putInt("page",0);
            getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }
}
