package com.example.carlosmario.mallinone_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MapLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_local);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("URL");

        //mapa("http://mwz.io/v/parque_comercial_el_tesoro?k=05d2cbf14e4e227f&u=default_universe&l=es");

        //mapa("https://maps.mapwize.io/#/p/parque_comercial_el_tesoro/bosi");

        mapa(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            //Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mapa(String mapa_url) {
        String url = mapa_url;
        WebView view = (WebView) this.findViewById(R.id.mapWizeWeb);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
        view.setWebViewClient(new WebViewClient());
    }
}
