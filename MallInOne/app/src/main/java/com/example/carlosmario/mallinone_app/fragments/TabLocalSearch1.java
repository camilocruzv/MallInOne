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
import com.example.carlosmario.mallinone_app.models.ListMall;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TabLocalSearch1 extends Fragment {

    String localId, localName, localImage, localMapUrl, mallId;

    private static final String URL_DATA = "http://mallinone.tk/api/mall/?format=json";
    //"https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    //"http://mallinone.tk/api/v1/mall/?format=json";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListMall> listMalls;

    public TabLocalSearch1(String localId, String localName, String localImage, String localMapUrl, String mallId) {
        this.localId = localId;
        this. localName = localName;
        this.localImage = localImage;
        this.localMapUrl = localMapUrl;
        this.mallId = mallId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablocalsearch1, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewLocalTab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listMalls = new ArrayList<>();

        setNameAndImage(localName, localImage, rootView);

        loadRecyclerViewData(rootView);

        return rootView;
    }

    private void setNameAndImage(String localName, String localImage, View rootView) {
        TextView name = rootView.findViewById(R.id.localNameLocalTab);
        name.setText(localName);

        ImageView imageViewLocal = rootView.findViewById(R.id.localProductImageLocalTab);

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
                                if(mallId.equals(o.getString("pk"))) {
                                    ListMall mall = new ListMall(
                                            o.getString("pk"),
                                            o.getString("name"), //"tags"),//"name")
                                            o.getString("image")
                                            //"https://defcrpc6rdpo8.cloudfront.net/madrid/up/2008/02/_vaguada-verde-centrada.jpg"
                                    );

                                    //if(o.getString("name").contains("S")){//equals("El Tesoro")) {
                                    listMalls.add(mall);
                                    //} else {
                                    //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                    //i++;
                                    //}
                                }
                            }

                            Button map = (Button) rootView.findViewById(R.id.buttonMapLocalTab);
                            map.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent (v.getContext(), MapLocalActivity.class);
                                    intent.putExtra("URL", localMapUrl);//"https://maps.mapwize.io/#/p/parque_comercial_el_tesoro/bosi");
                                    startActivityForResult(intent, 0);
                                }
                            });

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
