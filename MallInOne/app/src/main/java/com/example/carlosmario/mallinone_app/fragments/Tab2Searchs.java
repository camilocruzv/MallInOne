package com.example.carlosmario.mallinone_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.carlosmario.mallinone_app.R;
import com.example.carlosmario.mallinone_app.SearchLocalActivity;
import com.example.carlosmario.mallinone_app.SearchProductActivity;

public class Tab2Searchs extends Fragment {

    SearchView searchLocal, searchProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2searchs, container, false);

        searchLocal = (SearchView) rootView.findViewById(R.id.searchView1);
        searchLocal.setIconifiedByDefault(false);
        searchLocal.setQueryHint("Buscar Local o característica");

        searchProduct = (SearchView) rootView.findViewById(R.id.searchViewProduct);
        searchProduct.setIconifiedByDefault(false);
        searchProduct.setQueryHint("Buscar Producto o característica");

        searchLocal.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchLocal.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getContext(), query, Toast.LENGTH_LONG);

                Intent intent = new Intent(getContext(), SearchLocalActivity.class);
                intent.putExtra("LocalName", query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getContext(), "Mensaje", Toast.LENGTH_LONG);
                return false;
            }
        });

        searchProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getContext(), query, Toast.LENGTH_LONG);

                Intent intent = new Intent(getContext(), SearchProductActivity.class);
                intent.putExtra("ProductName", query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getContext(), "Mensaje", Toast.LENGTH_LONG);
                return false;
            }
        });

        return rootView;
    }

}
