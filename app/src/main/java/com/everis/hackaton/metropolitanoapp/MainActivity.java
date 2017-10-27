package com.everis.hackaton.metropolitanoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtSDMonto;
    Button btnRecargaFija;
    Button btnRecargaOtro;
    CustomAdapter adapter;
    ViewPager viewPager;

    int color = R.color.amarillo;
    int color2 = R.color.verde;
    int color3 = R.color.anaranjado;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_arrow:
                    //Intent intent0 = new Intent(MainActivity.this, MainActivity.class);
                    //startActivity(intent0);
                    break;
                case R.id.ic_android:
                    Intent intent1 = new Intent(MainActivity.this, TwoActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ic_books:
                    Intent intent2 = new Intent(MainActivity.this, ThreeActivity.class);
                    startActivity(intent2);
                    break;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSDMonto = (TextView) findViewById(R.id.txtSDMonto);
        btnRecargaFija = (Button) findViewById(R.id.btnRecargaFija);
        btnRecargaOtro = (Button) findViewById(R.id.btnRecargaOtro);

        viewPager = (ViewPager)findViewById(R.id.myViewPager);
        adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new DetailOnPageChangeListener());

        btnRecargaOtro.setOnClickListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        colorStatusBar(this.color);


    }

    public void colorStatusBar(int color){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this.getApplicationContext(),color));
        }
    }

    @Override
    public void onClick(View view) {

        View contenedor = view.getRootView();

        //click en otras recargas
        switch(view.getId()){
            case R.id.btnRecargaOtro:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Recarga virtual");
                builder.setMessage("Monto a recargar: S/. 20.00");
                builder.setCancelable(false);

                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"PRESIONAR EN OK",Toast.LENGTH_SHORT).show();
                        //LOGICA AQUI 
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"PRESIONAR EN CANCEL",Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();

                break;

        }
    }

    public class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

            if(position==0){
                btnRecargaFija.setText("+ S/. 2.50");
                txtSDMonto.setText("S/. 5.50");
                colorStatusBar(color);
            }
            else if(position==1){
                btnRecargaFija.setText("+ S/. 1.25");
                txtSDMonto.setText("S/. 8.00");
                colorStatusBar(color2);
            }else {
                btnRecargaFija.setText("+ S/. 1.25");
                txtSDMonto.setText("S/. 0.50");
                colorStatusBar(color3);
            }
        }
    }

}
