package com.pppb.if_apps.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Presenter.HomePresenter;
import com.pppb.if_apps.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment implements View.OnClickListener, IHome {
    FragmentHomeBinding binding;
    private HomePresenter presenter;
    private FragmentManager fragmentManager;

    public FragmentHome() {

    }

    public static FragmentHome newInstance() {
        return new FragmentHome();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.fragmentManager = getParentFragmentManager();
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);

        this.presenter = new HomePresenter(this, getActivity(), binding);
        this.binding.btnPengumuman.setOnClickListener(this::onClick);
        this.binding.btnPertemuan.setOnClickListener(this::onClick);
        this.binding.btnFRS.setOnClickListener(this::onClick);
        this.binding.btnKeluar.setOnClickListener(this::onClick);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnPengumuman){
            this.changePage(Key.FRAGMENT_PENGUMUMAN);
        }
        else if(view == this.binding.btnPertemuan){
            this.changePage(Key.FRAGMENT_PERTEMUAN);
        }
        else if(view == this.binding.btnFRS){
            this.changePage(Key.FRAGMENT_FRS);
        }
        else if(view == this.binding.btnKeluar){
            this.changePage(Key.PAGE_EXIT);
        }
    }

    public void changePage(int page){
        Bundle result = new Bundle();
        result.putInt(Key.CHANGE_PAGE_NUMBER, page);
        this.fragmentManager.setFragmentResult(Key.CHANGE_PAGE, result);
    }
}
