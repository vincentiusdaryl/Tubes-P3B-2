package com.pppb.if_apps.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private SharedPreferenceHelper spHelper;

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

        this.adapter = new listPengumumanAdapter(getActivity(), this.getParentFragmentManager());
        this.binding.lwPengumuman.setAdapter(adapter);

        this.fragmentManager.setFragmentResultListener("GET_TOKEN", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String token = Key.TOKEN;
                getToken(token);
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

    private void getToken (String token){
        if(token!=null){
            this.token = Key.TOKEN;
            Log.d("token pengumuman", this.token);
        }else{
            Log.e("NO TOKEN","");
        }
    }

    @Override
    public void getPengumumanList(GetPengumuman res) {
        int len = res.pengumuman.size();
        ArrayList<Pengumumann> list = new ArrayList<>();
        if(len>0){
            for(int i = 0; i<len; i++){
                String id = res.pengumuman.get(i).id;
                String title = res.pengumuman.get(i).title;
                String updated_at = res.pengumuman.get(i).updated_at;
                String created_at = res.pengumuman.get(i).created_at;
                String author = res.pengumuman.get(i).author;
                String[] tags = res.pengumuman.get(i).tags;
                String[] tag_id = res.pengumuman.get(i).tag_id;
//                list.add(new GetPengumuman(id, title, updated_at, created_at, author, tags, tag_id));
                list.add(new Pengumumann(id, title, updated_at, created_at, author, tags, tag_id));
            }
            this.adapter.update(list);
        }
    }
}




