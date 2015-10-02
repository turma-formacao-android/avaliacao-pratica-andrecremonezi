package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;

public class EmailContract {

    public static final String TABLE         = "email";
    public static final String ID            = "id";
    public static final String EMAILADDRESS  = "emailaddress";
    public static final String CONTACTID     = "contactid";

    public static final String[] COLUNS = {ID, EMAILADDRESS, CONTACTID};

    public EmailContract(){
        super();
    }


    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(EMAILADDRESS + " TEXT, ");
        create.append(CONTACTID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }


    public static ContentValues getContentValues(Email email) {
        ContentValues values = new ContentValues();

        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAILADDRESS, email.getEmailAddress());
        values.put(EmailContract.CONTACTID, email.getIdcontact());

        return values;
    }

    public static Email getEmail(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();
            email.setEmailAddress(cursor.getString(cursor.getColumnIndex(EmailContract.EMAILADDRESS)));
            email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
            email.setIdcontact(cursor.getLong(cursor.getColumnIndex(EmailContract.CONTACTID)));

            return email;
        }

        return null;
    }

    public static List<Email> getEmails(Cursor cursor) {
        ArrayList<Email> emails = new ArrayList<>();
        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }
        return emails;
    }
}