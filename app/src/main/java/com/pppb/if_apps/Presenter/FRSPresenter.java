package com.pppb.if_apps.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pppb.if_apps.Helper.SharedPreferenceHelper;
import com.pppb.if_apps.Model.FRS;
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Model.Pengumumann;
import com.pppb.if_apps.View.FragmentFRS;
import com.pppb.if_apps.View.IFrs;
import com.pppb.if_apps.View.IPengumuman;
import com.pppb.if_apps.databinding.FragmentFrsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FRSPresenter {
    private IFrs ui;
    private Context context;
    private Gson gson;
    private FragmentFrsBinding binding;
    private ArrayList<FRS> list_semester;

    public FRSPresenter(IFrs ui, Context context, Gson gson, FragmentFrsBinding binding){
        this.ui = (IFrs) ui;
        this.context = context;
        this.gson = gson;
        this.binding = binding;
    }

    public FRSPresenter(IFrs ui, Context context) {
        this.ui = (IFrs) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }

    public void getSemester() {
        String token = SharedPreferenceHelper.getString(context.getApplicationContext(), Key.TOKEN);
        Log.d("tokenHasilSave",SharedPreferenceHelper.getString(context.getApplicationContext(),Key.TOKEN));
        this.callVolley(token);
    }

    public void callVolley(String token) {
        String url = Key.BASE_URL + "academic-years";
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
                headers.put("Authorization", "Bearer " + SharedPreferenceHelper.getString(context.getApplicationContext(),Key.TOKEN));
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void processResult(JSONObject response) throws JSONException {
        String active_years = response.getString("active_year");
        JSONArray acad_years = response.getJSONArray("academic_years");
        int len = acad_years.length();
         list_semester = new ArrayList<>();
        int[] academic_years = new int[len];
        for (int i = 0; i < len; i++) {
            academic_years[i] = acad_years.getInt(i);

            FRS frs = new FRS(active_years,academic_years);
            list_semester.add(frs);
        }
        this.ui.getSemesterList(list_semester);

    }



}


