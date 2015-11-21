package com.example.michel.sqlliteejem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Michel on 31/10/2015.
 */

//  se define Nombres de los campos de la tabla
public class DBManager {
    public static final String NOMBRE_TABLA = "Aprendices";
    public static final String CN_ID = "_id";
    public static final String CN_NOMBRE = "Nombre";
    public static final String CN_APELLIDO = "Apellido";
    public static final String CN_DIRECCION = "Direccion";
    public static final String CN_EDAD = "Edad";


//  Crear tabla
    public static final String CREAR_TABLE = "create table "+NOMBRE_TABLA+" ("
            +CN_ID+ " integer primary key autoincrement,"
            +CN_NOMBRE+ " text not null,"
            +CN_APELLIDO+ " text not null,"
            +CN_DIRECCION+ " text not null,"
            +CN_EDAD+ " text);";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context)
    {
        helper = new DBHelper(context);
//      si no existe, crea la BD y si existe la abre.
        db = helper.getWritableDatabase();

    }

    public void Insertar(String Nombre,String Apellido,String Direccion, String Edad)
    {
        // contenedor de datos
        ContentValues values = new ContentValues();
        values.put(CN_NOMBRE,Nombre);
        values.put(CN_APELLIDO,Apellido);
        values.put(CN_DIRECCION,Direccion);
        values.put(CN_EDAD,Edad);

        db.insert(NOMBRE_TABLA, null, values);

    }

//  nos trae los datos a consultar
    public Cursor cursorConsultaAprendicesAll ()
    {
        String[] columns = new String[]{CN_ID,CN_NOMBRE,CN_APELLIDO,CN_DIRECCION, CN_EDAD};
        return db.query(NOMBRE_TABLA, columns,null,null,null,null,null);

    }

    public Cursor cursorConsultaAprendis (String id)
    {
        String[] columns = new String[]{CN_ID,CN_NOMBRE,CN_APELLIDO,CN_DIRECCION, CN_EDAD};
        return db.query(NOMBRE_TABLA, columns,CN_ID+"=?",new String[]{id},null,null,null);

    }

    public void eliminar(String id)
    {
//      "=?" exprecion "WHERE"
        db.delete(NOMBRE_TABLA,CN_ID+"=?",new String[]{id});
/*        borra por nombre tambien
        db.delete(NOMBRE_TABLA,CN_NOMBRE+"=?",new String[]{id});*/
    }

    public void actualizar (String Id, String Nombre, String Apellido,String Direccion,String Edad)
    {
//      Creamos el content para el update
        ContentValues values = new ContentValues();
        values.put(CN_NOMBRE,Nombre);
        values.put(CN_APELLIDO,Apellido);
        values.put(CN_DIRECCION,Direccion);
        values.put(CN_EDAD,Edad);


//      actualizamos campos segun el id
//      "=?" exprecion "WHERE"
        db.update(NOMBRE_TABLA,values,CN_ID+"=?",new String[]{Id});

    }

}
