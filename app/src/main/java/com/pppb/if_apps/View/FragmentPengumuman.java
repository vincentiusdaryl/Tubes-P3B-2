package com.pppb.if_apps.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pppb.if_apps.R;
import com.pppb.if_apps.dbPengumumanAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;

import com.pppb.if_apps.databinding.FragmentLoginBinding;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;
import com.pppb.if_apps.listPengumumanAdapter;
import com.pppb.if_apps.pengumuman;

import java.util.ArrayList;

public class FragmentPengumuman extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentPengumumanBinding binding;
    private listPengumumanAdapter myAdapter;
    private ArrayList<listPengumumanAdapter> arrayList;

    public static FragmentPengumuman newInstance() {
        return new FragmentPengumuman();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentManager = getParentFragmentManager();
        this.binding = FragmentPengumumanBinding.inflate(inflater, container, false);
         myAdapter = new listPengumumanAdapter(getActivity(),arrayList);

        binding.recyclerViewPengumuman.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerViewPengumuman.setLayoutManager(layoutManager);

        return this.binding.getRoot();
    }
}
