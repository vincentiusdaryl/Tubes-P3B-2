package com.pppb.if_apps.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pppb.if_apps.Model.GetPengumuman;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.View.IPengumuman;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PengumumanPresenter {
    private IPengumuman ui;
    private Context context;
    private Gson gson;
    private FragmentPengumumanBinding binding;
    private String token;
    private Pengumumann pengumumann;

    public PengumumanPresenter(IPengumuman ui, Context context){
        this.ui = (IPengumuman) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }
    public void getPengumuman(){
            token = Key.TOKEN;
            this.callVolley(token);
    }

    public void callVolley(String token){
        String url = Key.BASE_URL + "announcements/";
        RequestQueue queue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        processResult(response.toString());
                        Log.d("respons", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String body;
                        String errRes;
                        if(error.networkResponse.data!=null) {
                            try {
                                body = new String(error.networkResponse.data);
                                JSONObject json = new JSONObject(body);
                                errRes = json.getString("errcode");
                                Log.d("error", errRes);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer"+token);
                return map;
            }
        };
        queue.add(jsonObjectRequest);
    }
    private void processResult(String jsonObject) {
        GetPengumuman res = gson.fromJson(jsonObject, GetPengumuman.class);
        this.ui.getPengumumanList(res);
        Log.d("berhasil", "processResult: ");
    }
    }

