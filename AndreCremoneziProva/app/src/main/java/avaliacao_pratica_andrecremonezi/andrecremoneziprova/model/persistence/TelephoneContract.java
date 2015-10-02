package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Telephone;

public class TelephoneContract {

    public static final String TABLE         = "telephone";
    public static final String ID            = "id";
    public static final String NUMBER        = "numer";
    public static final String CONTACTID     = "contactid";

    public static final String[] COLUNS = {ID, NUMBER, CONTACTID};

    public TelephoneContract(){
        super();
    }


    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NUMBER + " TEXT, ");
        create.append(CONTACTID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }


    public static ContentValues getContentValues(Telephone telephone) {
        ContentValues values = new ContentValues();

        values.put(TelephoneContract.ID, telephone.getId());
        values.put(TelephoneContract.NUMBER, telephone.getNumber());
        values.put(TelephoneContract.CONTACTID, telephone.getIdcontact());

        return values;
    }

    public static Telephone getTelephone(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Telephone telephone = new Telephone();

            telephone.setNumber(cursor.getString(cursor.getColumnIndex(TelephoneContract.NUMBER)));
            telephone.setIdcontact(cursor.getLong(cursor.getColumnIndex(TelephoneContract.CONTACTID)));
            telephone.setId(cursor.getLong(cursor.getColumnIndex(TelephoneContract.ID)));
        }
        return null;
    }

    public static List<Telephone> getTelephones(Cursor cursor) {
        ArrayList<Telephone> telephones = new ArrayList<>();
        while (cursor.moveToNext()) {
            telephones.add(getTelephone(cursor));
        }
        return telephones;
    }

}
