package com.pppb.if_apps.Presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.pppb.if_apps.View.IFrs;
import com.pppb.if_apps.databinding.FragmentFrsBinding;

public class FRSPresenter {
    private IFrs ui;
    private Context context;
    private Gson gson;
    private FragmentFrsBinding binding;

    public FRSPresenter(IFrs ui, Context context, Gson gson, FragmentFrsBinding binding){
        this.ui = (IFrs) ui;
        this.context = context;
        this.gson = gson;
        this.binding = binding;
    }
}


