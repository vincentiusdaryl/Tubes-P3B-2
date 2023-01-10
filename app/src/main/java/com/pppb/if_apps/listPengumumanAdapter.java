package com.pppb.if_apps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.databinding.FragmentDetailpengumumanBinding;
import com.pppb.if_apps.databinding.FragmentListpengumumanBinding;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;

import java.util.ArrayList;

public class listPengumumanAdapter extends BaseAdapter {
    private Context context;
    FragmentListpengumumanBinding binding;
    FragmentDetailpengumumanBinding binding2;
    private ArrayList<Pengumumann> listPengumuman;
    private Activity activity;
    private FragmentManager fragmentManager;

    public listPengumumanAdapter(Activity activity, FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.listPengumuman = new ArrayList<>();

    }

    public void update(ArrayList<Pengumumann> listPengumuman) {
        this.listPengumuman.addAll(listPengumuman);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listPengumuman.size();
    }

    @Override
    public Object getItem(int i) {
        return listPengumuman.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Pengumumann pem = (Pengumumann) this.getItem(i);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentListpengumumanBinding.inflate(inflater);
        view = binding.getRoot();
        view.setTag(binding);

        binding.tvTitlePengumuman.setText(pem.getTitle());
        if (pem.getTag_name().length > 0) {
            String tag_name = pem.getTag_name()[0];
            int len = pem.getTag_name().length;
            for (int j = 1; j < len; j++) {
                tag_name += ", " + pem.getTag_name()[j];
            }
            binding.tvTags.setText(tag_name);
        }
        return view;
    }

}

