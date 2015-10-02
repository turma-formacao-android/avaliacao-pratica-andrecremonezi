package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Telephone;

public class TelephoneRepository {

    public TelephoneRepository(){
        super();
    }

    public static void save(Telephone telephone) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = TelephoneContract.getContentValues(telephone);

        if (telephone.getId() == null) {

            db.insert(TelephoneContract.TABLE, null, values);

        } else {

            String where = TelephoneContract.ID + " = ? ";
            String[] params = {telephone.getId().toString()};
            db.update(TelephoneContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = TelephoneContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(TelephoneContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Telephone> getAllByContact(Long idcontact) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = SocialNetworkContract.CONTACTID + " = ? ";
        String[] params = {idcontact.toString()};
        Cursor cursor = db.query(TelephoneContract.TABLE, TelephoneContract.COLUNS, where, params, null, null, TelephoneContract.ID);
        List<Telephone> values = TelephoneContract.getTelephones(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }

    public static List<Telephone> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TelephoneContract.TABLE, TelephoneContract.COLUNS, null, null, null, null, TelephoneContract.ID);
        List<Telephone> values = TelephoneContract.getTelephones(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }

}
