package com.pppb.if_apps.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Presenter.LoginPresenter;
import com.pppb.if_apps.databinding.FragmentLoginBinding;

public class FragmentLogin extends Fragment implements View.OnClickListener, ILogin {
    private FragmentLoginBinding binding;
    private LoginPresenter presenter;

    public FragmentLogin() {

    }

    public static FragmentLogin newInstance() {
        return new FragmentLogin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentLoginBinding.inflate(inflater, container, false);

        this.presenter = new LoginPresenter(this, getActivity());
        this.binding.btnAdmin.setOnClickListener(this::onClick);
        this.binding.btnMhs.setOnClickListener(this::onClick);
        this.binding.btnDosen.setOnClickListener(this::onClick);
        return this.binding.getRoot();

    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnAdmin){
            this.reqLogin(Key.ROLE_ADMIN);
            Log.d("sukses", "sukses");
        }
        else if(view == this.binding.btnMhs){
            this.reqLogin(Key.ROLE_MHS);
        }
        else if(view == this.binding.btnDosen){
            this.reqLogin(Key.ROLE_DOSEN);
        }
    }

    private void reqLogin(String roles){
        String email = this.binding.etEmail.getText().toString();
        String password = this.binding.etPwd.getText().toString();

        this.presenter.reqLogin(email, password, roles);
    }

    public void showLoginStatus(String loginStatus){
        this.binding.tvStatusLogin.setText(loginStatus);
    }
}
