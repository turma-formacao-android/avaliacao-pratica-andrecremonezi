package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Address;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;

public class ContactRepository {

    public ContactRepository(){
        super();
    }

    public static void save(Contact contact) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = ContactContract.getContentValues(contact);

        if (contact.getId() == null) {

            long insert = db.insert(ContactContract.TABLE, null, values);
            contact.setId(insert);

        } else {

            String where = ContactContract.ID + " = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static List<Contact> getByName(String name){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = ContactContract.NAME+" like ? ";
        String params[] = {"%"+name+"%"};

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, where, params, null, null, ContactContract.NAME);

        List<Contact> contactList = getContacts(cursor);

        dataBaseHelper.close();
        db.close();

        return contactList;
    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(ContactContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Contact> getContacts(Cursor cursor) {

        List<Contact> contacts = new ArrayList<>();

        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }

        return contacts;
    }

    private static Contact getContact(Cursor cursor) {


        while (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.getAddress().setZipCode(cursor.getString(cursor.getColumnIndex(ContactContract.ZIPCODE)));
            contact.getAddress().setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
            contact.getAddress().setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
            contact.getAddress().setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));
            contact.getAddress().setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));

            return contact;
        }
        return null;
    }

    public static List<Contact> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, null, null, null, null, ContactContract.ID);
        List<Contact> values = ContactContract.getContacts(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }



    public static Contact getById(Long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(ContactContract.TABLE,ContactContract.COLUNS,where,params,null,null,null);

        Contact contact = ContactContract.getContact(cursor);

        db.close();
        dataBaseHelper.close();

        return contact;
    }
}