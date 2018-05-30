package com.example.carlosmario.mallinone_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carlosmario.mallinone_app.adapters.MyLocalAdapter;
import com.example.carlosmario.mallinone_app.adapters.MySearchLocalAdapter;
import com.example.carlosmario.mallinone_app.models.ListLocal;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchLocalActivity extends AppCompatActivity  {

    //private static final String URL_DATA = "http://mallinone.tk/api/local/?format=json";
    //"https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    //http://mallinone.tk/api/v1/local/?format=json";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListLocal> listLocals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_local);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearchLocal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listLocals = new ArrayList<>();

        try {
            String url = getIncomingIntent();
            loadRecyclerViewData(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

    private String getIncomingIntent() throws UnsupportedEncodingException {
        String name = getIntent().getStringExtra("LocalName");
        String url = "http://mallinone.tk/api/local/?q=" + URLEncoder.encode(name, "UTF-8");
        return url;
    }

    private void loadRecyclerViewData(String url) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando información...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results");//"hits");//"results");

                            for(int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                ListLocal local = new ListLocal(
                                        o.getString("pk"),
                                        o.getString("name"), //"tags"),//"name")
                                        o.getString("image"), //"https://defcrpc6rdpo8.cloudfront.net/madrid/up/2008/02/_vaguada-verde-centrada.jpg",
                                        o.getString("map_url"),
                                        o.getString("mall"),
                                        o.getString("highlighted")
                                );
                                listLocals.add(local);
                            }

                            if(listLocals.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                                startActivityForResult(intent, 0);
                            } else {

                                adapter = new MySearchLocalAdapter(getApplicationContext(), listLocals);
                                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                                recyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
