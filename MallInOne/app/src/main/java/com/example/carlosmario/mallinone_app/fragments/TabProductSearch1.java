package com.example.carlosmario.mallinone_app.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carlosmario.mallinone_app.MapLocalActivity;
import com.example.carlosmario.mallinone_app.R;
import com.example.carlosmario.mallinone_app.adapters.MyLocalAdapter;
import com.example.carlosmario.mallinone_app.adapters.MyMallAdapter;
import com.example.carlosmario.mallinone_app.models.ListLocal;
import com.example.carlosmario.mallinone_app.models.ListMall;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TabProductSearch1 extends Fragment {

    String productId, productLocal, productName, productImage, price;

    private static final String URL_DATA = "http://mallinone.tk/api/local/?format=json";
    //"https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    //"http://mallinone.tk/api/v1/mall/?format=json";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListLocal> listLocals;

    public TabProductSearch1(String productId, String productLocal, String productName, String productImage, String price) {
        this.productId = productId;
        this. productLocal = productLocal;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabproductsearch1, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewProductTab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listLocals = new ArrayList<>();

        setNameAndImage(productName, productImage, rootView);

        loadRecyclerViewData(rootView);

        return rootView;
    }

    private void setNameAndImage(String productName, String productImage, View rootView) {
        TextView name = rootView.findViewById(R.id.localNameProductTab);
        name.setText(productName);

        ImageView imageViewLocal = rootView.findViewById(R.id.productProductImageProductTab);

        Picasso.with(getContext())
                .load(productImage)
                .into(imageViewLocal);
    }

    private void loadRecyclerViewData(final View rootView) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando información...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results"); //"hits");//"results");

                            for(int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                //if(o.getString("name").contains("S")){//equals("El Tesoro")) {
                                if(productLocal.equals(o.getString("pk"))) {
                                    ListLocal local = new ListLocal(
                                            o.getString("pk"),
                                            o.getString("name"), //"tags"),//"name")
                                            o.getString("image"),
                                            "",
                                            "",
                                            ""
                                            //"https://defcrpc6rdpo8.cloudfront.net/madrid/up/2008/02/_vaguada-verde-centrada.jpg"
                                    );

                                    //if(o.getString("name").contains("S")){//equals("El Tesoro")) {
                                    listLocals.add(local);
                                    //} else {
                                    //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                    //i++;
                                    //}
                                }
                            }

                            adapter = new MyLocalAdapter(getContext(), listLocals);
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
