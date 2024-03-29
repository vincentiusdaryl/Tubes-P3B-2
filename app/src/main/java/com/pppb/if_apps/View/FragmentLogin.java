package com.pppb.if_apps.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.pppb.if_apps.Helper.SharedPreferenceHelper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Presenter.LoginPresenter;
import com.pppb.if_apps.R;
import com.pppb.if_apps.databinding.FragmentLoginBinding;

import java.util.Objects;

public class FragmentLogin extends Fragment implements View.OnClickListener, ILogin {
    private FragmentLoginBinding binding;
    private LoginPresenter presenter;
    private FragmentManager fragmentManager;
    private String token = "";
    private SharedPreferenceHelper spHelper;
    private Context context;

    public FragmentLogin() {
    }

    public static FragmentLogin newInstance() {
        return new FragmentLogin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.fragmentManager = getParentFragmentManager();
        this.binding = FragmentLoginBinding.inflate(inflater, container, false);
        this.presenter = new LoginPresenter(this, getActivity(), binding);
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
            this.token = Key.TOKEN;
            Log.d("cekToken",this.token);
            SharedPreferenceHelper.setBoolean(getActivity(),Key.PREF_IS_LOGIN,true);
            SharedPreferenceHelper.setString(getActivity(),Key.PREF_ROLE,"admin");
            Log.d("cekRole",SharedPreferenceHelper.getString(getActivity(),Key.PREF_ROLE));
            Log.d("suksesSaveToken",SharedPreferenceHelper.getString(getContext(),Key.TOKEN));

        }
        else if(view == this.binding.btnMhs){
            this.reqLogin(Key.ROLE_MHS);
            Log.d("sukses", "sukses");
            SharedPreferenceHelper.setString(getActivity(),"token",Key.TOKEN);
            SharedPreferenceHelper.setBoolean(getActivity(),Key.PREF_IS_LOGIN,true);
            SharedPreferenceHelper.setString(getActivity(),Key.PREF_ROLE,"mahasiswa");
            Log.d("suksesSaveToken", "Berhasil save token");
        }
        else if(view == this.binding.btnDosen){
            this.reqLogin(Key.ROLE_DOSEN);
            Log.d("sukses", "sukses");
            SharedPreferenceHelper.setString(getActivity(),"token",Key.TOKEN);
            SharedPreferenceHelper.setBoolean(getActivity(),Key.PREF_IS_LOGIN,true);
            SharedPreferenceHelper.setString(getActivity(),Key.PREF_ROLE,"dosen");
            Log.d("suksesSaveToken",SharedPreferenceHelper.getString(getContext(),"token"));
        }
    }

    private void closeSoftKeyboard () {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void reqLogin(String roles){
        String email = this.binding.etEmail.getText().toString();
        String password = this.binding.etPwd.getText().toString();

        this.presenter.reqLogin(email, password, roles);
    }

    public void changePage(int page){
        Bundle result = new Bundle();
        result.putInt(Key.CHANGE_PAGE_NUMBER, page);
        this.fragmentManager.setFragmentResult(Key.CHANGE_PAGE, result);
    }

    public void showLoginStatus(String loginStatus, boolean statusLogin){
        if(statusLogin){
            this.closeSoftKeyboard();
            this.binding.tvStatusLogin.setTextColor(getResources().getColor(R.color.green));
            this.changePage(1);
            this.sendToken(token);
        }
        else{
            this.binding.tvStatusLogin.setTextColor(getResources().getColor(R.color.red));
            this.binding.tvStatusLogin.setText(loginStatus);
        }
    }

    public void sendToken(String token){
        Bundle result = new Bundle();
        result.putString("token", token);
        this.fragmentManager.setFragmentResult("GET_TOKEN", result);
    }

    public void setToken (String token) {
        this.token = token;
    }
}
