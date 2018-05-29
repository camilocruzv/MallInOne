package com.example.carlosmario.mallinone_app.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.carlosmario.mallinone_app.R;

public class Tab3Map extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3map, container, false);

        if(getArguments() != null) {
            String url = getArguments().getString("URL");
            mapa(url, rootView);
        } else {
            mapa("http://mwz.io/v/parque_comercial_el_tesoro?k=05d2cbf14e4e227f&u=default_universe&l=es", rootView);
        }

        //mapa("http://mwz.io/v/parque_comercial_el_tesoro?k=05d2cbf14e4e227f&u=default_universe&l=es", rootView);

        return rootView;
    }

    private void mapa(String mapa_url, View rootView) {
        String url = mapa_url;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando información...");
        progressDialog.show();
        WebView view = (WebView) rootView.findViewById(R.id.mapWizeWeb);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
        view.setWebViewClient(new WebViewClient());
        progressDialog.dismiss();
    }
}
