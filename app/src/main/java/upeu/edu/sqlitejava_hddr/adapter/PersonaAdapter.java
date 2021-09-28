package upeu.edu.sqlitejava_hddr.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import upeu.edu.sqlitejava_hddr.R;
import upeu.edu.sqlitejava_hddr.modelo.Persona;

public class PersonaAdapter extends BaseAdapter {
    private Context context;
    private List<Persona> lista;
    public PersonaAdapter(Context context, List<Persona> model){
        this.context= context;
        this.lista= model;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
       return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        Persona p = lista.get(i);
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.personas, null);
        }
        TextView txtlista = (TextView)v.findViewById(R.id.lista_personas);
        txtlista.setText((i+1+".-"+p.getNombre()+" "+p.getApellido()+" "+p.getDni()+" "+p.getTelefono()+" "+p.getCorreo()+" "+p.getUbicacion()));
        return v;
    }
}
