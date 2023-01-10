package com.pppb.if_apps.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.Presenter.PengumumanPresenter;
import com.pppb.if_apps.databinding.FragmentDetailpengumumanBinding;
import com.pppb.if_apps.listPengumumanAdapter;

import java.util.ArrayList;

public class FragmentDetailPengumuman extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentDetailpengumumanBinding binding;
    private listPengumumanAdapter adapter;
    private String token;
    private PengumumanPresenter presenter;

    public FragmentDetailPengumuman() {
    }

    public static FragmentDetailPengumuman newInstance() {
        return new FragmentDetailPengumuman();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.fragmentManager = getParentFragmentManager();
        this.binding = FragmentDetailpengumumanBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        this.fragmentManager.setFragmentResultListener("GET_TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String token = Key.TOKEN;
                getToken(token);
            }
        });

        return view;
    }

    private void getToken(String token) {
        if (token != null) {
            this.token = Key.TOKEN;
            Log.d("token pengumuman", this.token);
        } else {
            Log.e("NO TOKEN", "");
        }
    }

    public void detailPengumuman(ArrayList<Pengumumann> list_pengumuman){

    }

}




