package com.example.enzo.practica_fragmentos_catalan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Asus on 24/03/2017.
 */

public class TituloFragment extends ListFragment {


    OnTituloSelectedListener mCallBack;

    public interface OnTituloSelectedListener{
        public void onTituloSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //creando adptador
        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                Contenido.Titulos
        ));
    }

    @Override
    public void onStart(){
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.fgm_parrafo) != null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            Activity activity = (Activity)context;
            mCallBack = (OnTituloSelectedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " debe de implementar el método OnTitulosSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        mCallBack.onTituloSelected(position);
        getListView().setItemChecked(position,true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_titulo,container,false);
    }

}
