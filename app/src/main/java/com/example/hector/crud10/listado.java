package com.example.hector.crud10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listView = (ListView) findViewById(R.id.listView);
        cargarLista();
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Toast.makeText(listado.this,listado.get(i),Toast.LENGTH_SHORT).show();
                int contraseña = Integer.parseInt(listado.get(i).split(" ")[0]);
                String nombre = listado.get(i).split(" ")[1];
                String apellido = listado.get(i).split(" ")[2];
                Intent intent = new Intent(listado.this,Modificar.class);
                intent.putExtra("Id",contraseña);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Apellido",apellido);
                startActivity(intent);
            }

        });

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarLista(){
        try {
            listado = listadoPersonas();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
            listView.setAdapter(adapter);
            Toast.makeText(this,"Cargando Lista", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }

    private ArrayList<String> listadoPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        DataBase ayuda = new DataBase(this,"Demo1",null,1);
        SQLiteDatabase db =  ayuda.getReadableDatabase();
        try {
            String sql = "select Id,Nombre, Apellido from Persona";
            Cursor c = db.rawQuery(sql,null);
            if (c.moveToFirst()){
                do {
                    String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                    datos.add(linea);
                }while (c.moveToNext());

            }

        }catch (Exception e){
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        db.close();
        return datos;
    }
}
