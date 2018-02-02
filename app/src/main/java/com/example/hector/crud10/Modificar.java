package com.example.hector.crud10;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    EditText et_nombre, et_apellido;
    Button bt_modificar, bt_eliminar;
    int id;
    String nombre, apellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("Id");
            nombre = bundle.getString("Nombre");
            apellido = bundle.getString("Apellido");


        }

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);

        et_nombre.setText(nombre);
        et_apellido.setText(apellido);

        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_eliminar = (Button) findViewById(R.id.bt_eliminar);


        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modificarPersona(id, et_nombre.getText().toString(), et_apellido.getText().toString());
                onBackPressed();
            }
        });

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarpersona(id);
                onBackPressed();
            }
        });

    }

    private void modificarPersona(int Id, String Nombre, String Apellido) {
        DataBase ayuda = new DataBase(this, "Demo1", null, 1);
        SQLiteDatabase db = ayuda.getReadableDatabase();

        String Sql = "update Persona set Nombre ='" + Nombre + "',Apellido='" + Apellido + "'where Id=" + Id;
        db.execSQL(Sql);

    }

    private void eliminarpersona(int Id){
        try {
            DataBase ayuda = new DataBase(this,"Demo1",null,1);
            SQLiteDatabase db = ayuda.getWritableDatabase();

            String sql = "delete from persona where Id="+ Id ;
            db.execSQL(sql);

            Toast.makeText(this,"Persona Eliminada" ,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }
}
