package com.pppb.if_apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pppb.if_apps.databinding.FragmentListfrsMatkulBinding;
import com.pppb.if_apps.databinding.FragmentListfrsMatkulprasyaratBinding;

import java.util.ArrayList;

public class listFRSMatkulAdapter extends BaseAdapter {
    private Context context;
    FragmentListfrsMatkulBinding binding;
    private ArrayList<listFRSMatkulAdapter> listFRSMatkul;

    @Override
    public int getCount() {
        return listFRSMatkul.size();
    }

    @Override
    public Object getItem(int i) {
        return listFRSMatkul.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentListfrsMatkulBinding.inflate(inflater);

        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.fragment_listfrs_matkul, viewGroup, false);
        }

        listFRSMatkulAdapter.ViewHolder viewHolder = new listFRSMatkulAdapter.ViewHolder(itemView);
        matkul matkul = (matkul) getItem(i);
        System.out.println(matkul.getmatkul());

        viewHolder.add(matkul);
        return binding.getRoot();

    }
    private class ViewHolder{

        ViewHolder(View view){

        }
        void add(matkul matkul){
            binding.tvTitleMatkul.setText(matkul.getmatkul());
        }
    }
}
