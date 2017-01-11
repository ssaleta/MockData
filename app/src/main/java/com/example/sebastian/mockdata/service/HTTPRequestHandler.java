package com.example.sebastian.mockdata.service;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
        * Created by Sebastian on 2017-01-10.
        */
public class HTTPRequestHandler {

    private RequestQueue requestQueue;
    private static HTTPRequestHandler httpRequestHandler;

    private HTTPRequestHandler() {
    }

    public static HTTPRequestHandler getInstance() {
        if (httpRequestHandler == null) {
            httpRequestHandler = new HTTPRequestHandler();
        }
        return httpRequestHandler;
    }

    public void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void sendGetRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
        requestQueue.add(stringRequest);
    }
}
