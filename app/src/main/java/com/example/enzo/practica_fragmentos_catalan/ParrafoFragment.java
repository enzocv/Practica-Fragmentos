package com.example.enzo.practica_fragmentos_catalan;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;

/**
 * Created by Asus on 24/03/2017.
 */

public class ParrafoFragment extends Fragment {

    final static String ARG_POSITION = "positon";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        if (savedInstanceState != null){
            //regresando de un estado previo
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        return inflater.inflate(R.layout.fragment_parrafo,container,false);
    }

    public void updateParrafoView(int position){
        TextView parrafo = (TextView)getActivity().findViewById(R.id.txt_fragmento);

        parrafo.setText(Contenido.Parrafos[position]);

        mCurrentPosition = position;
    }

    @Override
    public void onStart(){
        super.onStart();

        //verificar si existen argumentos

        Bundle args = getArguments();
        if (args != null){
            updateParrafoView(args.getInt(ARG_POSITION));
        }
        else if (mCurrentPosition != -1){
            //vengo de una posicion anterior
            updateParrafoView(mCurrentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        //para que los datos se guarden (onPause | onStop)
        //almacena el mCurrentPosition en ARG_POSITION (regresando de onPause, onStop)
        outState.putInt(ARG_POSITION,mCurrentPosition);
    }
}
