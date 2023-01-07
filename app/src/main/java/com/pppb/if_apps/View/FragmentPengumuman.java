package com.pppb.if_apps.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.GetPengumuman;
import com.pppb.if_apps.Presenter.PengumumanPresenter;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;
import com.pppb.if_apps.listPengumumanAdapter;

import java.util.ArrayList;

public class FragmentPengumuman extends Fragment implements IPengumuman {
    private FragmentManager fragmentManager;
    private FragmentPengumumanBinding binding;
    private listPengumumanAdapter adapter;
    private String token;
    private PengumumanPresenter pengumuman;

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
        return view;
    }

    @Override
    public void getPengumumanList(GetPengumuman res) {
        int len = res.pengumuman.size();
        ArrayList<GetPengumuman> list = new ArrayList();
        if(len>0){
            for(int i = 0; i<len; i++){
                String id = res.pengumuman.get(i).id;
                String title = res.pengumuman.get(i).id;
                String updated_at = res.pengumuman.get(i).updated_at;
                String created_at = res.pengumuman.get(i).created_at;
                String author = res.pengumuman.get(i).created_at;
                String[] tags = res.pengumuman.get(i).tags;
                String[] tag_id = res.pengumuman.get(i).tag_id;
//                list.add(new GetPengumuman(id, title, updated_at, created_at, author, tags, tag_id));
                list.add(new GetPengumuman());
            }
        }
    }
}




