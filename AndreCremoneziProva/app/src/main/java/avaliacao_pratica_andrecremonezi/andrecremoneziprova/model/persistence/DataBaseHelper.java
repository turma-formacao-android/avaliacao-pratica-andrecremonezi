package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "taskmanagerdb";
    private static final int DATABASE_VERSION = 1;


    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getIstance(){
        return new DataBaseHelper(ApplicationUtil.getApplicationContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e("Prova", "Create Tabela Contact");
        db.execSQL(ContactContract.getCreateTableScript());

        Log.e("Task Manager", "Create Tabela Email");
        db.execSQL(EmailContract.getCreateTableScript());

        Log.e("Task Manager", "Create Tabela Social Network");
        db.execSQL(SocialNetworkContract.getCreateTableScript());

        Log.e("Task Manager", "Create Tabela Social Telephone");
        db.execSQL(TelephoneContract.getCreateTableScript());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
