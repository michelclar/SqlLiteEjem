package com.example.michel.sqlliteejem;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CreateActivity extends AppCompatActivity {
    /*n:nombre
    a:apeliido
    d:direccion
    e:edad*/
    private EditText n, a, d, e;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        inicializarUI();


      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }

    private void inicializarUI() {
        n = (EditText) findViewById(R.id.nombre);
        a = (EditText) findViewById(R.id.apellido);
        d = (EditText) findViewById(R.id.direccion);
        e = (EditText) findViewById(R.id.edad);
        dbManager = new DBManager(this);

    }

    private boolean validar(View v) {
        if (n.getText().toString().isEmpty() || a.getText().toString().isEmpty() || d.getText().toString().isEmpty() || e.getText().toString().isEmpty()) {
            Snackbar.make(v, R.string.Vacios, Snackbar.LENGTH_SHORT).show();
            return false;

        } else {

            return true;
        }

    }

    public void create(View v){
        if(validar(v)){
            dbManager.Insertar(n.getText().toString(), a.getText().toString(), d.getText().toString(), e.getText().toString());
            Snackbar.make(v, R.string.Guardado, Snackbar.LENGTH_SHORT).show();
            n.setText("");
            a.setText("");
            d.setText("");
            e.setText("");


        }

    }


}
