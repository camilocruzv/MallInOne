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
import com.example.carlosmario.mallinone_app.adapters.MyMallAdapter;
import com.example.carlosmario.mallinone_app.adapters.MyProductAdapter;
import com.example.carlosmario.mallinone_app.models.ListMall;
import com.example.carlosmario.mallinone_app.models.ListProduct;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TabLocalSearch2 extends Fragment {

    String localId, localName, localImage, localMapUrl, mallId, highlighted;

    private static final String URL_DATA = "http://mallinone.tk/api/product/?format=json";
    //"https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    //"http://mallinone.tk/api/v1/mall/?format=json";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListProduct> listProducts;

    public TabLocalSearch2(String localId, String localName, String localImage, String localMapUrl, String mallId, String highlighted) {
        this.localId = localId;
        this. localName = localName;
        this.localImage = localImage;
        this.localMapUrl = localMapUrl;
        this.mallId = mallId;
        this.highlighted = highlighted;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablocalsearch2, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewLocalTab2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listProducts = new ArrayList<>();

        setNameAndImage(localName, localImage, highlighted, rootView);

        loadRecyclerViewData(rootView);

        return rootView;
    }

    private void setNameAndImage(String localName, String localImage, String highlighted, View rootView) {
        TextView name = rootView.findViewById(R.id.localNameLocalTab2);
        name.setText(localName);

        TextView desc = rootView.findViewById(R.id.localHighligthedLocalTab2);
        desc.setText(highlighted);

        ImageView imageViewLocal = rootView.findViewById(R.id.localProductImageLocalTab2);

        Picasso.with(getContext())
                .load(localImage)
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
                                if(localId.equals(o.getString("local"))) {
                                    ListProduct product = new ListProduct(
                                            o.getString("pk"),
                                            o.getString("local"), //"tags"),//"name")
                                            o.getString("name"),
                                            //"https://defcrpc6rdpo8.cloudfront.net/madrid/up/2008/02/_vaguada-verde-centrada.jpg"
                                            o.getString("image"),
                                            o.getString("price"),
                                            o.getString("characteristics")
                                    );

                                    //if(o.getString("name").contains("S")){//equals("El Tesoro")) {
                                    listProducts.add(product);
                                    //} else {
                                    //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                    //i++;
                                    //}
                                }
                            }

                            adapter = new MyProductAdapter(listProducts, getContext(), "", "");
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
