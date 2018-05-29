package com.example.carlosmario.mallinone_app.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carlosmario.mallinone_app.R;
import com.example.carlosmario.mallinone_app.adapters.MyMallAdapter;
import com.example.carlosmario.mallinone_app.models.ListMall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tab1Malls extends Fragment {

    private static final String URL_DATA = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    //"http://mallinone.tk/api/v1/mall/?format=json";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListMall> listMalls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1malls, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listMalls = new ArrayList<>();

        loadRecyclerViewData();

        return rootView;
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando información...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("hits");//"results");

                            for(int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                //if(o.getString("name").contains("S")){//equals("El Tesoro")) {
                                ListMall mall = new ListMall(
                                        o.getString("tags"),//"name")
                                        "https://defcrpc6rdpo8.cloudfront.net/madrid/up/2008/02/_vaguada-verde-centrada.jpg"
                                );
                                //if(o.getString("name").contains("S")){//equals("El Tesoro")) {
                                listMalls.add(mall);
                                //} else {
                                //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                //i++;
                                //}
                            }

                            adapter = new MyMallAdapter(listMalls, getContext());
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
