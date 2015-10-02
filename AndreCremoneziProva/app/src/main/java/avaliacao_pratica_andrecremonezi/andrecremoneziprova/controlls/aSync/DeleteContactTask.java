package avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.aSync;

import android.os.AsyncTask;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services.ContactBussinessService;

/**
 * Created by Administrador on 02/10/2015.
 */
public class DeleteContactTask extends AsyncTask<Contact, Void, Void> {

    @Override
    protected Void doInBackground(Contact...product) {
        ContactBussinessService.delete(product[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
