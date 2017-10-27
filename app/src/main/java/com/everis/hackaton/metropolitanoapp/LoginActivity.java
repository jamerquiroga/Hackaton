package com.everis.hackaton.metropolitanoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnScanear;
    private int CODE_SCAN = 1;
    private MVBarcodeScanner.ScanningMode modo_Escaneo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnScanear = (Button)findViewById(R.id.btnScanear);

        UI();
    }

    private void UI(){
        btnScanear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        modo_Escaneo = MVBarcodeScanner.ScanningMode.SINGLE_AUTO;

        new MVBarcodeScanner.Builder().setScanningMode(modo_Escaneo).setFormats(Barcode.ALL_FORMATS)
                .build().launchScanner(this, CODE_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODE_SCAN){
            if (resultCode == RESULT_OK && data != null
                    && data.getExtras() != null) {
                Intent intent = new Intent(LoginActivity.this, SaludoActivity.class);
                startActivity(intent);
            }
        }
    }
}
