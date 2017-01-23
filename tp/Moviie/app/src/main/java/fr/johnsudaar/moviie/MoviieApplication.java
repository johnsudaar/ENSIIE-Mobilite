package fr.johnsudaar.moviie;

import android.app.Application;

import fr.johnsudaar.moviie.database.DatabaseHelpler;

public class MoviieApplication extends Application {
    @Override
    public void onCreate(){
        Configuration.get().setDbHelper(new DatabaseHelpler(getApplicationContext()));
    }
}
