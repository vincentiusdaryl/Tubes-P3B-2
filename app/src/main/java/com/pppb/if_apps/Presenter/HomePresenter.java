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
import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.View.IHome;
import com.pppb.if_apps.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomePresenter {
    private IHome ui;
    private Context context;
    private Gson gson;
    private FragmentHomeBinding binding;

    public HomePresenter(IHome ui, Context context, FragmentHomeBinding binding){
        this.ui = (IHome) ui;
        this.context = context;
        this.binding = binding;
        this.gson = new Gson();
    }

    public void getUserSelf(){
        String token = SharedPreferenceHelper.getString(context.getApplicationContext(), Key.TOKEN);
        this.callVolley(token);
    }

    public void callVolley(String token) {
        String url = Key.BASE_URL + "/users/self";
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
                headers.put("Authorization", "Bearer " + SharedPreferenceHelper.getString(context.getApplicationContext(),Key.TOKEN));
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void processResult(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String email = jsonObject.getString("email");
        JSONArray roles = jsonObject.getJSONArray("roles");
        int len = roles.length();
        String[] role = new String[len];
        for(int i = 0; i<len;i++){
            JSONObject role_item = roles.getJSONObject(i);
            role[i] = role_item.getString("roles");
        }
    }


}
