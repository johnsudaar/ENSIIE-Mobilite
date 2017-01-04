package com.ensiie.tp4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.ensiie.tp4.database.Todo;

import java.util.ArrayList;

/**
 * Created by john on 13/12/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ensiie";
    // Définition des tables
    interface Tables {
        String TODO = "todo";
    }
    // Définition des colonnes pour chaque table
    interface TodoColumns extends BaseColumns {
        String TODO_TEXT = "todo_text";
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Tables.TODO+" ("
            + BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TodoColumns.TODO_TEXT+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Tables.TODO);
        onCreate(db);
    }

    public void addTodo(Todo t){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoColumns.TODO_TEXT, t.getText());
        db.insert(Tables.TODO, null, values);
        db.close();
    }

    public ArrayList<Todo> getTodos(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                Tables.TODO,
                new String[]{
                        BaseColumns._ID,
                        TodoColumns.TODO_TEXT,
                },
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Todo> todos = new ArrayList<Todo>();
        if(cursor.moveToFirst()){
            do{
                Todo td = new Todo(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_TEXT)));
                todos.add(td);
            }while(cursor.moveToNext());
        }

        return todos;
    }
}
