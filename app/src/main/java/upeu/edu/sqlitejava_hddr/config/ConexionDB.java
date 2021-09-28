package upeu.edu.sqlitejava_hddr.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionDB extends SQLiteOpenHelper {
    private static final String DB_NAME  = "DBHDDR";
    private static final int DB_VERSION = 1;
    public ConexionDB(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table personas(_id integer primary key autoincrement, "
                +"nombre text not null, apellido text not null, dni text not null, "
                +"telefono text not null, correo text not null, ubicacion text not null)");
        db.execSQL("insert into personas(nombre, apellido, dni, telefono, correo, ubicacion) " +
                    "values('Helmer', 'Martinez', '0601200104521', '88726420', 'helmer@mail.com', 'Honduras')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Personas{
        public static final String TABLE = "personas";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String DNI = "dni";
        public static final String TELEFONO = "telefono";
        public static final String CORREO = "correo";
        public static final String UBICACION = "ubicacion";
        public static final String[] COLUMNAS = new String[]{_ID, NOMBRE, APELLIDO, DNI, TELEFONO, CORREO, UBICACION};
    }
}
