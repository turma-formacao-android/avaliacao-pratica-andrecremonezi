package avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.R;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.util.FormHelper;

/**
 * Created by Administrador on 01/10/2015.
 */
public class NewContactActivity extends AppCompatActivity {
    private Button   buttonCriar;
    private Button   buttonSearch;
    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextCity;
    private EditText editTextState;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private Contact contact;
    private ProgressDialog progressDialog;

    public static final String PARAM_TASK = "PARAM_TASK";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        initContact();
        bindFields();
        bindButtonSearch();
        bindButtonSave();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindButtonSave() {
        buttonCriar = (Button) findViewById(R.id.buttonSave);
        buttonCriar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String requiredMessage = getResources().getString(R.string.msg_field_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
                    bindContact();
                    //ContactBussinessServices.save(contact);
                    Toast.makeText(NewContactActivity.this, R.string.msg_contact_create_sucess, Toast.LENGTH_LONG).show();
                    NewContactActivity.this.finish();
                }
            }
        });
    }


    public void bindContact(){
        contact.setName(editTextName.getText().toString());
        contact.setZipCode(editTextZipCode.getText().toString());
        contact.setCity(editTextCity.getText().toString());
        contact.setStreet(editTextStreet.getText().toString());
        contact.setNeighborhood(editTextNeighborhood.getText().toString());
        contact.setState(editTextState.getText().toString());
        contact.setType(editTextType.getText().toString());
    }

    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initContact(){
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.contact = (Contact) extras.getParcelable(PARAM_TASK);
        }

        this.contact = this.contact == null ? new Contact() : this.contact;
    }


    public void bindFields(){
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextState = (EditText) findViewById(R.id.editTextState);
    }




}
