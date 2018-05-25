package com.example.carlosmario.mallinone_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BosiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosi);

        Button map = (Button) findViewById(R.id.buttonMalls);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Tab3Map.class);
                intent.putExtra("URL", "https://maps.mapwize.io/#/p/parque_comercial_el_tesoro/bosi");
                startActivityForResult(intent, 0);
            }
        });
    }
}
