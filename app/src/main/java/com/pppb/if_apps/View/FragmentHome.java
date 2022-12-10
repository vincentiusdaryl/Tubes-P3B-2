package com.pppb.if_apps.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.if_apps.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment implements View.OnClickListener {
    FragmentHomeBinding binding;

    public FragmentHome() {

    }

    public static FragmentHome newInstance() {
        return new FragmentHome();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater);
        return this.binding.getRoot();

    }

    @Override
    public void onClick(View view) {

    }
}
