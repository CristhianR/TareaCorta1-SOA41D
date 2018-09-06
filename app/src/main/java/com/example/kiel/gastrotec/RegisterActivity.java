package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kiel.gastrotec.models.Clientes;
import com.example.kiel.gastrotec.sqlite.MenuDB;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUserName;
    private EditText mUserApe1;
    private EditText mUserApe2;
    private EditText mUserCourse;
    private EditText mUserID;
    private EditText mUserEmail;
    private EditText mUserPassword;


    private Button mAccept;
    private Button mCancel;

    private Bundle mBundle;

    MenuDB db; //Base de datos



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Context context = getApplicationContext();

        mUserName = (EditText) findViewById(R.id.editTextNameRegisterActivity);
        mUserApe1 = (EditText) findViewById(R.id.editTextApe1RegisterActivity);
        mUserApe2 = (EditText) findViewById(R.id.editTextApe2RegisterActivity);
        mUserCourse = (EditText) findViewById(R.id.editTextCourseRegisterActivity);
        mUserEmail = (EditText) findViewById(R.id.editTextEmailRegisterActivity);
        mUserID = (EditText) findViewById(R.id.editTextIDRegisterActivity);
        mUserPassword = (EditText) findViewById(R.id.editTextPasswordRegisterActivity);

        mAccept = (Button) findViewById(R.id.buttonAcceptRegisterActivity);
        mCancel = (Button) findViewById(R.id.buttonCancelRegisterActivity);

        db = new MenuDB(getApplicationContext());

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clientes cliente = new Clientes(Integer.valueOf(mUserID.getText().toString()),mUserName.getText().toString(),
                        mUserApe1.getText().toString(), mUserApe2.getText().toString(),
                        mUserCourse.getText().toString(), mUserEmail.getText().toString(),
                        mUserPassword.getText().toString());

                String array = "[" + cliente.getmIdCliente() + ", " + cliente.getmNomCliente() + ", " + cliente.getmApe1() + ", " + cliente.getmApe2() + ", " + cliente.getmCarrera() + ", " + cliente.getmEmail() + ", " + cliente.getmPassword() + "] ";
                Log.d("ValoresREGISTER", "Cliente: " + array);

                long clientID = db.createCliente(cliente);
                //String clave  = db.getCliente(cliente.getmIdCliente()).getmPassword();
                Toast toast1 = Toast.makeText(context,"¡Registrado!" + cliente.getmNomCliente(), Toast.LENGTH_SHORT);
                toast1.show();

                db.closeDB(); // Cerrando la conexión a la base de datos.

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                db.closeDB(); // Cerrando la conexión a la base de datos.

                startActivity(intent);
            }
        });

        db.closeDB(); // Cerrando la conexión a la base de datos.

    }
}
