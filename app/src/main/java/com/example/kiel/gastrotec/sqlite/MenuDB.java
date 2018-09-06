package com.example.kiel.gastrotec.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kiel.gastrotec.models.Clientes;
import com.example.kiel.gastrotec.models.HorarioPlatillo;
import com.example.kiel.gastrotec.models.Menu;
import com.example.kiel.gastrotec.models.Platillo;
import com.example.kiel.gastrotec.models.Restaurante;
import com.example.kiel.gastrotec.models.Voto;

import java.util.ArrayList;
import java.util.List;

public class MenuDB extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = MenuDB.class.getName();

    // Versión base de datos
    private static final int DATABASE_VERSION = 1;

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "menu";

    // Nombre de las Tablas
    private static final String TABLE_CLIENTES = "clientes";
    private static final String TABLE_RESTAURANTES = "restaurante";
    private static final String TABLE_PLATILLOS = "platillos";
    private static final String TABLE_HORARIOS = "horarios";
    private static final String TABLE_MENUS = "menus";
    private static final String TABLE_VOTOS = "votos";

    // Columnas de la tabla CLIENTES
    private static final String COLUMN_CLIENTE_ID = "clienteID";
    private static final String COLUMN_CLIENTE_NOM = "clienteNom";
    private static final String COLUMN_CLIENTE_APE1 = "clienteApe1";
    private static final String COLUMN_CLIENTE_APE2 = "clienteApe2";
    private static final String COLUMN_CLIENTE_CARRERA = "clienteCarrera";
    private static final String COLUMN_CLIENTE_EMAIL = "clienteEmail";
    private static final String COLUMN_CLIENTE_PASSWORD = "clientePassword";

    // Columnas de la tabla RESTAURANTES
    private static final String COLUMN_RESTAURANTE_ID = "restID";
    private static final String COLUMN_RESTAURANTE_NOM = "restNom";
    private static final String COLUMN_RESTAURANTE_LOC = "restLoc";
    private static final String COLUMN_RESTAURANTE_HORARIO = "restHorario";

    // Columnas de las tabla PLATILLOS
    private static final String COLUMN_PLATILLO_ID = "platilloID";
    private static final String COLUMN_PLATILLO_NOM = "platilloNom";
    private static final String COLUMN_PLATILLO_VOTOS = "platilloVotos";
    private static final String COLUMN_PLATILLO_ID_REST = "platilloIdRest";

    // Columnas de las tabla HORARIOS
    private static final String COLUMN_HORARIO_ID = "horarioID";
    private static final String COLUMN_HORARIO_NOM = "horarioNom";
    private static final String COLUMN_HORARIO_HOR = "horarioHor";
    private static final String COLUMN_HORARIO_ID_REST = "horarioIdRest";

    // Columnas de las tabla MENUS
    private static final String COLUMN_MENU_ID = "menuID";
    private static final String COLUMN_MENU_ID_HORARIO = "menuIdHorario";
    private static final String COLUMN_MENU_ID_PLATILLO = "menuIdPlatillo";

    // Columnas de las tabla VOTOS
    private static final String COLUMN_VOTO_ID = "votoID";
    private static final String COLUMN_VOTO_ID_CLIENTE = "votoIdCliente";
    private static final String COLUMN_VOTO_ID_PLATILLO = "votoIdPlatillo";
    private static final String COLUMN_VOTO_VALOR = "votoValor";

    private int mIdRestGen = -1;
    private int mIdPlatGen = -1;
    private int mIdHorGen = -1;
    private int mIdMenuGen = -1;
    private int mIdVotoGen = -1;

    // <------------------------------------- Creación de Tablas ------------------------------------->
    // Creación de la tabla CLIENTES
    private static final String CREATE_TABLE_CLIENTES = "CREATE TABLE " + TABLE_CLIENTES + "("
            + COLUMN_CLIENTE_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + COLUMN_CLIENTE_NOM + " TEXT NOT NULL, "
            + COLUMN_CLIENTE_APE1 + " TEXT NOT NULL, "
            + COLUMN_CLIENTE_APE2 + " TEXT NOT NULL, "
            + COLUMN_CLIENTE_CARRERA + " TEXT NOT NULL, "
            + COLUMN_CLIENTE_EMAIL + " TEXT NOT NULL, "
            + COLUMN_CLIENTE_PASSWORD + " TEXT NOT NULL"
            + ");";

    // Creación de la tabla RESTAURANTES
    private static final String CREATE_TABLE_RESTAURANTES = "CREATE TABLE " + TABLE_RESTAURANTES + "("
            + COLUMN_RESTAURANTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_RESTAURANTE_NOM + " TEXT NOT NULL, "
            + COLUMN_RESTAURANTE_LOC + " TEXT NOT NULL, "
            + COLUMN_RESTAURANTE_HORARIO + " TEXT NOT NULL"
            + ");";

    // Creación de la tabla PLATILLOS
    private static final String CREATE_TABLE_PLATILLOS = "CREATE TABLE " + TABLE_PLATILLOS + "("
            + COLUMN_PLATILLO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PLATILLO_NOM + " TEXT NOT NULL, "
            + COLUMN_PLATILLO_VOTOS + " INTEGER, "
            + COLUMN_PLATILLO_ID_REST + " INTEGER NOT NULL"
            + ");";

    // Creación de la tabla HORARIOS
    private static final String CREATE_TABLE_HORARIOS = "CREATE TABLE " + TABLE_HORARIOS + "("
            + COLUMN_HORARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_HORARIO_NOM + " TEXT NOT NULL, "
            + COLUMN_HORARIO_HOR + " DATETIME NOT NULL, "
            + COLUMN_HORARIO_ID_REST + " INTEGER NOT NULL"
            + ");";

    // Creación de la tabla MENUS
    private static final String CREATE_TABLE_MENUS = "CREATE TABLE " + TABLE_MENUS + "("
            + COLUMN_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MENU_ID_HORARIO + " INTEGER NOT NULL, "
            + COLUMN_MENU_ID_PLATILLO + " INTEGER NOT NULL"
            + ");";

    // Creación de la tabla VOTOS
    private static final String CREATE_TABLE_VOTOS = "CREATE TABLE " + TABLE_VOTOS + "("
            + COLUMN_VOTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_VOTO_ID_CLIENTE + " INTEGER NOT NULL, "
            + COLUMN_VOTO_ID_PLATILLO + " INTEGER NOT NULL, "
            + COLUMN_VOTO_VALOR + " INTEGER"
            + ");";


    // Constructor de la base de datos
    public MenuDB(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Creando las tablas requeridas
        db.execSQL(CREATE_TABLE_CLIENTES);
        db.execSQL(CREATE_TABLE_RESTAURANTES);
        db.execSQL(CREATE_TABLE_PLATILLOS);
        db.execSQL(CREATE_TABLE_HORARIOS);
        db.execSQL(CREATE_TABLE_MENUS);
        db.execSQL(CREATE_TABLE_VOTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // En la actualización, elimina las tablas viejas
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLATILLOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HORARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTOS);

        // Crea las nuevas tablas
        onCreate(db);
    }

    // <------------------------------------- Creación de los métodos CRUD ------------------------------------->

    // <------------------------------------- Métodos del Cliente ------------------------------------->
    /**
     * Método para crea clientes en la base de datos.
     * @param cliente, objeto de la clase Clientes.
     * @return clienteID, el idetificador único del archivo.
     **/
    public long createCliente(Clientes cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTE_ID, cliente.getmIdCliente());
        values.put(COLUMN_CLIENTE_NOM, cliente.getmNomCliente());
        values.put(COLUMN_CLIENTE_APE1, cliente.getmApe1());
        values.put(COLUMN_CLIENTE_APE2, cliente.getmApe2());
        values.put(COLUMN_CLIENTE_CARRERA, cliente.getmCarrera());
        values.put(COLUMN_CLIENTE_EMAIL, cliente.getmEmail());
        values.put(COLUMN_CLIENTE_PASSWORD, cliente.getmPassword());

        // Insertando fila
        long clienteID = db.insert(TABLE_CLIENTES, null, values);

        return clienteID;
    }

    /**
     * Método para obtener un cliente de la base de datos.
     * @param clienteID, identificador único del archivo.
     * @return cliente, objeto de la clase Clientes
     **/
    public Clientes getCliente(long clienteID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTES + " WHERE " + COLUMN_CLIENTE_ID + " = " + clienteID;
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        if (c != null) {
            c.moveToFirst();
        }

        Clientes cliente = new Clientes();
        cliente.setmIdCliente(c.getInt(c.getColumnIndex(COLUMN_CLIENTE_ID)));
        cliente.setmNomCliente(c.getString(c.getColumnIndex(COLUMN_CLIENTE_NOM)));
        cliente.setmApe1(c.getString(c.getColumnIndex(COLUMN_CLIENTE_APE1)));
        cliente.setmApe2(c.getString(c.getColumnIndex(COLUMN_CLIENTE_APE2)));
        cliente.setmCarrera(c.getString(c.getColumnIndex(COLUMN_CLIENTE_CARRERA)));
        cliente.setmEmail(c.getString(c.getColumnIndex(COLUMN_CLIENTE_EMAIL)));
        cliente.setmPassword(c.getString(c.getColumnIndex(COLUMN_CLIENTE_PASSWORD)));

        return cliente;
    }

    /**
     * Método para obtener todos los clientes de la base de datos.
     * @return clientes, lista de objetos de la clase Clientes
     **/
    public List<Clientes> getClientes() {
        List<Clientes> clientes = new ArrayList<Clientes>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        // Loop para obtener todos los clientes
        if (c.moveToFirst()) {
            do {
                Clientes cliente = new Clientes();
                cliente.setmIdCliente(c.getInt(c.getColumnIndex(COLUMN_CLIENTE_ID)));
                cliente.setmNomCliente(c.getString(c.getColumnIndex(COLUMN_CLIENTE_NOM)));
                cliente.setmApe1(c.getString(c.getColumnIndex(COLUMN_CLIENTE_APE1)));
                cliente.setmApe2(c.getString(c.getColumnIndex(COLUMN_CLIENTE_APE2)));
                cliente.setmCarrera(c.getString(c.getColumnIndex(COLUMN_CLIENTE_CARRERA)));
                cliente.setmEmail(c.getString(c.getColumnIndex(COLUMN_CLIENTE_EMAIL)));
                cliente.setmPassword(c.getString(c.getColumnIndex(COLUMN_CLIENTE_PASSWORD)));

                clientes.add(cliente); // Añadiendo al array

            } while (c.moveToNext());
        }

        return clientes;
    }

    /**
     * Método para actualizar un cliente de la base de datos.
     * @return un identificador único.
     **/
    public int updateCliente(Clientes cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTE_ID, cliente.getmIdCliente());
        values.put(COLUMN_CLIENTE_NOM, cliente.getmNomCliente());
        values.put(COLUMN_CLIENTE_APE1, cliente.getmApe1());
        values.put(COLUMN_CLIENTE_APE2, cliente.getmApe2());
        values.put(COLUMN_CLIENTE_CARRERA, cliente.getmCarrera());
        values.put(COLUMN_CLIENTE_EMAIL, cliente.getmEmail());
        values.put(COLUMN_CLIENTE_PASSWORD, cliente.getmPassword());

        // updating row
        return db.update(TABLE_CLIENTES, values, COLUMN_CLIENTE_ID + " = ?",
                new String[] { String.valueOf(cliente.getmIdCliente()) });
    }

    /**
     * Método para eliminar un cliente de la base de datos.
     * @param clienteID un identificador único.
     **/
    public void deleteCliente(long clienteID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENTES, COLUMN_CLIENTE_ID + " = ?",
                new String[] { String.valueOf(clienteID) });
    }

    // <------------------------------------- Métodos del Restaurante ------------------------------------->
    /**
     * Método para crea restaurante en la base de datos.
     * @param restaurante, objeto de la clase Restaurante.
     * @return restauranteID, el idetificador único del archivo.
     **/
    public long createRestaurante(Restaurante restaurante) {
        SQLiteDatabase db = this.getWritableDatabase();
        //restaurante.setmIdRest(idGenerator(0));

        ContentValues values = new ContentValues();
        values.put(COLUMN_RESTAURANTE_ID, restaurante.getmIdRest());
        values.put(COLUMN_RESTAURANTE_NOM, restaurante.getmNomRest());
        values.put(COLUMN_RESTAURANTE_LOC, restaurante.getmLocRest());
        values.put(COLUMN_RESTAURANTE_HORARIO, restaurante.getmHorarioRest());

        long restauranteID = db.insert(TABLE_RESTAURANTES, null, values); // Insertando fila

        return restauranteID;
    }

    /**
     * Método para obtener un restaurante de la base de datos.
     * @param restauranteID, identificador único del archivo.
     * @return restaurante, objeto de la clase Restaurante
     **/
    public Restaurante getRestaurante(long restauranteID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANTES + " WHERE " + COLUMN_RESTAURANTE_ID + " = " + restauranteID;
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        if (c != null) {
            c.moveToFirst();
        }

        Restaurante restaurante = new Restaurante();
        restaurante.setmIdRest(c.getInt(c.getColumnIndex(COLUMN_RESTAURANTE_ID)));
        restaurante.setmNomRest(c.getString(c.getColumnIndex(COLUMN_RESTAURANTE_NOM)));
        restaurante.setmLocRest(c.getString(c.getColumnIndex(COLUMN_RESTAURANTE_LOC)));
        restaurante.setmHorarioRest(c.getString(c.getColumnIndex(COLUMN_RESTAURANTE_HORARIO)));

        return restaurante;
    }

    /**
     * Método para obtener todos los restaurantes de la base de datos.
     * @return restaurante, lista de objetos de la clase Restaurante
     **/
    public List<Restaurante> getRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<Restaurante>();
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        // Loop para obtener todos los clientes
        if (c.moveToFirst()) {
            do {
                Restaurante restaurante = new Restaurante();
                restaurante.setmIdRest(c.getInt(c.getColumnIndex(COLUMN_RESTAURANTE_ID)));
                restaurante.setmNomRest(c.getString(c.getColumnIndex(COLUMN_RESTAURANTE_NOM)));
                restaurante.setmLocRest(c.getString(c.getColumnIndex(COLUMN_RESTAURANTE_LOC)));
                restaurante.setmHorarioRest(c.getString(c.getColumnIndex(COLUMN_RESTAURANTE_HORARIO)));

                restaurantes.add(restaurante); // Añadiendo al array

            } while (c.moveToNext());
        }
        return restaurantes;
    }

    /**
     * Método para actualizar un restaurante de la base de datos.
     * @return un identificador único.
     **/
    public int updateRestaurante(Restaurante restaurante) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RESTAURANTE_ID, restaurante.getmIdRest());
        values.put(COLUMN_RESTAURANTE_NOM, restaurante.getmNomRest());
        values.put(COLUMN_RESTAURANTE_LOC, restaurante.getmLocRest());
        values.put(COLUMN_RESTAURANTE_HORARIO, restaurante.getmHorarioRest());

        // updating row
        return db.update(TABLE_RESTAURANTES, values, COLUMN_RESTAURANTE_ID + " = ?",
                new String[] { String.valueOf(restaurante.getmIdRest()) });
    }

    /**
     * Método para eliminar un cliente de la base de datos.
     * @param restauranteID un identificador único.
     **/
    public void deleteRestaurante(long restauranteID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTES, COLUMN_RESTAURANTE_ID + " = ?",
                new String[] { String.valueOf(restauranteID) });
    }

    // <------------------------------------- Métodos del Platillo ------------------------------------->
    /**
     * Método para crear un platillo en la base de datos.
     * @param platillo, objeto de la clase Platillo.
     * @return platilloID, el idetificador único del archivo.
     **/
    public long createPlatillo(Platillo platillo) {
        SQLiteDatabase db = this.getWritableDatabase();
        //platillo.setmIdPlatillo(idGenerator(1));

        ContentValues values = new ContentValues();
        values.put(COLUMN_PLATILLO_ID, platillo.getmIdPlatillo());
        values.put(COLUMN_PLATILLO_NOM, platillo.getmNombrePlatillo());
        values.put(COLUMN_PLATILLO_VOTOS, platillo.getmVotos());
        values.put(COLUMN_PLATILLO_ID_REST, platillo.getmIdRestaurante());

        long platilloID = db.insert(TABLE_PLATILLOS, null, values); // Insertando fila

        return platilloID;
    }

    /**
     * Método para obtener un platillo de la base de datos.
     * @param platilloID, identificador único del archivo.
     * @return platillo, objeto de la clase Platillo.
     **/
    public Platillo getPlatillo(long platilloID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_PLATILLOS + " WHERE " + COLUMN_PLATILLO_ID + " = " + platilloID;
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        if (c != null) {
            c.moveToFirst();
        }

        Platillo platillo = new Platillo();
        platillo.setmIdPlatillo(c.getInt(c.getColumnIndex(COLUMN_PLATILLO_ID)));
        platillo.setmNombrePlatillo(c.getString(c.getColumnIndex(COLUMN_PLATILLO_NOM)));
        platillo.setmVotos(c.getInt(c.getColumnIndex(COLUMN_PLATILLO_VOTOS)));
        platillo.setmIdRestaurante(c.getInt(c.getColumnIndex(COLUMN_PLATILLO_ID_REST)));

        return platillo;
    }

    /**
     * Método para obtener todos los restaurantes de la base de datos.
     * @return restaurante, lista de objetos de la clase Restaurante
     **/
    public List<Platillo> getPlatillos() {
        List<Platillo> platillos = new ArrayList<Platillo>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLATILLOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        // Loop para obtener todos los clientes
        if (c.moveToFirst()) {
            do {
                Platillo platillo = new Platillo();
                platillo.setmIdPlatillo(c.getInt(c.getColumnIndex(COLUMN_PLATILLO_ID)));
                platillo.setmNombrePlatillo(c.getString(c.getColumnIndex(COLUMN_PLATILLO_NOM)));
                platillo.setmVotos(c.getInt(c.getColumnIndex(COLUMN_PLATILLO_VOTOS)));
                platillo.setmIdRestaurante(c.getInt(c.getColumnIndex(COLUMN_PLATILLO_ID_REST)));

                platillos.add(platillo); // Añadiendo al array

            } while (c.moveToNext());
        }
        return platillos;
    }

    /**
     * Método para actualizar un platillo de la base de datos.
     * @return un identificador único.
     **/
    public int updatePlatillo(Platillo platillo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PLATILLO_ID, platillo.getmIdPlatillo());
        values.put(COLUMN_PLATILLO_NOM, platillo.getmNombrePlatillo());
        values.put(COLUMN_PLATILLO_VOTOS, platillo.getmVotos());
        values.put(COLUMN_PLATILLO_ID_REST, platillo.getmIdRestaurante());

        // updating row
        return db.update(TABLE_PLATILLOS, values, COLUMN_PLATILLO_ID + " = ?",
                new String[] { String.valueOf(platillo.getmIdPlatillo()) });
    }

    /**
     * Método para eliminar un platillo de la base de datos.
     * @param platilloID un identificador único.
     **/
    public void deletePlatillo(long platilloID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLATILLOS, COLUMN_PLATILLO_ID + " = ?",
                new String[] { String.valueOf(platilloID) });
    }

    // <------------------------------------- Métodos del HorarioPlatillo ------------------------------------->
    /**
     * Método para crear un horarioPlatillo en la base de datos.
     * @param horarioPlatillo, objeto de la clase HorarioPlatillo.
     * @return horarioPlatilloID, el idetificador único del archivo.
     **/
    public long createHorarioPlatillo(HorarioPlatillo horarioPlatillo) {
        SQLiteDatabase db = this.getWritableDatabase();
        //horarioPlatillo.setmIdHorarioComida(idGenerator(2));

        ContentValues values = new ContentValues();
        values.put(COLUMN_HORARIO_ID, horarioPlatillo.getmIdHorarioComida());
        values.put(COLUMN_HORARIO_NOM, horarioPlatillo.getmNombreHorario());
        values.put(COLUMN_HORARIO_HOR, horarioPlatillo.getmHorarioComida());
        values.put(COLUMN_HORARIO_ID_REST, horarioPlatillo.getmIdRestaurante());

        long horarioPlatilloID = db.insert(TABLE_HORARIOS, null, values); // Insertando fila

        return horarioPlatilloID;
    }

    /**
     * Método para obtener un horarioPlatillo de la base de datos.
     * @param horarioPlatilloID, identificador único del archivo.
     * @return platillo, objeto de la clase Platillo.
     **/
    public HorarioPlatillo getHorarioPlatillo(long horarioPlatilloID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_HORARIOS + " WHERE " + COLUMN_HORARIO_ID + " = " + horarioPlatilloID;
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        if (c != null) {
            c.moveToFirst();
        }

        HorarioPlatillo horarioPlatillo = new HorarioPlatillo();
        horarioPlatillo.setmIdHorarioComida(c.getInt(c.getColumnIndex(COLUMN_HORARIO_ID)));
        horarioPlatillo.setmNombreHorario(c.getString(c.getColumnIndex(COLUMN_HORARIO_NOM)));
        horarioPlatillo.setmHorarioComida(c.getString(c.getColumnIndex(COLUMN_HORARIO_HOR)));
        horarioPlatillo.setmIdRestaurante(c.getInt(c.getColumnIndex(COLUMN_HORARIO_ID_REST)));

        return horarioPlatillo;
    }

    /**
     * Método para obtener todos los horarioPlatillo de la base de datos.
     * @return horarioPlatillo, lista de objetos de la clase HorarioPlatillo.
     **/
    public List<HorarioPlatillo> getHorariosPlatillos() {
        List<HorarioPlatillo> horarioPlatillos = new ArrayList<HorarioPlatillo>();
        String selectQuery = "SELECT  * FROM " + TABLE_HORARIOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        // Loop para obtener todos los clientes
        if (c.moveToFirst()) {
            do {
                HorarioPlatillo horarioPlatillo = new HorarioPlatillo();
                horarioPlatillo.setmIdHorarioComida(c.getInt(c.getColumnIndex(COLUMN_HORARIO_ID)));
                horarioPlatillo.setmNombreHorario(c.getString(c.getColumnIndex(COLUMN_HORARIO_NOM)));
                horarioPlatillo.setmHorarioComida(c.getString(c.getColumnIndex(COLUMN_HORARIO_HOR)));
                horarioPlatillo.setmIdRestaurante(c.getInt(c.getColumnIndex(COLUMN_HORARIO_ID_REST)));

                horarioPlatillos.add(horarioPlatillo); // Añadiendo al array

            } while (c.moveToNext());
        }
        return horarioPlatillos;
    }

    /**
     * Método para actualizar un horarioPlatillo de la base de datos.
     * @return un identificador único.
     **/
    public int updateHorarioPlatillo(HorarioPlatillo horarioPlatillo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HORARIO_ID, horarioPlatillo.getmIdHorarioComida());
        values.put(COLUMN_HORARIO_NOM, horarioPlatillo.getmNombreHorario());
        values.put(COLUMN_HORARIO_HOR, horarioPlatillo.getmHorarioComida());
        values.put(COLUMN_HORARIO_ID_REST, horarioPlatillo.getmIdRestaurante());

        // updating row
        return db.update(TABLE_HORARIOS, values, COLUMN_HORARIO_ID + " = ?",
                new String[] { String.valueOf(horarioPlatillo.getmIdHorarioComida()) });
    }

    /**
     * Método para eliminar un horarioPlatillo de la base de datos.
     * @param horarioPlatilloID un identificador único.
     **/
    public void deleteHorarioPlatillo(long horarioPlatilloID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HORARIOS, COLUMN_HORARIO_ID + " = ?",
                new String[] { String.valueOf(horarioPlatilloID) });
    }

    // <------------------------------------- Métodos del Menu ------------------------------------->
    /**
     * Método para crear un menu en la base de datos.
     * @param menu, objeto de la clase Menu.
     * @return menuID, el idetificador único del archivo.
     **/
    public long createMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();
        //menu.setmIdMenu(idGenerator(3));

        ContentValues values = new ContentValues();
        values.put(COLUMN_MENU_ID, menu.getmIdMenu());
        values.put(COLUMN_MENU_ID_HORARIO, menu.getmIdHorario());
        values.put(COLUMN_MENU_ID_PLATILLO, menu.getmIdPlatillo());

        long menuID = db.insert(TABLE_MENUS, null, values); // Insertando fila

        return menuID;
    }

    /**
     * Método para obtener un menu de la base de datos.
     * @param menuID, identificador único del archivo.
     * @return menu, objeto de la clase Menu.
     **/
    public Menu getMenu(long menuID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_MENUS + " WHERE " + COLUMN_MENU_ID + " = " + menuID;
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        if (c != null) {
            c.moveToFirst();
        }

        Menu menu = new Menu();
        menu.setmIdMenu(c.getInt(c.getColumnIndex(COLUMN_MENU_ID)));
        menu.setmIdPlatillo(c.getInt(c.getColumnIndex(COLUMN_MENU_ID_PLATILLO)));
        menu.setmIdHorario(c.getInt(c.getColumnIndex(COLUMN_MENU_ID_HORARIO)));

        return menu;
    }

    /**
     * Método para obtener todos los menus de la base de datos.
     * @return menus, lista de objetos de la clase Menu.
     **/
    public List<Menu> getMenus() {
        List<Menu> menus = new ArrayList<Menu>();
        String selectQuery = "SELECT  * FROM " + TABLE_MENUS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        // Loop para obtener todos los clientes
        if (c.moveToFirst()) {
            do {
                Menu menu = new Menu();
                menu.setmIdMenu(c.getInt(c.getColumnIndex(COLUMN_MENU_ID)));
                menu.setmIdPlatillo(c.getInt(c.getColumnIndex(COLUMN_MENU_ID_PLATILLO)));
                menu.setmIdHorario(c.getInt(c.getColumnIndex(COLUMN_MENU_ID_HORARIO)));

                menus.add(menu); // Añadiendo al array

            } while (c.moveToNext());
        }
        return menus;
    }

    /**
     * Método para actualizar un menu de la base de datos.
     * @return un identificador único.
     **/
    public int updateMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MENU_ID, menu.getmIdMenu());
        values.put(COLUMN_MENU_ID_HORARIO, menu.getmIdHorario());
        values.put(COLUMN_MENU_ID_PLATILLO, menu.getmIdPlatillo());

        // updating row
        return db.update(TABLE_MENUS, values, COLUMN_MENU_ID + " = ?",
                new String[] { String.valueOf(menu.getmIdMenu()) });
    }

    /**
     * Método para eliminar un horarioPlatillo de la base de datos.
     * @param menuID un identificador único.
     **/
    public void deleteMenu(long menuID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MENUS, COLUMN_MENU_ID + " = ?",
                new String[] { String.valueOf(menuID) });
    }

    // <------------------------------------- Métodos del Voto ------------------------------------->
    /**
     * Método para crear un voto en la base de datos.
     * @param voto, objeto de la clase Voto.
     * @return votoID, el idetificador único del archivo.
     **/
    public long createVoto(Voto voto) {
        SQLiteDatabase db = this.getWritableDatabase();
        voto.setmIdVoto(idGenerator(4));

        ContentValues values = new ContentValues();
        values.put(COLUMN_VOTO_ID, voto.getmIdVoto());
        values.put(COLUMN_VOTO_ID_CLIENTE, voto.getmIdCliente());
        values.put(COLUMN_VOTO_ID_PLATILLO, voto.getmIdPlatillo());
        values.put(COLUMN_VOTO_VALOR, voto.getmValor());

        long votoID = db.insert(TABLE_VOTOS, null, values); // Insertando fila

        return votoID;
    }

    /**
     * Método para obtener un voto de la base de datos.
     * @param votoID, identificador único del archivo.
     * @return voto, objeto de la clase Voto.
     **/
    public Voto getVoto(long votoID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_VOTOS + " WHERE " + COLUMN_VOTO_ID + " = " + votoID;
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        if (c != null) {
            c.moveToFirst();
        }

        Voto voto = new Voto();
        voto.setmIdVoto(c.getInt(c.getColumnIndex(COLUMN_VOTO_ID)));
        voto.setmValor(c.getInt(c.getColumnIndex(COLUMN_VOTO_VALOR)));
        voto.setmIdCliente(c.getInt(c.getColumnIndex(COLUMN_VOTO_ID_CLIENTE)));
        voto.setmIdPlatillo(c.getInt(c.getColumnIndex(COLUMN_VOTO_ID_PLATILLO)));

        return voto;
    }

    /**
     * Método para obtener todos los votos de la base de datos.
     * @return votos, lista de objetos de la clase Voto.
     **/
    public List<Voto> getVotos() {
        List<Voto> votos = new ArrayList<Voto>();
        String selectQuery = "SELECT  * FROM " + TABLE_VOTOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Log.e(LOG, selectQuery);

        // Loop para obtener todos los clientes
        if (c.moveToFirst()) {
            do {
                Voto voto = new Voto();
                voto.setmIdVoto(c.getInt(c.getColumnIndex(COLUMN_VOTO_ID)));
                voto.setmValor(c.getInt(c.getColumnIndex(COLUMN_VOTO_VALOR)));
                voto.setmIdCliente(c.getInt(c.getColumnIndex(COLUMN_VOTO_ID_CLIENTE)));
                voto.setmIdPlatillo(c.getInt(c.getColumnIndex(COLUMN_VOTO_ID_PLATILLO)));

                votos.add(voto); // Añadiendo al array

            } while (c.moveToNext());
        }
        return votos;
    }

    /**
     * Método para actualizar un menu de la base de datos.
     * @return un identificador único.
     **/
    public int updateVoto(Voto voto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VOTO_ID, voto.getmIdVoto());
        values.put(COLUMN_VOTO_ID_CLIENTE, voto.getmIdCliente());
        values.put(COLUMN_VOTO_ID_PLATILLO, voto.getmIdPlatillo());
        values.put(COLUMN_VOTO_VALOR, voto.getmValor());

        // updating row
        return db.update(TABLE_VOTOS, values, COLUMN_VOTO_ID + " = ?",
                new String[] { String.valueOf(voto.getmIdVoto()) });
    }

    /**
     * Método para eliminar un voto de la base de datos.
     * @param votoID un identificador único.
     **/
    public void deleteVoto(long votoID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VOTOS, COLUMN_VOTO_ID + " = ?",
                new String[] { String.valueOf(votoID) });
    }

    //Método para crear IDs
    public int idGenerator(int id){
        if(id == 0){
            mIdRestGen++;

            return mIdRestGen;
        }
        if(id == 1){
            mIdPlatGen++;
            return mIdPlatGen;
        }
        if(id == 2){
            mIdHorGen++;
            return mIdHorGen;
        }
        if(id == 3){
            mIdMenuGen++;
            return mIdMenuGen;
        }else{
            mIdVotoGen++;
            return mIdVotoGen;
        }

    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
