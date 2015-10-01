package avaliacao_pratica_andrecremonezi.andrecremoneziprova.util;

import android.content.Context;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ApplicationUtil {

    private static Context applicationContext;

    private ApplicationUtil(){
        super();
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContext) {
        ApplicationUtil.applicationContext = applicationContext;
    }
}
