package com.pppb.if_apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.pppb.if_apps.databinding.FragmentTambahpengumumanBinding;

import java.util.ArrayList;

public class fragment_tambahpengumuman extends Fragment{

    FragmentTambahpengumumanBinding binding;
    ArrayList<String> listItems;
    dbPengumumanAdapter db;

    public fragment_tambahpengumuman(){

    }
    public static fragment_tambahpengumuman newInstance(){
        fragment_tambahpengumuman fragment = new fragment_tambahpengumuman();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentTambahpengumumanBinding.inflate(inflater);
//        viewData();
        db = new dbPengumumanAdapter(getActivity());

        listItems = new ArrayList<>();
        binding.buttonSimpan.setOnClickListener(this::onClick);
        return binding.getRoot();
    }

    public void onClick(View view) {
        String judul = binding.etJudul.getText().toString();
        String tags = binding.etTags.getText().toString();
        String deskripsi = binding.etDeksripsi.getText().toString();

        if(judul.equals("")) {
            Toast.makeText(getContext(), "Isi judul pengumuman!", Toast.LENGTH_SHORT).show();
        }
        else if(tags.equals("")){
            Toast.makeText(getContext(), "Isi tags pengumuman!", Toast.LENGTH_SHORT).show();
        }
        else if(deskripsi.equals("")){
            Toast.makeText(getContext(), "isi deskripsi pengumuman!", Toast.LENGTH_SHORT).show();
        }
        else{
            db.insertBaris(judul,tags,deskripsi);
            Toast.makeText(getContext(), "Data dokter berhasil disimpan!", Toast.LENGTH_SHORT).show();
            Bundle result = new Bundle();
            result.putInt("page", 3);
            getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }
}
