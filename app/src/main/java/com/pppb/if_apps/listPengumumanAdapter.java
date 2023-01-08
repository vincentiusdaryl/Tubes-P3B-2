package com.pppb.if_apps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;

import java.util.ArrayList;

public class listPengumumanAdapter extends BaseAdapter {
    private Context context;
    FragmentPengumumanBinding binding;
    private ArrayList<Pengumumann> listPengumuman;
    private Activity activity;
    private FragmentManager fragmentManager;

    public listPengumumanAdapter(Activity activity, FragmentManager fragmentManager){
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.listPengumuman = new ArrayList<>();

    }

    public void update(ArrayList<Pengumumann> listPengumuman){
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
        Pengumumann pem =(Pengumumann) this.getItem(i);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = FragmentPengumumanBinding.inflate(inflater);
        view = binding.getRoot();
        view.setTag(binding);

        String id = pem.getId();
        String title = pem.getTitle();
        String updated_at = pem.getUpdated_at();
        String created_at = pem.getCreated_at();
        String author = pem.getAuthor();
        String[] tags = pem.getTags();
        String[] tag_id = pem.getTag_id();

        return view;

    }
}

