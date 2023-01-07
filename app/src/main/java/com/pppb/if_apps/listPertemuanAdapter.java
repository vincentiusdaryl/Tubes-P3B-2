package com.pppb.if_apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pppb.if_apps.databinding.FragmentPertemuanBinding;

import java.util.ArrayList;

public class listPertemuanAdapter extends BaseAdapter{

        private Context context;
        FragmentPertemuanBinding binding;
        private ArrayList<com.pppb.if_apps.listPertemuanAdapter> listPertemuan;

        public listPertemuanAdapter(Context context, ArrayList<com.pppb.if_apps.listPertemuanAdapter> listPertemuan){
            this.context = context;
            this.listPertemuan = listPertemuan;
        }

        @Override
        public int getCount() {
            return listPertemuan.size();
        }

        @Override
        public Object getItem(int i) {
            return listPertemuan.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            binding = FragmentPertemuanBinding.inflate(inflater);

            View itemView = view;

            if (itemView == null) {
                itemView = LayoutInflater.from(context).inflate(R.layout.fragment_listpertemuan, viewGroup, false);
            }

            com.pppb.if_apps.listPertemuanAdapter.ViewHolder viewHolder = new com.pppb.if_apps.listPertemuanAdapter.ViewHolder(itemView);
            pertemuan pertemuan = (pertemuan) getItem(i);
            System.out.println(pertemuan.getJudul());

            viewHolder.add(pertemuan);
            return binding.getRoot();

        }
        private class ViewHolder{

            ViewHolder(View view){

            }
            void add(pertemuan pertemuan){
//                binding.tv_listpertemuan_judul.setText(pertemuan.getJudul());
//                binding.tv_listpertemuan_tanggal.setText(pertemuan.getTanggal());

            }
        }
}
