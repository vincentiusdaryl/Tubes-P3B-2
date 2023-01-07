package com.pppb.if_apps.Presenter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pppb.if_apps.R;
import com.pppb.if_apps.databinding.FragmentListpengumumanBinding;
import com.pppb.if_apps.pengumuman;

import java.util.ArrayList;

public class listPengumumanAdapter extends BaseAdapter {

    private Context context;
    FragmentListpengumumanBinding binding;
    private ArrayList<listPengumumanAdapter> listPengumuman;

    public listPengumumanAdapter(Context context, ArrayList<listPengumumanAdapter> listPengumuman){
        this.context = context;
        this.listPengumuman = listPengumuman;
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

