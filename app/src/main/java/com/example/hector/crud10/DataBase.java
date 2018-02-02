package com.example.hector.crud10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 2/02/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    // Crear Tabla

    String tabla = "CREATE TABLE PERSONA (ID INTEGER PRIMARY KEY , NOMBRE TEXT , APELLIDO TEXT)";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Ejecucion
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla);

    }


    //Actualizacion
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table persona");
        sqLiteDatabase.execSQL(tabla);
    }
}
