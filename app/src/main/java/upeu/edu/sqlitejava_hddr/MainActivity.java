package upeu.edu.sqlitejava_hddr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import upeu.edu.sqlitejava_hddr.adapter.PersonaAdapter;
import upeu.edu.sqlitejava_hddr.dao.PersonaDao;
import upeu.edu.sqlitejava_hddr.modelo.Persona;
import upeu.edu.sqlitejava_hddr.util.Mensajes;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{
    private ListView listav;
    private List<Persona> listap;
    private PersonaAdapter adapter;
    private PersonaDao personaDao;
    private int idposi;
    private AlertDialog alertDialog, alertconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        personaDao = new PersonaDao(this);
        listap = personaDao.listarPersona();
        adapter = new PersonaAdapter(this,listap);

        listav = (ListView) findViewById(R.id.persona_list);
        listav.setAdapter(adapter);

        listav.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_guardar) {
            startActivity(new Intent(this, PersonaActivity.class));
        }
        if(id==R.id.action_menu_salir1){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listap.get(idposi).get_id();
        switch (which){
            case 0:
                Intent intent = new Intent(this, PersonaActivity.class);
                intent.putExtra("PERSONA_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                listap.remove(idposi);
                personaDao.eliminarPersona(id);
                listav.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertconfirm.dismiss();break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposi = position;
        alertDialog.show();
    }
}