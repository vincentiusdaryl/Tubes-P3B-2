package com.pppb.if_apps.Presenter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pppb.if_apps.R;
import com.pppb.if_apps.databinding.FragmentListfrsSemesterBinding;
import com.pppb.if_apps.prasyaratsemester;

import java.util.ArrayList;

public class listFRSSemesterAdapter extends BaseAdapter {

    private Context context;
    FragmentListfrsSemesterBinding binding;
    private ArrayList<listFRSSemesterAdapter> listFRSSemester;

    public listFRSSemesterAdapter(Context context, ArrayList<listFRSSemesterAdapter> listFRSSemester){
        this.context = context;
        this.listFRSSemester = listFRSSemester;
    }

    @Override
    public int getCount() {
        return listFRSSemester.size();
    }

    @Override
    public Object getItem(int i) {
        return listFRSSemester.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentListfrsSemesterBinding.inflate(inflater);

        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.fragment_listfrs_semester, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        prasyaratsemester prasyaratsemester = (prasyaratsemester) getItem(i);
        System.out.println(prasyaratsemester.getMatkul());

        viewHolder.add(prasyaratsemester);
        return binding.getRoot();

    }
    private class ViewHolder{

        ViewHolder(View view){

        }
        void add(prasyaratsemester prasyaratsemester){
            binding.tvNamaMatkulPrasyarat2.setText(prasyaratsemester.getMatkul());
            binding.tvStatus.setText(prasyaratsemester.getStatus());
        }
    }
}
