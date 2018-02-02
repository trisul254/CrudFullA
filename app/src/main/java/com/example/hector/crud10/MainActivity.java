package com.example.hector.crud10;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre,et_apellido;
    Button bt_guardar,bt_mostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // busqueda
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);

        bt_guardar = (Button) findViewById(R.id.bt_guardar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarBD(et_nombre.getText().toString(),et_apellido.getText().toString());
            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, listado.class));
            }
        });
    }

    private void  guardarBD(String nombre, String apellido){
        DataBase ayuda = new DataBase(this,"Demo1",null,1);
        SQLiteDatabase db = ayuda.getWritableDatabase();
        try {
            ContentValues contenido =  new ContentValues();
            contenido.put("Nombre",nombre);
            contenido.put("Apellido",apellido);
            db.insert("Persona",null,contenido);
            Toast.makeText(this,"Ingreso a una nueva persona"  ,Toast.LENGTH_SHORT).show();
        }catch (Exception e){

            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
