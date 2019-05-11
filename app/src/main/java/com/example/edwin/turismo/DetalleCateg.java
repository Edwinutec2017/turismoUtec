package com.example.edwin.turismo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleCateg extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
/*para la categoria*/
private String titulo,fecha,descuento,contacto,comercio,email,telefono,descripcion,coordenadas;
private Bitmap img;
private int id;

/*-----------------*/
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_categ);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.principal);
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fab.setVisibility(View.INVISIBLE);



Bundle recibir =getIntent().getExtras();
id=recibir.getInt("id");
titulo="Titulo :"+recibir.getString("titulo");
fecha=recibir.getString("fecha");
descuento=String.valueOf(recibir.getDouble("descuento"))+"%";
descripcion=recibir.getString("descripcion");
contacto=recibir.getString("contacto");
telefono=recibir.getString("telefono");
comercio=recibir.getString("comercio");
email=recibir.getString("email");
coordenadas=recibir.getString("ubicacion");
img= (Bitmap) recibir.get("img");
//Toast.makeText(getApplicationContext(),"img"+img,Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        retornar();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_categ, menu);
        return true;
    }


public void retornar(){
    Intent re=new Intent(getApplicationContext(),MenuPrincipal.class);
    startActivity(re);
        finish();
}
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
     switch (position){
         case 0:
             return new Detalle(titulo,fecha,descuento,descripcion,contacto,telefono,comercio,email,img);
         case 1:return new Ubicacion();
         case 2: return new Cotizacion();


     }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:return "Detalle";
                case 1: return "Ubicacion";
                case 2: return "Cotizacion";

            }
            return null;

        }
    }


}
