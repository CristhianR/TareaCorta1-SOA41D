package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kiel.gastrotec.models.Restaurante;
import com.example.kiel.gastrotec.sqlite.MenuDB;

public class RegisterRestaurantActivity extends AppCompatActivity {

    private EditText mNameRestaurant;
    private EditText mLocRestaurant;
    private EditText mHorRestaurant;

    private String mUserID;

    private Button mAccept;
    private Button mCancel;

    MenuDB db; //Base de datos

    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);

        final Context context = getApplicationContext();

        mNameRestaurant = (EditText) findViewById(R.id.editTextRestaurantNameRegisterRestaurantActivity);
        mLocRestaurant = (EditText) findViewById(R.id.editTextRestaurantLocationRegisterRestaurantActivity);
        mHorRestaurant = (EditText) findViewById(R.id.editTextRestaurantHorarioRegisterRestaurantActivity);

        mAccept = (Button) findViewById(R.id.buttonAcceptRegisterRestaurantActivity);
        mCancel = (Button) findViewById(R.id.buttonCancelRegisterRestaurantActivity);

        db = new MenuDB(getApplicationContext());

        mUserID = getIntent().getStringExtra("idUser");

        mBundle = new Bundle();

        mBundle.putString("idUser",mUserID);

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Restaurante restaurante = new Restaurante(db.getRestaurantes().size(), mNameRestaurant.getText().toString(),
                        mLocRestaurant.getText().toString(), mHorRestaurant.getText().toString());

                long restaurantID = db.createRestaurante(restaurante);

                Toast toast1 = Toast.makeText(context,"¡Restaurante Registrado!" + restaurante.getmNomRest(), Toast.LENGTH_SHORT);
                toast1.show();

                db.closeDB(); // Cerrando la conexión a la base de datos.

                Intent intent = new Intent(RegisterRestaurantActivity.this, MainPageAdminActivity.class);

                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterRestaurantActivity.this, MainPageAdminActivity.class);

                intent.putExtras(mBundle);

                db.closeDB(); // Cerrando la conexión a la base de datos.

                startActivity(intent);
            }
        });

        db.closeDB(); // Cerrando la conexión a la base de datos.
    }
}
