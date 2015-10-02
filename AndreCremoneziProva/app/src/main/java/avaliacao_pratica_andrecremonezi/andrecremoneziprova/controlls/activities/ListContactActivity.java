package avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.R;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.aSync.DeleteContactTask;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.adapters.ContactListAdpater;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services.ContactBussinessService;

public class ListContactActivity extends AppCompatActivity {
    private ListView listViewContactList;
    private Contact selectedContact;
    private FloatingActionButton buttonAddContact;
    public static final String PARAM_CONTACT = "PARAM_CONTACT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact_activity);
        bindListView();
        bindContactList();
        bindButtonAddContact();
    }



    private void bindListView() {
        listViewContactList = (ListView) findViewById(R.id.listViewContact);
        registerForContextMenu(listViewContactList);
        listViewContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContact = (Contact) listViewContactList.getAdapter().getItem(position);
                return false;
            }
        });
        onUpdateList();
    }

    private void bindButtonAddContact() {
        buttonAddContact = (FloatingActionButton) findViewById(R.id.floatingButton);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToTaskList = new Intent(ListContactActivity.this, NewContactActivity.class);
                startActivity(redirectToTaskList);
            }
        });
    }

    private void bindContactList() {
        listViewContactList = (ListView) findViewById(R.id.listViewContact);
        registerForContextMenu(listViewContactList);

        listViewContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListAdpater adapter = (ContactListAdpater) listViewContactList.getAdapter();
                selectedContact = adapter.getItem(position);
                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        onUpdateList();
        super.onResume();
    }

    private void onUpdateList() {
        List<Contact> values = ContactBussinessService.findAll();
        listViewContactList.setAdapter(new ContactListAdpater(this, values));
        ContactListAdpater adapter = (ContactListAdpater) listViewContactList.getAdapter();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuUpdateClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuUpdateClick() {
        Intent gotoForm = new Intent(ListContactActivity.this, NewContactActivity.class);
        gotoForm.putExtra(PARAM_CONTACT,selectedContact.getId());
        startActivity(gotoForm);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ListContactActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteContactTask().execute(selectedContact);

                        selectedContact = null;
                        String message = getString(R.string.msg_delete_sucessfull);
                        onUpdateList();
                        Toast.makeText(ListContactActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }



}
