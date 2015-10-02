package avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.R;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Address;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.SocialNetwork;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Telephone;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.http.AddressService;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services.ContactBussinessService;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services.SocialNetworkBussinessService;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.util.FormHelper;

/**
 * Created by Administrador on 01/10/2015.
 */
public class NewContactActivity extends AppCompatActivity {
    private Button   buttonCriar;
    private Button   buttonSearch;
    private Button   buttonAddSocialNetwork;
    private Button   buttonAddTelephone;
    private Button   buttonAddEmail;

    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextCity;
    private EditText editTextState;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextEmail;
    private EditText editTextSocialNetwork;
    private EditText editTextTelephone;

    private Contact contact;
    private ProgressDialog progressDialog;

    public static final String PARAM_CONTACT = "PARAM_CONTACT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        initContact();
        bindFields();
        bindButtonAddEmail();
        bindButtonAddTelephone();
        bindButtonAddSocialNetwork();
        bindButtonSearch();
        bindButtonSave();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void bindButtonAddEmail(){
        buttonAddEmail = (Button) findViewById(R.id.buttonAddEmail);
        buttonAddEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String requiredMessage = getResources().getString(R.string.msg_field_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextEmail)) {

                    Email email = new Email();
                    email.setEmailAddress(editTextEmail.getText().toString());
                    contact.getEmails().add(email);


                    Toast.makeText(NewContactActivity.this, R.string.msg_email_add, Toast.LENGTH_LONG).show();
                    editTextEmail.setText("");
                }
            }
        });
    }


    public void bindButtonAddTelephone(){
        buttonAddTelephone = (Button) findViewById(R.id.buttonAddTelephone);
        buttonAddTelephone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String requiredMessage = getResources().getString(R.string.msg_field_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextTelephone)) {

                    Telephone telephone = new Telephone();
                    telephone.setNumber(editTextTelephone.getText().toString());
                    contact.getTelephones().add(telephone);


                    Toast.makeText(NewContactActivity.this, R.string.msg_telefone_add, Toast.LENGTH_LONG).show();
                    editTextTelephone.setText("");
                }
            }
        });
    }

    public void bindButtonAddSocialNetwork(){
        buttonAddSocialNetwork = (Button) findViewById(R.id.buttonAddSocialNetwork);
        buttonAddSocialNetwork.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String requiredMessage = getResources().getString(R.string.msg_field_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextSocialNetwork)) {

                    SocialNetwork socialNetwork = new SocialNetwork();
                    socialNetwork.setLink(editTextSocialNetwork.getText().toString());
                    contact.getSocialNetworks().add(socialNetwork);


                    Toast.makeText(NewContactActivity.this, R.string.msg_social_add, Toast.LENGTH_LONG).show();
                    editTextSocialNetwork.setText("");
                }
            }
        });
    }

    private void bindButtonSave() {
        buttonCriar = (Button) findViewById(R.id.buttonSave);
        buttonCriar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String requiredMessage = getResources().getString(R.string.msg_field_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
                    bindContact();
                    ContactBussinessService.save(contact);

                    Toast.makeText(NewContactActivity.this, R.string.msg_contact_create_sucess, Toast.LENGTH_LONG).show();
                    NewContactActivity.this.finish();
                }
            }
        });
    }


    public void bindContact(){
        contact.setName(editTextName.getText().toString());
        contact.getAddress().setZipCode(editTextZipCode.getText().toString());
        contact.getAddress().setCity(editTextCity.getText().toString());
        contact.getAddress().setStreet(editTextStreet.getText().toString());
        contact.getAddress().setNeighborhood(editTextNeighborhood.getText().toString());
        contact.getAddress().setState(editTextState.getText().toString());
        contact.getAddress().setType(editTextType.getText().toString());
    }


    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new GetAddressTask().execute(editTextZipCode.getText().toString());
            }
        });
    }

    public void initContact(){
        Bundle extras = getIntent().getExtras();
        Contact contact = null;
        Long id;
        if (extras != null) {
            id = extras.getLong(ListContactActivity.PARAM_CONTACT);
           contact = ContactBussinessService.getById(id);
        }

        this.contact = contact == null ? new Contact() : ContactBussinessService.getAllDependencies(contact);
    }

    public void bindFields(){
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(contact.getName() == null ? "" : contact.getName());

        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);

        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextStreet.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());

        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextNeighborhood.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());

        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextCity.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());

        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextType.setText(contact.getAddress().getType() == null ? "" : contact.getAddress().getType());

        editTextState = (EditText) findViewById(R.id.editTextState);
        editTextState.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSocialNetwork = (EditText) findViewById(R.id.editTextSocialNetwork);
        editTextTelephone = (EditText) findViewById(R.id.editTextTelephone);
    }



    private class GetAddressTask extends AsyncTask<String, Void, Address> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NewContactActivity.this);
            progressDialog.setMessage("Carregando");
            progressDialog.show();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddressByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
            progressDialog.dismiss();
            editTextCity.setText(address.getCity().toString());
            editTextState.setText(address.getState().toString());
            editTextType.setText(address.getType().toString());
            editTextNeighborhood.setText(address.getNeighborhood().toString());
            editTextStreet.setText(address.getStreet().toString());
            contact.setAddress(address);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
