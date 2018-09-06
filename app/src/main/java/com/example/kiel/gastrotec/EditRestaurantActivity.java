package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiel.gastrotec.models.HorarioPlatillo;
import com.example.kiel.gastrotec.models.Menu;
import com.example.kiel.gastrotec.models.Platillo;
import com.example.kiel.gastrotec.models.Restaurante;
import com.example.kiel.gastrotec.sqlite.MenuDB;

import java.util.ArrayList;
import java.util.List;

public class EditRestaurantActivity extends AppCompatActivity {

    private String mIdRest;
    private String mIdUser;

    private EditText mNomRest;
    private EditText mLocRest;
    private EditText mHorRest;
    private EditText mNomHorario;
    private EditText mHorarioServicio;
    private EditText mNomPlatillo;
    private EditText mHorPlatillo;

    private TextView mNomRestView;
    private TextView mLocRestView;
    private TextView mHorRestView;

    private String mNomRestValue;
    private String mLocRestValue;
    private String mHorRestValue;

    private Button mAccept;

    private Bundle mBundle;

    MenuDB db; //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);

        final Context context = getApplicationContext();

        mNomRest = (EditText) findViewById(R.id.editTextRestNameEditRestaurantAcitvity);
        mLocRest = (EditText) findViewById(R.id.editTextRestLocEditRestaurantAcitvity);
        mHorRest = (EditText) findViewById(R.id.editTextRestHorServEditRestaurantAcitvity);
        mNomHorario = (EditText) findViewById(R.id.editTextNameMenuEditRestaurantAcitvity);
        mHorarioServicio = (EditText) findViewById(R.id.editTextHorMenuEditRestaurantAcitvity);
        mNomPlatillo = (EditText) findViewById(R.id.editTextNamePlatilloEditRestaurantActivity);
        mHorPlatillo = (EditText) findViewById(R.id.editTextHorPlatilloEditRestaurantActivity);


        mNomRestView = (TextView) findViewById(R.id.textViewRestNameEditRestaurantAcitvity);
        mLocRestView = (TextView) findViewById(R.id.textViewRestLocEditRestaurantAcitvity);
        mHorRestView = (TextView) findViewById(R.id.textViewRestHorServEditRestaurantAcitvity);

        mAccept = (Button) findViewById(R.id.buttonAcceptEditRestaurantActivity);

        mIdRest = getIntent().getStringExtra("idRest");
        mIdUser = getIntent().getStringExtra("idUser");

        Log.d("IIIIIIIDDDDD",mIdRest);

        db = new MenuDB(getApplicationContext());

        final Restaurante restaurante = db.getRestaurante(0);

        mNomRestView.setText(restaurante.getmNomRest());
        mLocRestView.setText(restaurante.getmLocRest());
        mHorRestView.setText(restaurante.getmHorarioRest());

        mBundle = new Bundle();

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mNomRest.getText().toString().length() > 0){
                    restaurante.setmNomRest(mNomRest.getText().toString());
                }else {
                    restaurante.setmNomRest(restaurante.getmNomRest().toString());
                }

                if(mLocRest.getText().toString().length() > 0){
                    restaurante.setmLocRest(mLocRest.getText().toString());
                }else{
                    restaurante.setmLocRest(restaurante.getmLocRest().toString());
                }

                if(mHorRest.getText().toString().length() > 0){
                    restaurante.setmHorarioRest(mHorRest.getText().toString());
                }else{
                    restaurante.setmHorarioRest(restaurante.getmHorarioRest().toString());
                }

                long restID = db.updateRestaurante(restaurante);

                Toast toast1 = Toast.makeText(context,"Restaurante Actualizado", Toast.LENGTH_SHORT);
                toast1.show();

                if(mNomHorario.getText().toString().length() > 0
                        && mHorarioServicio.getText().toString().length() > 0){

                    HorarioPlatillo horarioPlatillo = new HorarioPlatillo(db.getHorariosPlatillos().size(),
                            mNomHorario.getText().toString(), mHorarioServicio.getText().toString(),Integer.valueOf(mIdRest));

                    long idHorario = db.createHorarioPlatillo(horarioPlatillo);

                    Toast toast2 = Toast.makeText(context,"Horario Agregado", Toast.LENGTH_SHORT);
                    toast2.show();

                }else{
                    //TOAST
                }

                if(mNomPlatillo.getText().toString().length() > 0
                        && mHorPlatillo.getText().toString().length() > 0){

                    Platillo platillo = new Platillo(db.getPlatillos().size()+1, mNomPlatillo.getText().toString(), 0 ,Integer.valueOf(mIdRest));
                    long idPlatillo = db.createPlatillo(platillo);

                    List<HorarioPlatillo> horarioPlatillos = db.getHorariosPlatillos();

                    for(int i = 0; i < horarioPlatillos.size(); i++){
                        int idHorarioComida = horarioPlatillos.get(i).getmIdHorarioComida();
                        int idRestaurante = horarioPlatillos.get(i).getmIdRestaurante();
                        String nombreHorario = horarioPlatillos.get(i).getmNombreHorario();

                        if(idRestaurante == Integer.valueOf(mIdRest)
                                && nombreHorario.equals(mHorPlatillo)){

                            Menu menu = new Menu(db.getMenus().size(),idHorarioComida,platillo.getmIdPlatillo());
                            long idMenu = db.createMenu(menu);

                            Toast toast3 = Toast.makeText(context,"Menu Creado", Toast.LENGTH_SHORT);
                            toast3.show();
                        }
                    }
                }
                Intent intent = new Intent(EditRestaurantActivity.this, MainPageAdminActivity.class);

                mBundle.putString("idUser",mIdUser);
                mBundle.putString("idRest",mIdRest);

                intent.putExtras(mBundle);

                db.closeDB(); // Cerrando la conexión a la base de datos.

                startActivity(intent);
            }
        });

        db.closeDB(); // Cerrando la conexión a la base de datos.
    }
}
