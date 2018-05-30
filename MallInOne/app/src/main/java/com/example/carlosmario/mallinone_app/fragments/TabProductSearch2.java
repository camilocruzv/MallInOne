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
public class TabProductSearch2 extends Fragment {

    String productId, productLocal, productName, productImage, price, characteristics;


    private RecyclerView recyclerView;

    public TabProductSearch2(String productId, String productLocal, String productName, String productImage, String price, String characteristics) {
        this.productId = productId;
        this. productLocal = productLocal;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.characteristics = characteristics;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabproductsearch2, container, false);

        setNameAndImage(productName, productImage, characteristics, price, rootView);

        return rootView;
    }

    private void setNameAndImage(String localName, String localImage, String highlighted, String price, View rootView) {
        TextView name = rootView.findViewById(R.id.localNameProductTab2);
        name.setText(localName);

        TextView desc = rootView.findViewById(R.id.productHighligthedProductTab2);
        desc.setText(highlighted);

        ImageView imageViewLocal = rootView.findViewById(R.id.productImageLocalTab2);

        Picasso.with(getContext())
                .load(localImage)
                .into(imageViewLocal);

        TextView precio = rootView.findViewById(R.id.price);
        precio.setText("Precio:     &" + price);
    }
}
