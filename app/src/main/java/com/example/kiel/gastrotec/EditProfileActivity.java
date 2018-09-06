package com.example.kiel.gastrotec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kiel.gastrotec.models.Clientes;
import com.example.kiel.gastrotec.sqlite.MenuDB;

public class EditProfileActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mUserApe1;
    private EditText mUserApe2;
    private EditText mUserCourse;
    private EditText mUserPassword;

    private TextView mUserNameTV;
    private TextView mUserCourseTV;
    private TextView mUserID;
    private TextView mUserEmail;
    private TextView mUserPasswordTV;

    private Button mAccept;
    private Button mCancel;

    private String mUserId;

    private Bundle mBundle;

    MenuDB db; //Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final Context context = getApplicationContext();

        mUserName = (EditText) findViewById(R.id.editTextUserNameEditActivity);
        mUserApe1 = (EditText) findViewById(R.id.editTextUserApe1EditActivity);
        mUserApe2 = (EditText) findViewById(R.id.editTextUserApe2EditActivity);
        mUserCourse = (EditText) findViewById(R.id.editTextCourseEditActivity);
        mUserPassword = (EditText) findViewById(R.id.editTextPasswordEditActivity);

        mUserNameTV = (TextView) findViewById(R.id.textViewUserNameEditActivity);
        mUserCourseTV = (TextView) findViewById(R.id.textViewCourseEditActivity);
        mUserEmail = (TextView) findViewById(R.id.textViewEmailValorEditActivity);
        mUserID = (TextView) findViewById(R.id.textViewIDValorEditActivity);
        mUserPasswordTV = (TextView) findViewById(R.id.textViewPasswordEditActivity);

        mAccept = (Button) findViewById(R.id.buttonAcceptEditActivity);
        mCancel = (Button) findViewById(R.id.buttonCancelEditActivity);

        db = new MenuDB(getApplicationContext());

        mUserId = getIntent().getStringExtra("idUser");

        final Clientes cliente  = db.getCliente(Integer.valueOf(mUserId));

        mUserNameTV.setText("Nombre: " + cliente.getmNomCliente()
        + " Apellido: " + cliente.getmApe1() + " Apellido: " + cliente.getmApe2());
        mUserCourseTV.setText("Carrera: " + cliente.getmCarrera());
        mUserEmail.setText("Email: " + cliente.getmEmail());
        mUserID.setText("Carne: " + mUserId.toString());
        mUserPasswordTV.setText("Clave: " + cliente.getmPassword());

        mBundle = new Bundle();

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mUserName.getText().toString().length() > 0){
                    cliente.setmNomCliente(mUserName.getText().toString());
                }else {
                    cliente.setmNomCliente(cliente.getmNomCliente().toString());
                }

                if(mUserApe1.getText().toString().length() > 0){
                    cliente.setmApe1(mUserApe1.getText().toString());
                }else{
                    cliente.setmApe1(cliente.getmApe1().toString());
                }

                if(mUserApe2.getText().toString().length() > 0){
                    cliente.setmApe2(mUserApe2.getText().toString());
                }else{
                    cliente.setmApe2(cliente.getmApe2().toString());
                }

                if(mUserPassword.getText().toString().length() > 0){
                    cliente.setmPassword(mUserPassword.getText().toString());
                }else{
                    cliente.setmPassword(cliente.getmPassword().toString());
                }

                if(mUserCourse.getText().toString().length() > 0){
                    cliente.setmCarrera(mUserCourse.getText().toString());
                }else{
                    cliente.setmCarrera(cliente.getmCarrera().toString());
                }

                String array = "[" + cliente.getmIdCliente() + ", " + cliente.getmNomCliente() + ", " + cliente.getmApe1() + ", " + cliente.getmApe2() + ", " + cliente.getmCarrera() + ", " + cliente.getmEmail() + ", " + cliente.getmPassword() + "] ";
                Log.d("ValoresREGISTER", "Cliente: " + array);

                long clientID = db.updateCliente(cliente);

                if(mUserId.charAt(0) == '3'){
                    Intent intent = new Intent(EditProfileActivity.this, MainPageAdminActivity.class);

                    mBundle.putString("idUser",mUserId);
                    intent.putExtras(mBundle);

                    startActivity(intent);

                }else{
                    Intent intent = new Intent(EditProfileActivity.this, MainPageActivity.class);

                    mBundle.putString("idUser",mUserId);
                    intent.putExtras(mBundle);

                    startActivity(intent);
                }

                db.closeDB(); // Cerrando la conexión a la base de datos.
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserId.charAt(0) == '3'){
                    Intent intent = new Intent(EditProfileActivity.this, MainPageAdminActivity.class);

                    mBundle.putString("idUser",mUserId);
                    intent.putExtras(mBundle);

                    startActivity(intent);
                }else{
                    Intent intent = new Intent(EditProfileActivity.this, MainPageActivity.class);

                    mBundle.putString("idUser",mUserId);
                    intent.putExtras(mBundle);

                    startActivity(intent);
                }
            }
        });

        db.closeDB(); // Cerrando la conexión a la base de datos.
    }
}
