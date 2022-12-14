package com.pppb.if_apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pppb.if_apps.databinding.FragmentPengumumandosenBinding;

public class fragment_pengumumandosen extends Fragment {
    FragmentPengumumandosenBinding binding;
    FloatingActionButton floatingActionButton;
    Button buttonAdd;

    public fragment_pengumumandosen(){

    }
    public static fragment_pengumumandosen newInstance(){
        fragment_pengumumandosen fragment = new fragment_pengumumandosen();
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentPengumumandosenBinding.inflate(inflater);

//        floatingActionButton = binding.buttonAdd;
        buttonAdd = binding.buttonAdd;
//        floatingActionButton.setVisibility(View.GONE);
        binding.buttonAdd.setOnClickListener(this::onClick);
        return binding.getRoot();
    }

    private void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page",5);
        getParentFragmentManager().setFragmentResult("changePage",result);
    }

    public static fragment_pengumumandosen newInstance(String title) {
        fragment_pengumumandosen fragment = new fragment_pengumumandosen();
        Bundle args = new Bundle();
        args.putString("pengumumandosen", title);
        fragment.setArguments(args);
        return fragment;
    }
}
