package com.pppb.if_apps.Presenter;

import android.content.Context;
import android.util.Log;

import com.pppb.if_apps.Helper.SharedPreferenceHelper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Model.Login;
import com.pppb.if_apps.Model.ResLogin;
import com.pppb.if_apps.View.ILogin;
import com.pppb.if_apps.databinding.FragmentLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter {
    private ILogin ui;
    private Context context;
    private Gson gson;
    private FragmentLoginBinding binding;
    private SharedPreferenceHelper spHelper;

    public LoginPresenter(ILogin ui, Context context, FragmentLoginBinding binding){
        this.ui = (ILogin) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }

    public void reqLogin(String email, String password, String role){
        try {
            this.callVolley(new JSONObject(this.gson.toJson(new Login(email,password, role))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void callVolley(JSONObject jsonObject){
        String url = Key.BASE_URL + "authenticate/";
        RequestQueue queue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        processResult(response.toString());
                        Log.d("response", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String body = "";
                        String errRes = "";
                        if(error.networkResponse.data!=null) {
                            try {
                                body = new String(error.networkResponse.data);
                                JSONObject json = new JSONObject(body);
                                errRes = json.getString("errcode");
                                Log.d("error", errRes);
                                if(errRes.equals("E_AUTH_FAILED")){
                                    ui.showLoginStatus("Email atau Password Salah!", false);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if(binding.etEmail.getText().toString().equals("") && binding.etPwd.getText().toString().equals("")){
                            ui.showLoginStatus("Email atau Password harus diisi!", false);
                        }
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void processResult(String jsonObject) {
        ResLogin res = gson.fromJson(jsonObject, ResLogin.class);
        String token = "";
        if(res.getToken()!=null){
            Key.TOKEN = res.getToken();
            token = res.getToken();

            this.ui.showLoginStatus("Login Berhasil", true);
        }

        Log.d("token", Key.TOKEN);
        SharedPreferenceHelper.setString(context.getApplicationContext(),Key.TOKEN,Key.TOKEN);
        Log.d("CekTokenDiLP",SharedPreferenceHelper.getString(context.getApplicationContext(),Key.TOKEN));
    }

}
