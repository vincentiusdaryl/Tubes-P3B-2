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
import com.pppb.if_apps.Model.GetPengumuman;
import com.pppb.if_apps.Model.Key;
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

    public PengumumanPresenter(IPengumuman ui, Context context){
        this.ui = (IPengumuman) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }
    public void getPengumuman(){
            String token = Key.TOKEN;
            this.callVolley(token);
    }

    public void callVolley(String token){
        String url = Key.BASE_URL + "announcements?limit=10";
        RequestQueue queue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            processResult(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
            public Map<String, String> getHeaders() {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + token);
            return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }
    private void processResult(String jsonObject) throws JSONException {
        GetPengumuman res = gson.fromJson(jsonObject, GetPengumuman.class);
        this.ui.getPengumumanList(res);
    }
    }

