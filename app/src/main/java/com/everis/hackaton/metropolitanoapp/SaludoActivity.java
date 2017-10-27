package com.everis.hackaton.metropolitanoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SaludoActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMisTarjetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        btnMisTarjetas = (Button)findViewById(R.id.btnMisTarjetas);
        enviarMisTarjetas();
    }

    private void enviarMisTarjetas(){
        btnMisTarjetas.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(SaludoActivity.this, MainActivity.class);
        startActivity(intent);


    }
}
