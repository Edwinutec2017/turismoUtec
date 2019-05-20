package com.example.edwin.turismo;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PrincipalPreferencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_preferencia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Configuraciones");
        /*llama del fralment*/
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.add(android.R.id.content,new Preferencia());
        ft.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
        finish();
        return false;
    }
}


