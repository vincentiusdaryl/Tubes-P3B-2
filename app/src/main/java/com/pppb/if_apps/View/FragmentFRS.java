package com.pppb.if_apps.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.pppb.if_apps.Helper.SharedPreferenceHelper;
import com.pppb.if_apps.Model.FRS;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.Presenter.FRSPresenter;
import com.pppb.if_apps.Presenter.PengumumanPresenter;
import com.pppb.if_apps.databinding.FragmentFrsBinding;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;
import com.pppb.if_apps.listFRSSemesterAdapter;

import java.util.ArrayList;

public class FragmentFRS extends Fragment implements IFrs {

    private FragmentManager fragmentManager;
    private FragmentFrsBinding binding;
    private listFRSSemesterAdapter adapter;
    private String token;
    private FRSPresenter presenter;


    public static FragmentFRS newInstance(){
        return new FragmentFRS();
    }

    public FragmentFRS(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentManager = getParentFragmentManager();
        this.binding = FragmentFrsBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        Log.d("testokendifrssp", SharedPreferenceHelper.getString(getActivity(), Key.TOKEN));
        this.adapter = new listFRSSemesterAdapter(getActivity(), this.getParentFragmentManager());
        this.binding.lwSemester.setAdapter(adapter);

        this.fragmentManager.setFragmentResultListener("GET_TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String token = Key.TOKEN;
                getToken();
            }
        });

        this.fragmentManager.setFragmentResultListener("TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                presenter.getSemester();
            }
        });

        this.presenter = new FRSPresenter(this, getActivity());
        this.presenter.getSemester();
        return view;


    }

    @Override
    public void getSemesterList(ArrayList<FRS> list_semester) {
        adapter.update(list_semester);
        ListView listView = binding.lwSemester;
        listView.setAdapter(adapter);

    }

    private void getToken() {
        if (token != null) {
            this.token = Key.TOKEN;
            Log.d("token pengumuman", this.token);
        } else {
            Log.e("NO TOKEN", "");
        }
    }

}
