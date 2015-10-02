package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.SocialNetwork;

public class SocialNetworkRepository {
    public SocialNetworkRepository(){
        super();
    }

    public static void save(SocialNetwork socialNetwork) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = SocialNetworkContract.getContentValues(socialNetwork);

        if (socialNetwork.getId() == null) {

            db.insert(SocialNetworkContract.TABLE, null, values);

        } else {

            String where = SocialNetworkContract.ID + " = ? ";
            String[] params = {socialNetwork.getId().toString()};
            db.update(SocialNetworkContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = SocialNetworkContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(SocialNetworkContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<SocialNetwork> getAllByContact(Long idcontact) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = SocialNetworkContract.CONTACTID + " = ? ";
        String[] params = {idcontact.toString()};
        Cursor cursor = db.query(SocialNetworkContract.TABLE, SocialNetworkContract.COLUNS, where, params, null, null, SocialNetworkContract.ID);
        List<SocialNetwork> values = SocialNetworkContract.getSocialNetworks(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static List<SocialNetwork> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(SocialNetworkContract.TABLE, SocialNetworkContract.COLUNS, null, null, null, null, SocialNetworkContract.ID);
        List<SocialNetwork> values = SocialNetworkContract.getSocialNetworks(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }
}