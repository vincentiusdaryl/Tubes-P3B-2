package com.pppb.if_apps.Presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.pppb.if_apps.View.FragmentPengumuman;
import com.pppb.if_apps.View.IHome;
import com.pppb.if_apps.View.IPengumuman;
import com.pppb.if_apps.databinding.FragmentHomeBinding;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;

public class PengumumanPresenter {
    private IPengumuman ui;
    private Context context;
    private Gson gson;
    private FragmentPengumumanBinding binding;

    public PengumumanPresenter(IPengumuman ui, Context context, FragmentPengumumanBinding binding){
        this.ui = (IPengumuman) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }

}
