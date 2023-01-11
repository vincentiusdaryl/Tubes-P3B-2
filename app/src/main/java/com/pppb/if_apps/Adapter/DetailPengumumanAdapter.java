package com.pppb.if_apps.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.DetailPengumuman;
import com.pppb.if_apps.databinding.FragmentDetailpengumumanBinding;

import java.util.ArrayList;

public class DetailPengumumanAdapter extends BaseAdapter {
    private Context context;
    FragmentDetailpengumumanBinding binding;
    private ArrayList<DetailPengumuman> listPengumuman;
    private Activity activity;
    private FragmentManager fragmentManager;

    public DetailPengumumanAdapter(Activity activity, FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.listPengumuman = new ArrayList<DetailPengumuman>();

    }

    public void update(ArrayList<DetailPengumuman> listPengumuman) {
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
        DetailPengumuman pem = (DetailPengumuman) this.getItem(i);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentDetailpengumumanBinding.inflate(inflater);
        view = binding.getRoot();
        view.setTag(binding);

        binding.tvJudul.setText(pem.getTitle());
        if (pem.getTag_name().length > 0) {
            String tag_name = pem.getTag_name()[0];
            int len = pem.getTag_name().length;
            for (int j = 1; j < len; j++) {
                tag_name += ", " + pem.getTag_name()[j];
            }
            binding.tvTags.setText(tag_name);
        }
        binding.tvDesc.setText(pem.getContent());
        return view;
    }

}

