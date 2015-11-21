package com.example.michel.sqlliteejem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditarActivity extends AppCompatActivity {

    /*
    i:ID
    n:nombre
    a:apeliido
    d:direccion
    e:edad*/
    private EditText i, n, a, d, e;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
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

        i = (EditText) findViewById(R.id.idU);
        n = (EditText) findViewById(R.id.nombreU);
        a = (EditText) findViewById(R.id.apellidoU);
        d = (EditText) findViewById(R.id.direccionU);
        e = (EditText) findViewById(R.id.edadU);
        dbManager = new DBManager(this);

    }

    private boolean validar(View v){
        if (i.getText().toString().isEmpty()) {
            Snackbar.make(v, R.string.ErrorID, Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if (n.getText().toString().isEmpty() || a.getText().toString().isEmpty() || d.getText().toString().isEmpty() || e.getText().toString().isEmpty()) {
            Snackbar.make(v, R.string.Vacios, Snackbar.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }
    }


    public void update(View v) {
        if (validar(v)){
            dbManager.actualizar(i.getText().toString(), n.getText().toString(), a.getText().toString(), d.getText().toString(), e.getText().toString());
            Snackbar.make(v, R.string.Actializado, Snackbar.LENGTH_SHORT).show();
            i.setText("");
            n.setText("");
            a.setText("");
            d.setText("");
            e.setText("");
        }

    }

}
