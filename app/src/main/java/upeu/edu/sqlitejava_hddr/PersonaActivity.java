package upeu.edu.sqlitejava_hddr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import upeu.edu.sqlitejava_hddr.dao.PersonaDao;
import upeu.edu.sqlitejava_hddr.modelo.Persona;
import upeu.edu.sqlitejava_hddr.util.Mensajes;

public class PersonaActivity extends AppCompatActivity {
    private EditText edtNombre, edtApellido, edtDni, edtTelefono, edtCorreo, edtUbicacion;
    private PersonaDao personaDao;
    private Persona persona;
    private int idper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        personaDao = new PersonaDao(this);

        edtNombre = (EditText) findViewById(R.id.txtNombre);
        edtApellido = (EditText) findViewById(R.id.txtApellido);
        edtDni = (EditText) findViewById(R.id.txtDNI);
        edtTelefono = (EditText) findViewById(R.id.txtTelefono);
        edtCorreo = (EditText) findViewById(R.id.txtCorreo);
        edtUbicacion = (EditText) findViewById(R.id.txtUbicacion);
        idper = getIntent().getIntExtra("PERSONA_ID",0);

        if(idper > 0){
            Persona model = personaDao.buscarPersonaPorID(idper);
            edtNombre.setText(model.getNombre());
            edtApellido.setText(model.getApellido());
            edtDni.setText(model.getDni());
            edtTelefono.setText(model.getTelefono());
            edtCorreo.setText(model.getCorreo());
            edtUbicacion.setText(model.getUbicacion());
            setTitle("Actualizar Persona");
        }
    }

    @Override
    protected void onDestroy() {
        personaDao.cerrarDB();
        super.onDestroy();
    }

    private void registrar(){
        boolean validar = true;
        String nombre = edtNombre.getText().toString();
        String apellido = edtApellido.getText().toString();
        String dni = edtDni.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String correo = edtCorreo.getText().toString();
        String ubicacion = edtUbicacion.getText().toString();

        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError("Nombre Obligatorio");
        }
        if(apellido == null || apellido.equals("")){
            validar = false;
            edtNombre.setError("Apellido Obligatorio");
        }
        if(dni == null || dni.equals("")){
            validar = false;
            edtNombre.setError("DNI Obligatorio");
        }
        if(telefono == null || telefono.equals("")){
            validar = false;
            edtNombre.setError("Telefono Obligatorio");
        }
        if(correo == null || correo.equals("")){
            validar = false;
            edtNombre.setError("Correo Obligatorio");
        }
        if(ubicacion == null || ubicacion.equals("")){
            validar = false;
            edtNombre.setError("Ubicacion Obligatorio");
        }
        if(validar){
            persona = new Persona();
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDni(dni);
            persona.setTelefono(telefono);
            persona.setCorreo(correo);
            persona.setUbicacion(ubicacion);
            if(idper > 0){
                persona.set_id(idper);
            }
            long resultado = personaDao.modificarPersona(persona);
            if(resultado != -1){
                if(idper > 0) {
                    Mensajes.Msg(this, "Registro modificado...!");
                    startActivity(new Intent(this, MainActivity.class));
                }else{
                    Mensajes.Msg(this, "Registro Guardado...!");
                    startActivity(new Intent(this, MainActivity.class));
                }
                finish();
                //startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, "Error al Guardar!");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guardar_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardar:
                this.registrar();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
