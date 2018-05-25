package com.example.carlosmario.mallinone_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class LocalsActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView bankCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        bankCard = (CardView) findViewById(R.id.tesoro);

        bankCard.setOnClickListener(this);

        getIncomingIntent();
    }

    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        Intent i = new Intent(this, BosiActivity.class);
        startActivity(i);
    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("MallName")) {
            String name = getIntent().getStringExtra("MallName");

            setName(name);
        }
    }

    private void setName(String mallName) {
        TextView name = findViewById(R.id.mallName);
        name.setText(mallName);
    }
}
