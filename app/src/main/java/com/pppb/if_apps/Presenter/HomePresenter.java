package com.pppb.if_apps.Presenter;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.pppb.if_apps.View.FragmentHome;
import com.pppb.if_apps.View.IHome;
import com.pppb.if_apps.databinding.FragmentHomeBinding;

public class HomePresenter {
    private IHome ui;
    private Context context;
    private Gson gson;
    private FragmentHomeBinding binding;

    public HomePresenter(IHome ui, Context context, FragmentHomeBinding binding){
        this.ui = (IHome) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }


}
