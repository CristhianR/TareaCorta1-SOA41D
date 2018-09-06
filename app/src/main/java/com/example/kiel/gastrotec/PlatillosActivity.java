package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kiel.gastrotec.models.Menu;
import com.example.kiel.gastrotec.models.Platillo;
import com.example.kiel.gastrotec.models.Restaurante;
import com.example.kiel.gastrotec.sqlite.MenuDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlatillosActivity extends AppCompatActivity {

    private String mRestID;
    private String mUserID;
    private String mServID;
    private String mPlatilloID;
    private String mNomPlatillo;
    private final String vacio = " ";

    private int mVotosPlatillo;
    private int mIdRest;
    private int mIdPlatillo;
    private int mHorario;

    private Bundle mBundle;

    private ListView listView;

    private ArrayList<String> mArrayPlatillos;
    private ArrayAdapter<String> mAdapter;

    MenuDB db; //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platillos);

        final Context context = getApplicationContext();

        listView = (ListView) findViewById(R.id.listViewPlatillosActivity);

        db = new MenuDB(getApplicationContext());

        mUserID = getIntent().getStringExtra("idUser");
        mRestID = getIntent().getStringExtra("idRest");
        mServID = getIntent().getStringExtra("idServ");


        List<Platillo> platillos = db.getPlatillos();
        List<Menu> menus = db.getMenus();
        final String[] arrayPlatillos = new String[platillos.size()];
        final String[] arrayMenus = new String[menus.size()];
        int count = 0;

        Toast toast1 = Toast.makeText(context,"LARGO: " + menus.size(), Toast.LENGTH_SHORT);
        toast1.show();

        for(int i = 0; i < menus.size(); i++){
            mHorario = menus.get(i).getmIdHorario();
            mIdPlatillo = menus.get(i).getmIdPlatillo();

            if(mHorario == Integer.valueOf(mServID)){
                arrayMenus[i] = String.valueOf(mIdPlatillo);
                Log.d("ArrayHorarios","ARRAY: " + arrayMenus[i]);
                count += 1;
            }else{
                arrayMenus[i] = "X";
            }
        }

        Log.d("platilloID","ARRAYLENGTH: " + arrayMenus.length);

        String[] platilloID = new String[count];
        count = 0;

        for(int i = 0; i < arrayMenus.length; i++){
            if(arrayMenus[i].toString() != "X"){
                platilloID[count] = arrayMenus[i];
                Log.d("HorariosFiltados","IDS: " + platilloID[count]);
                count += 1;
            }
        }
        count = 0;
        for(int i = 0; i < platillos.size(); i++){
            mNomPlatillo = platillos.get(i).getmNombrePlatillo();
            mVotosPlatillo = platillos.get(i).getmVotos();
            mIdRest = platillos.get(i).getmIdRestaurante();
            mIdPlatillo = platillos.get(i).getmIdPlatillo();

            if(mIdRest == Integer.valueOf(mRestID)){

                    arrayPlatillos[i] = String.valueOf(mIdPlatillo) + " " + mNomPlatillo + " "
                            + mVotosPlatillo + " " + mIdRest;
                    count += 1;
                    Log.d("PLATILLOS", String.valueOf(arrayPlatillos[i]));

            }else{
                arrayPlatillos[i] = "X";
            }
        }
        String[] platillosList = new String[count];
        count = 0;
        for(int i = 0; i < arrayPlatillos.length; i++){
            if(arrayPlatillos[i] != "X"){
                platillosList[count] = arrayPlatillos[i];
                count += 1;
            }
        }

        mArrayPlatillos = new ArrayList<>(Arrays.asList(platillosList));
        mAdapter = new ArrayAdapter<String>(this,R.layout.list_items,R.id.textItem,mArrayPlatillos);
        listView.setAdapter(mAdapter);

        mBundle = new Bundle();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(PlatillosActivity.this, PlatillosActivity.class);

                mPlatilloID = "";
                String vacio = " ";
                String info = mArrayPlatillos.get(position);
                for (int i = 0; i < info.length(); i++){
                    if(info.charAt(i) != vacio.charAt(0)){
                        mPlatilloID += info.charAt(i);
                    }else{
                        break;
                    }
                }

                mBundle.putString("idUser",mUserID);
                mBundle.putString("idPlatillo", mPlatilloID);

                intent.putExtras(mBundle);

                //startActivity(intent);
            }
        });
    }
}
