package com.pppb.if_apps.Presenter;

import android.content.Context;
import android.util.Log;

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

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter {
    private ILogin ui;
    private Context context;
    private Gson gson;

    public LoginPresenter(ILogin ui, Context context){
        this.ui = (ILogin) ui;
        this.context = context;
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
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("ERROR", error.toString());
                        ui.showLoginStatus(error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void processResult(String jsonObject) {
        ResLogin res = gson.fromJson(jsonObject, ResLogin.class);

        if(res.getToken()!=null){
            Key.TOKEN = res.getToken();
        }

        this.ui.showLoginStatus(Key.TOKEN);
        Log.d("token", Key.TOKEN);
    }
}
