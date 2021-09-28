package upeu.edu.sqlitejava_hddr.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upeu.edu.sqlitejava_hddr.config.ConexionDB;
import upeu.edu.sqlitejava_hddr.modelo.Persona;

public class PersonaDao {
    private ConexionDB conexionDB;
    private SQLiteDatabase database;
    public PersonaDao(Context context){conexionDB = new ConexionDB(context);}

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = conexionDB.getWritableDatabase();
        }
        return database;
    }

    private Persona crearPersona(Cursor cursor){
        Persona persona = new Persona(
                cursor.getInt(cursor.getColumnIndex(ConexionDB.Personas._ID)),
                cursor.getString(cursor.getColumnIndex(ConexionDB.Personas.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(ConexionDB.Personas.APELLIDO)),
                cursor.getString(cursor.getColumnIndex(ConexionDB.Personas.DNI)),
                cursor.getString(cursor.getColumnIndex(ConexionDB.Personas.TELEFONO)),
                cursor.getString(cursor.getColumnIndex(ConexionDB.Personas.CORREO)),
                cursor.getString(cursor.getColumnIndex(ConexionDB.Personas.UBICACION))
        );
        return persona;
    }

    public List<Persona> listarPersona(){
        Cursor cursor = getDatabase().query(ConexionDB.Personas.TABLE,ConexionDB.Personas.COLUMNAS, null, null, null, null, null);
        List<Persona> lista = new ArrayList<Persona>();
        while(cursor.moveToNext()){
            Persona modelo = crearPersona(cursor);
            lista.add(modelo);
        }
        return lista;
    }

    public long modificarPersona(Persona persona){
        ContentValues values = new ContentValues();
        values.put(ConexionDB.Personas.NOMBRE, persona.getNombre());
        values.put(ConexionDB.Personas.APELLIDO, persona.getApellido());
        values.put(ConexionDB.Personas.DNI, persona.getDni());
        values.put(ConexionDB.Personas.TELEFONO, persona.getTelefono());
        values.put(ConexionDB.Personas.CORREO, persona.getCorreo());
        values.put(ConexionDB.Personas.UBICACION, persona.getUbicacion());
        if(persona.get_id() != null){
            return database.update(ConexionDB.Personas.TABLE, values,
                    "_id = ?", new String[]{persona.get_id().toString()});
        }
        return getDatabase().insert(ConexionDB.Personas.TABLE,null,values);
    }

    public boolean eliminarPersona(int id){
        return getDatabase().delete(ConexionDB.Personas.TABLE,"_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Persona buscarPersonaPorID(int id){
        Cursor cursor = getDatabase().query(ConexionDB.Personas.TABLE,
                ConexionDB.Personas.COLUMNAS, "_id = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Persona model = crearPersona(cursor);
            cursor.close();
            return model;
        }
        return null;
    }


    public void cerrarDB(){
        conexionDB.close();
        database = null;
    }
}
