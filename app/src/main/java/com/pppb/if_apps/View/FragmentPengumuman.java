package com.pppb.if_apps.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.pppb.if_apps.Helper.SharedPreferenceHelper;
import com.pppb.if_apps.Model.GetPengumuman;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.Presenter.PengumumanPresenter;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;
import com.pppb.if_apps.listPengumumanAdapter;

import java.util.ArrayList;

public class FragmentPengumuman extends Fragment implements IPengumuman {
    private FragmentManager fragmentManager;
    private FragmentPengumumanBinding binding;
    private listPengumumanAdapter adapter;
    private String token;
    private PengumumanPresenter presenter;

    public FragmentPengumuman() {
    }

    public static FragmentPengumuman newInstance() {
        return new FragmentPengumuman();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.fragmentManager = getParentFragmentManager();
        this.binding = FragmentPengumumanBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        Log.d("testokendisp",SharedPreferenceHelper.getString(getActivity(),"token"));
        this.adapter = new listPengumumanAdapter(getActivity(), this.getParentFragmentManager());
        this.binding.lwPengumuman.setAdapter(adapter);

        this.fragmentManager.setFragmentResultListener("GET_TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String token = SharedPreferenceHelper.getString(getActivity(),"token");
                getToken();
            }
        });

        this.fragmentManager.setFragmentResultListener("TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                presenter.getPengumuman();
            }
        });

        this.presenter = new PengumumanPresenter(this, getActivity());
        this.presenter.getPengumuman();
        return view;
    }

    private void getToken() {
        if (SharedPreferenceHelper.getString(getActivity(),"token") != null) {
            this.token = SharedPreferenceHelper.getString(getActivity(),"token");
            Log.d("token pengumuman", this.token);
        } else {
            Log.e("NO TOKEN", "");
        }
    }

    @Override
    public void getPengumumanList(ArrayList<Pengumumann> list_pengumuman) {
        adapter.update(list_pengumuman);
        ListView listView = binding.lwPengumuman;
        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
//        presenter.clickPengumuman();
    }
}





