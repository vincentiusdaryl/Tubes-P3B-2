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

import com.pppb.if_apps.Helper.SharedPreferenceHelper;
import com.pppb.if_apps.Model.DetailPengumuman;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Presenter.DetailPengumumanPresenter;
import com.pppb.if_apps.databinding.FragmentDetailpengumumanBinding;

public class FragmentDetailPengumuman extends Fragment implements IDetailP {

    private FragmentManager fragmentManager;
    private FragmentDetailpengumumanBinding binding;
    private String token;
    private DetailPengumumanPresenter presenter;

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
        Log.d("testokendisp", SharedPreferenceHelper.getString(getActivity(),Key.TOKEN));

        this.fragmentManager.setFragmentResultListener("GET_TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String token = Key.TOKEN;
                getToken(token);
            }
        });

        this.fragmentManager.setFragmentResultListener(Key.MOVE_TO_DETAILP, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String id = result.getString("pengumuman");
                presenter.getDetailPengumuman(id);
            }
        });

        this.presenter = new DetailPengumumanPresenter(this, getActivity());
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

    @Override
    public void updateDetail(DetailPengumuman detailPengumuman) {
        this.binding.tvJudul.setText(detailPengumuman.getTitle());
        if (detailPengumuman.getTag_name().length > 0) {
            String tag_name = detailPengumuman.getTag_name()[0];
            int len = detailPengumuman.getTag_name().length;
            for (int j = 1; j < len; j++) {
                tag_name += ", " + detailPengumuman.getTag_name()[j];
            }
            binding.tvTags.setText(tag_name);
        }
        this.binding.tvDesc.setText(detailPengumuman.getContent());
    }
}




