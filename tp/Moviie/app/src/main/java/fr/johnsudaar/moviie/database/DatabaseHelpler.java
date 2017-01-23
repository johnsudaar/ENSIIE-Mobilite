package fr.johnsudaar.moviie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import fr.johnsudaar.moviie.models.Movie;


public class DatabaseHelpler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "moviie";

    public DatabaseHelpler(Context ctx) {
        super(ctx,DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Movie.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Movie.upgrade(db, i, i1);
    }

    public SQLiteDatabase getDb() {
        return getWritableDatabase();
    }
}
