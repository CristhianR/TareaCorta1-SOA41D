package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiel.gastrotec.models.Restaurante;
import com.example.kiel.gastrotec.sqlite.MenuDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {

    private TextView mUserName;

    private Button mEdit;
    private Button mLogOut;

    private String mUserID;
    private String mRestID;

    private Bundle mBundle;

    private ArrayList<String> mArrayRestaurants;
    private ArrayAdapter<String> mAdapter;

    MenuDB db; //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pag);

        final Context context = getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.listViewRestaurantMainPageActivity);

        mUserName = (TextView) findViewById(R.id.textView);

        mEdit = (Button) findViewById(R.id.button);
        mLogOut = (Button) findViewById(R.id.button4);

        db = new MenuDB(getApplicationContext());

        mBundle = new Bundle();

        mUserID = getIntent().getStringExtra("idUser");

        Toast toast1 = Toast.makeText(context,mUserID, Toast.LENGTH_SHORT);
        toast1.show();

        String name = db.getCliente(Integer.valueOf(mUserID)).getmNomCliente();
        String ap1 = db.getCliente(Integer.valueOf(mUserID)).getmApe1();
        String ap2 = db.getCliente(Integer.valueOf(mUserID)).getmApe2();

        mUserName.setText("Nombre: " + name + " Apellidos: " + ap1 + " " + ap2);

        List<Restaurante> restaurantes = db.getRestaurantes();
        final String[] restarurante = new String[restaurantes.size()];

        for(int i = 0; i < restaurantes.size(); i++){
            int idRest = restaurantes.get(i).getmIdRest();
            String nomRest = restaurantes.get(i).getmNomRest();
            String locRest = restaurantes.get(i).getmLocRest();
            String horRest = restaurantes.get(i).getmHorarioRest();
            restarurante[i] = String.valueOf(idRest) + " " + nomRest + " " + locRest + " " + horRest;
        }

        mArrayRestaurants = new ArrayList<>(Arrays.asList(restarurante));
        mAdapter = new ArrayAdapter<String>(this,R.layout.list_items,R.id.textItem,mArrayRestaurants);
        listView.setAdapter(mAdapter);

        mUserName.setText("Nombre: " + name + " Apellidos: " + ap1 + " " + ap2);

        mBundle = new Bundle();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(MainPageActivity.this, ScheduleActivity.class);

                mRestID = "";
                String vacio = " ";
                String info = mArrayRestaurants.get(position);

                for (int i = 0; i < info.length(); i++){
                    if(info.charAt(i) != vacio.charAt(0)){
                        mRestID += info.charAt(i);
                    }else{
                        break;
                    }
                }

                mBundle.putString("idUser",mUserID);
                mBundle.putString("idRest", mRestID);

                intent.putExtras(mBundle);

                Toast toast1 = Toast.makeText(context,mRestID + " " + mUserID, Toast.LENGTH_SHORT);
                toast1.show();

                startActivity(intent);
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, EditProfileActivity.class);

                mBundle.putString("idUser",mUserID);

                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        db.closeDB(); // Cerrando la conexión a la base de datos.

    }
}
