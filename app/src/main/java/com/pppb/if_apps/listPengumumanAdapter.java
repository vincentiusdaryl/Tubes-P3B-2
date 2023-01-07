package com.pppb.if_apps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pppb.if_apps.View.FragmentPengumuman;
import com.pppb.if_apps.databinding.FragmentListpengumumanBinding;

import java.util.ArrayList;

public class listPengumumanAdapter extends BaseAdapter {
    private Context context;
    FragmentListpengumumanBinding binding;
    private ArrayList<listPengumumanAdapter> listPengumuman;
    private Activity activity;
    private FragmentManager fragmentManager;

    public listPengumumanAdapter(Activity activity, FragmentManager fragmentManager){
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.listPengumuman = new ArrayList<>();

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
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentListpengumumanBinding.inflate(inflater);

        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.fragment_listpengumuman, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        pengumuman pengumuman = (pengumuman) getItem(i);
        System.out.println(pengumuman.getJudul());

        viewHolder.add(pengumuman);
        return binding.getRoot();

    }
    private class ViewHolder{

        ViewHolder(View view){

        }
        void add(pengumuman pengumuman){
            binding.tvTitlePengumuman.setText(pengumuman.getJudul());
            binding.tvTags.setText(pengumuman.getTags());
        }
    }
}

