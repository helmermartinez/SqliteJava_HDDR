package upeu.edu.sqlitejava_hddr.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Mensajes {

    public static void Msg(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
    public static void MSGOk(Activity activity, String titulo, String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setNeutralButton("OK", null);
        alert.show();
    }
    public static void MsgConfirm(Activity activity, String titulo, String msg, DialogInterface.OnClickListener listener){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setPositiveButton("Si", listener);
        alert.setNeutralButton("No", null);
        //alert.setIcon(icone);
        alert.show();
    }
    public static AlertDialog crearAlertaDialog(Activity activity){
        final CharSequence[] items = {
                "Editar",
                "Eliminar"
        };
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opciones");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);
        return alert.create();
    }
    public static androidx.appcompat.app.AlertDialog crearDialogConfirmacion(Activity activity){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Desea Eliminar..?");
        alert.setPositiveButton("Si", (DialogInterface.OnClickListener) activity);
        alert.setNeutralButton("No", (DialogInterface.OnClickListener) activity);
        return alert.create();
    }
}
