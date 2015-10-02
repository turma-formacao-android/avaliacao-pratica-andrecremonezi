package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;

public class EmailRepository {

    public EmailRepository(){
        super();
    }

    public static void save(Email email) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = EmailContract.getContentValues(email);

        if (email.getId() == null) {

            db.insert(EmailContract.TABLE, null, values);

        } else {

            String where = EmailContract.ID + " = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = EmailContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(EmailContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Email> getAllByContact(Long idcontact) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = EmailContract.CONTACTID + " = ? ";
        String[] params = {idcontact.toString()};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, where, params, null, null, EmailContract.ID);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static List<Email> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, null, null, null, null, EmailContract.ID);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }
}
