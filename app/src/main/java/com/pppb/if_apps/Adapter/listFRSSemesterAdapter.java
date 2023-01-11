package com.pppb.if_apps;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.FRS;
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.databinding.FragmentListfrsSemesterBinding;
import com.pppb.if_apps.databinding.FragmentListpengumumanBinding;

import java.util.ArrayList;

public class listFRSSemesterAdapter extends BaseAdapter {

    private FragmentManager fragmentManager;
    private Context context;
    FragmentListfrsSemesterBinding binding;
    private ArrayList<FRS> listFRSSemester;
    private Activity activity;

    public listFRSSemesterAdapter(Activity activity, FragmentManager fragmentManager){
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.listFRSSemester= new ArrayList<>();
    }
    public void update(ArrayList<FRS> listFRSSemester) {
        this.listFRSSemester.addAll(listFRSSemester);
        this.notifyDataSetChanged();
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
        FRS frs = (FRS) this.getItem(i);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentListfrsSemesterBinding.inflate(inflater);
        view = binding.getRoot();
        view.setTag(binding);

        int len = frs.getAcademic_years().length;
        Log.d("cekLength",String.valueOf(len));
        String years = String.valueOf(frs.getAcademic_years()[i]);
        String years2 = String.valueOf(frs.getAcademic_years()[i+3]);
        if (years.substring(4).equals("1")){
            binding.tvTitleMatkul.setText("Semester Ganjil Tahun "+years.substring(0,4)+"-"+years2.substring(0,4));
        }
        if (years.substring(4).equals("2")){
            binding.tvTitleMatkul.setText("Semester Genap Tahun "+years.substring(0,4)+"-"+years2.substring(0,4));
        }
        if (years.substring(4).equals("3")){
            binding.tvTitleMatkul.setText("Semester Pendek Tahun "+years.substring(0,4)+"-"+years2.substring(0,4));
        }

        return view;

    }
}
