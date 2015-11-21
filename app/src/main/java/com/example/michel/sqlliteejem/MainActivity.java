package com.example.michel.sqlliteejem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    public ListView lstVListaAprendices;
    public Cursor cursorListaAprendices,cursorsearch;    // variable cursor: trae los datos
    public SimpleCursorAdapter simpleAdapter,adapterSearch;    //Adaptador:mapea la fuente de datos
    EditText campo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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




//      Insertamos datos
/*      dbManager.Insertar("Michel", "Martinez", "Cll 68 # 9a-124", "24");
        dbManager.Insertar("Juan", "Garcia", "Cll 67 # 3-14", "26");
        dbManager.Insertar("Carlos", "Tabares", "Cll 66 # 3-134", "34");
        dbManager.Insertar("Felipe", "Muños", "Cll 65 # 5-24", "36");
        dbManager.Insertar("Gustavo", "Escobar", "Cll 64 # 4a-45", "18");*/


//      dbManager.eliminar("Gustavo");
//      dbManager.actualizar("Juan","30");
        listaAll();


    }

    private void listaAll() {
        //      Se crea el cursor que trae los datos
        cursorListaAprendices = dbManager.cursorConsultaAprendicesAll();

//      String from y to para el SimpleCursorAdapter
        String[] from = new String[]{dbManager.CN_ID,dbManager.CN_NOMBRE, dbManager.CN_APELLIDO,dbManager.CN_DIRECCION,dbManager.CN_EDAD};
        int[] to = new int[]{R.id.text1, R.id.text2,R.id.text3,R.id.text4,R.id.text5};

//      Creamos el adaptador para los datos consultados, con el layout predefinido:android.R.layout.two_line_list_item
        simpleAdapter = new SimpleCursorAdapter(this, R.layout.lista_aprendis, cursorListaAprendices, from, to, 0);

//      se hace el set del adaptador al ListView
        lstVListaAprendices.setAdapter(simpleAdapter);
    }

    private void lista(String id) {
        //      Se crea el cursor que trae los datos
        cursorsearch = dbManager.cursorConsultaAprendis(id);

//      String from y to para el SimpleCursorAdapter
        String[] from = new String[]{dbManager.CN_ID,dbManager.CN_NOMBRE, dbManager.CN_APELLIDO,dbManager.CN_DIRECCION,dbManager.CN_EDAD};
        int[] to = new int[]{R.id.text1, R.id.text2,R.id.text3,R.id.text4,R.id.text5};

//      Creamos el adaptador para los datos consultados, con el layout predefinido:android.R.layout.two_line_list_item
        adapterSearch = new SimpleCursorAdapter(this, R.layout.lista_aprendis, cursorsearch, from, to, 0);

//      se hace el set del adaptador al ListView
        lstVListaAprendices.setAdapter(adapterSearch);
    }



    private void inicializarUI() {

//       creamos la BD
         dbManager = new DBManager(this);

//      inicialiazamos ListView
        lstVListaAprendices = (ListView) findViewById(R.id.lstVAprendis);

        campo = (EditText) findViewById(R.id.edtcampo);


    }

    public void delet(View v){

           if(campo.getText().toString().isEmpty()){
               Snackbar.make(v, R.string.ErrorID, Snackbar.LENGTH_SHORT).show();
           }else {
               dbManager.eliminar(campo.getText().toString());
               Snackbar.make(v, R.string.Borrado, Snackbar.LENGTH_SHORT).show();
               listaAll();
               campo.setText("");


           }


    }

    public void search(View v){

        if(campo.getText().toString().isEmpty()){
            Snackbar.make(v, R.string.ErrorID, Snackbar.LENGTH_SHORT).show();
        }else {
            lista(campo.getText().toString());
            campo.setText("");

        }
    }

    public void mostrarTodo(View v){
        listaAll();
    }



    @Override
    protected void onResume() {
        super.onResume();
        listaAll();
    }



    public void LanzadorCreate (View V)
    {
        Intent myIntent = new Intent();
        myIntent.setClass(MainActivity.this,CreateActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_bottom, android.support.v7.appcompat.R.anim.abc_slide_out_bottom);

    }

    public void LanzadorUpdate (View V)
    {
        Intent myIntent = new Intent();
        myIntent.setClass(MainActivity.this,EditarActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_bottom, android.support.v7.appcompat.R.anim.abc_slide_out_bottom);

    }

   /* public void LanzadorDelet (View V)
    {
        Intent myIntent = new Intent();
        myIntent.setClass(MainActivity.this,CalculadorafinancieraActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_bottom,android.support.v7.appcompat.R.anim.abc_slide_out_bottom);

    }



    public void LanzadorSearch (View V)
    {
        Intent myIntent = new Intent();
        myIntent.setClass(MainActivity.this,CalculadorafinancieraActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_bottom,android.support.v7.appcompat.R.anim.abc_slide_out_bottom);

    }*/










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
