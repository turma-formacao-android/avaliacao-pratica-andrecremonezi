package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.SocialNetwork;

public class SocialNetworkContract {
    public static final String TABLE         = "socialnetwork";
    public static final String ID            = "id";
    public static final String LINK          = "link";
    public static final String CONTACTID     = "contactid";

    public static final String[] COLUNS = {ID, LINK, CONTACTID};

    public SocialNetworkContract(){
        super();
    }


    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(LINK + " TEXT, ");
        create.append(CONTACTID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }


    public static ContentValues getContentValues(SocialNetwork socialNetwork) {
        ContentValues values = new ContentValues();

        values.put(SocialNetworkContract.ID, socialNetwork.getId());
        values.put(SocialNetworkContract.LINK, socialNetwork.getLink());
        values.put(SocialNetworkContract.CONTACTID, socialNetwork.getIdcontact());

        return values;
    }

    public static SocialNetwork getSocialNetwork(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            SocialNetwork socialNetwork = new SocialNetwork();

            socialNetwork.setLink(cursor.getString(cursor.getColumnIndex(SocialNetworkContract.LINK)));
            socialNetwork.setIdcontact(cursor.getLong(cursor.getColumnIndex(SocialNetworkContract.CONTACTID)));
            socialNetwork.setId(cursor.getLong(cursor.getColumnIndex(SocialNetworkContract.ID)));
        }
        return null;
    }

    public static List<SocialNetwork> getSocialNetworks(Cursor cursor) {
        ArrayList<SocialNetwork> socialNetworks = new ArrayList<>();
        while (cursor.moveToNext()) {
            socialNetworks.add(getSocialNetwork(cursor));
        }
        return socialNetworks;
    }
}
