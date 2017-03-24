package com.example.enzo.practica_fragmentos_catalan;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class    MainActivity extends AppCompatActivity implements TituloFragment.OnTituloSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // agregar fragmentos
        // preguntar si el fragmento existe
        if (findViewById(R.id.fragment_container)!= null){
            // preguntar si existe la variable savedInstanceState existe
            if (savedInstanceState != null){
                //regresando del onPause
                return;
            }

            //crear un nuevo fragmento
            TituloFragment tituloFragment = new TituloFragment();

            //agregar extras si existen (si otras actividades mandaron info)
            tituloFragment.setArguments(getIntent().getExtras());

            //lanzar la vista
            //FragmentManager fragmentManager = this.getSupportFragmentManager();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, tituloFragment).commit();
        }
    }

    @Override
    public void onTituloSelected(int position) {

        //tomando el valor del fragmento
        ParrafoFragment parrafoFragment = (ParrafoFragment)getSupportFragmentManager().findFragmentById(R.id.fgm_parrafo);

        if (parrafoFragment != null){
            parrafoFragment.updateParrafoView(position);
        }else{
            ParrafoFragment fragmentNuevo = new ParrafoFragment();
            Bundle args = new Bundle();
            args.putInt(ParrafoFragment.ARG_POSITION,position);
            fragmentNuevo.setArguments(args);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragmentNuevo);

            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();

        }

    }
}
