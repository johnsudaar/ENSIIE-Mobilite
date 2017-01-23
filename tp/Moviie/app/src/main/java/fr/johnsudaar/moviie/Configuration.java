package fr.johnsudaar.moviie;

import fr.johnsudaar.moviie.database.DatabaseHelpler;
import fr.johnsudaar.moviie.models.User;


public class Configuration {
    private DatabaseHelpler dbHelper = null;
    private User me;

    private static Configuration configuration = null;

    private Configuration(){}

    public static Configuration get(){
        if(configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }

    public DatabaseHelpler getDbHelper() {
        return dbHelper;
    }

    public void setLoggedInUser(User u){
        this.me = u;
    }

    public User getLoggedInUser(){
        return this.me;
    }


    public void setDbHelper(DatabaseHelpler dbHelper) {
        this.dbHelper = dbHelper;
    }
}
