package avaliacao_pratica_andrecremonezi.andrecremoneziprova.ApplicationManager;

import android.app.Application;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.util.ApplicationUtil;

/**
 * Created by Administrador on 02/10/2015.
 */
public class ApplicationManager extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setApplicationContext(getApplicationContext());
    }
}
