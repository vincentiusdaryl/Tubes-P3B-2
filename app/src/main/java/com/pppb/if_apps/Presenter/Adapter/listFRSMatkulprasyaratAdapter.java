package com.pppb.if_apps.Presenter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pppb.if_apps.R;
import com.pppb.if_apps.databinding.FragmentListfrsMatkulprasyaratBinding;
import com.pppb.if_apps.matkulprasyarat;

import java.util.ArrayList;

public class listFRSMatkulprasyaratAdapter extends BaseAdapter {
    private Context context;
    FragmentListfrsMatkulprasyaratBinding binding;
    private ArrayList<listFRSMatkulprasyaratAdapter> listFRSMatkulprasyarat;

    public listFRSMatkulprasyaratAdapter(Context context, ArrayList<listFRSMatkulprasyaratAdapter> listFRSMatkulprasyarat){
        this.context = context;
        this.listFRSMatkulprasyarat = listFRSMatkulprasyarat;
    }

    @Override
    public int getCount() {
        return listFRSMatkulprasyarat.size();
    }

    @Override
    public Object getItem(int i) {
        return listFRSMatkulprasyarat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentListfrsMatkulprasyaratBinding.inflate(inflater);

        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.fragment_listfrs_matkulprasyarat, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        matkulprasyarat matkulprasyarat = (matkulprasyarat) getItem(i);
        System.out.println(matkulprasyarat.getnamaMatkulprasyarat());

        viewHolder.add(matkulprasyarat);
        return binding.getRoot();

    }
    private class ViewHolder{

        ViewHolder(View view){

        }
        void add(matkulprasyarat matkulprasyarat){
            binding.tvTitleMatkulPrasyarat.setText(matkulprasyarat.getnamaMatkulprasyarat());
        }
    }
}
