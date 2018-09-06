package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kiel.gastrotec.models.HorarioPlatillo;
import com.example.kiel.gastrotec.sqlite.MenuDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private TextView mRestNameView;
    private TextView mRestLocView;
    private TextView mRestHorView;

    private String mUserID;
    private String mRestID;
    private String mServID;

    private String mRestName;
    private String mRestLoc;
    private String mRestHor;

    private Bundle mBundle;

    private ArrayList<String> mArraySchedule;
    private ArrayAdapter<String> mAdapter;

    MenuDB db; //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Context context = getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.listViewHorariosMenuActivity);

        mRestNameView = (TextView) findViewById(R.id.textViewRestNameMenuActivity);
        mRestLocView = (TextView) findViewById(R.id.textViewRestLocMenuActivity);
        mRestHorView = (TextView) findViewById(R.id.textViewRestHorarioMenuActivity);

        db = new MenuDB(getApplicationContext());

        mUserID = getIntent().getStringExtra("idUser");
        mRestID = getIntent().getStringExtra("idRest");

        mRestName = db.getRestaurante(Integer.valueOf(mRestID)).getmNomRest();
        mRestLoc = db.getRestaurante(Integer.valueOf(mRestID)).getmLocRest();
        mRestHor = db.getRestaurante(Integer.valueOf(mRestID)).getmHorarioRest();

        mRestNameView.setText("Nombre Restaurante: " + mRestName);
        mRestLocView.setText("Direccion Restaurante: " + mRestLoc);
        mRestHorView.setText("Horario Servicio: " + mRestHor);

        List<HorarioPlatillo> horarioPlatillos = db.getHorariosPlatillos();
        final String[] horarios = new String[horarioPlatillos.size()];
        int count = 0;

        for(int i = 0; i < horarioPlatillos.size(); i++){

            int idHorarioPlatillo = horarioPlatillos.get(i).getmIdHorarioComida();
            int idRestSelect = horarioPlatillos.get(i).getmIdRestaurante();
            String nomHorarioComida = horarioPlatillos.get(i).getmNombreHorario();
            String horarioHorarioComida = horarioPlatillos.get(i).getmHorarioComida();

            if(idRestSelect == Integer.valueOf(mRestID)) {
                horarios[i] = String.valueOf(idHorarioPlatillo) + " "
                        + nomHorarioComida + " " + horarioHorarioComida + " "
                        + String.valueOf(idRestSelect);
                count += 1;
            }else{
                horarios[i] = "";
            }
        }

        String[] schedules = new String[count];
        count = 0;
        for(int i = 0; i < horarios.length; i++){
            if(horarios[i] != ""){
                schedules[count] = horarios[i];
                count += 1;
            }
        }

        mArraySchedule = new ArrayList<>(Arrays.asList(schedules));
        mAdapter = new ArrayAdapter<String>(this,R.layout.list_items,R.id.textItem,mArraySchedule);
        listView.setAdapter(mAdapter);

        mBundle = new Bundle();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(ScheduleActivity.this, PlatillosActivity.class);

                mServID = "";
                String vacio = " ";
                String info = mArraySchedule.get(position);
                for (int i = 0; i < info.length(); i++){
                    if(info.charAt(i) != vacio.charAt(0)){
                        mServID += info.charAt(i);
                    }else{
                        break;
                    }
                }

                mBundle.putString("idUser",mUserID);
                mBundle.putString("idRest", mRestID);
                mBundle.putString("idServ", mServID);

                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });
    }
}
