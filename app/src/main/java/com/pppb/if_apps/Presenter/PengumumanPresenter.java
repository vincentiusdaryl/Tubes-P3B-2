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
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.View.IPengumuman;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PengumumanPresenter {
    private IPengumuman ui;
    private Context context;
    private Gson gson;
    private FragmentPengumumanBinding binding;
    private String token;

    public PengumumanPresenter(IPengumuman ui, Context context) {
        this.ui = (IPengumuman) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }

    public void getPengumuman() {
        String token = Key.TOKEN;
        this.callVolley(token);
    }

    public void callVolley(String token) {
        String url = Key.BASE_URL + "announcements?limit=10";
        RequestQueue queue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            processResult(response);
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
                        if (error.networkResponse.data != null) {
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
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

    //    private void processResult(String jsonObject) throws JSONException {
//        GetPengumuman res = gson.fromJson(jsonObject, GetPengumuman.class);
//        this.ui.getPengumumanList(res);
//    }
    private void processResult(JSONObject response) throws JSONException {
        JSONArray data = response.getJSONArray("data");
        int len = data.length();
        ArrayList<Pengumumann> list_pengumuman = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            String id = jsonObject.getString("id");
            String title = jsonObject.getString("title");
            String updated_at = jsonObject.getString("updated_at");
            String created_at = jsonObject.getString("created_at");
            JSONObject author = jsonObject.getJSONObject("author");
            String author_id = author.getString("id");
            String author_name = author.getString("author");
            JSONArray tags = jsonObject.getJSONArray("tags");
            int tag_length = tags.length();
            String[] tag_name = new String[tag_length];
            String[] tag_id = new String[tag_length];
            if (tag_length > 0) {
                for (int j = 0; j < tag_length; j++) {
                    JSONObject tags_item = tags.getJSONObject(j);
                    tag_name[j] = tags_item.getString("tag");
                    tag_id[j] = tags_item.getString("tag_id");
                }
            }


            Pengumumann pengumumann = new Pengumumann(id, title, updated_at, created_at, author_id,
                    author_name, tag_name, tag_id);
            list_pengumuman.add(pengumumann);
        }
        this.ui.getPengumumanList(list_pengumuman);
    }
}

