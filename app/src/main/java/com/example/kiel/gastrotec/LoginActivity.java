package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kiel.gastrotec.sqlite.MenuDB;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;

    private Button mEnter;
    private Button mRegister;

    private Bundle mBundle;

    MenuDB db; //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplicationContext();

        mUsername = (EditText) findViewById(R.id.editTextUsernameLoginActivity);
        mPassword = (EditText) findViewById(R.id.editTextPasswordLoginActivity);

        mEnter = (Button) findViewById(R.id.buttonEnterLoginActivity);
        mRegister = (Button) findViewById(R.id.buttonRegisterLoginActivity);

        mBundle = new Bundle();

        db = new MenuDB(getApplicationContext());

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = mUsername.getText().toString();
                String pw = mPassword.getText().toString();
                long id = Integer.valueOf(ID);

                String clave  = db.getCliente(id).getmPassword();

                if(clave.equals(pw)){

                    if(mUsername.getText().charAt(0) == '3'){
                        Intent intent = new Intent(LoginActivity.this, MainPageAdminActivity.class);

                        mBundle.putString("idUser",ID);

                        intent.putExtras(mBundle);

                        startActivity(intent);

                    }else {
                        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);

                        mBundle.putString("idUser",ID);

                        intent.putExtras(mBundle);

                        startActivity(intent);
                    }
                }else{
                    Toast toast1 = Toast.makeText(context,"Clave o usuario incorrecto: " , Toast.LENGTH_SHORT);
                    toast1.show();
                }
                db.closeDB(); // Cerrando la conexión a la base de datos.
            }
        });

        db.closeDB(); // Cerrando la conexión a la base de datos.
    }

}
