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
import com.pppb.if_apps.Helper.SharedPreferenceHelper;
import com.pppb.if_apps.Model.DetailPengumuman;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.View.IDetailP;
import com.pppb.if_apps.databinding.FragmentPengumumanBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailPengumumanPresenter {
    private IDetailP ui;
    private Context context;
    private Gson gson;
    private FragmentPengumumanBinding binding;
    private String token;
    private ArrayList<DetailPengumuman> list_pengumuman;

    public DetailPengumumanPresenter(IDetailP ui, Context context) {
        this.ui = ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }

    public void getDetailPengumuman(String id) {
        String token = SharedPreferenceHelper.getString(context.getApplicationContext(), Key.TOKEN);
        Log.d("tokenHasilSave", SharedPreferenceHelper.getString(context.getApplicationContext(), Key.TOKEN));
        this.callVolley(id);
    }

    public void callVolley(String id) {
        String url = Key.BASE_URL + "announcements/" + id;
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
                headers.put("Authorization", "Bearer " + SharedPreferenceHelper.getString(context.getApplicationContext(), Key.TOKEN));
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void processResult(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        list_pengumuman = new ArrayList<>();
        String id = jsonObject.getString("id");
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
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
        DetailPengumuman detailPengumuman = new DetailPengumuman(id, title, content, updated_at, created_at, author_id,
                author_name, tag_name, tag_id);
        list_pengumuman.add(detailPengumuman);

        this.ui.updateDetail(detailPengumuman);
    }
}

