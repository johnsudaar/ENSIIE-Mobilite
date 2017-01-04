package com.ensiie.tp4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ensiie";

    interface Tables {
        String TODO = "todo";
    }

    interface TodoColumns extends BaseColumns {
        String TODO_TEXT = "todo_text";
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.TODO + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TodoColumns.TODO_TEXT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TODO);
        onCreate(db);
    }

    public void addTodo(Todo todo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoColumns.TODO_TEXT, todo.getText());

        db.insert(Tables.TODO, null, values);
        db.close();
    }

    public Todo getTodo(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Todo todo = null;

        String selectQuery =
                "SELECT * FROM " + Tables.TODO +
                        " WHERE " + TodoColumns._ID + "=" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            todo = new Todo(
                    cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                    cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_TEXT)));
        }
        cursor.close();
        db.close();
        return todo;
    }

    public ArrayList<Todo> getAllTodos() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Todo> todos = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Tables.TODO;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo(
                        cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                        cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_TEXT)));
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return todos;
    }

    public int updateTodo(Todo todo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoColumns.TODO_TEXT, todo.getText());

        int res = db.update(
                Tables.TODO,
                values,
                BaseColumns._ID + " = ?",
                new String[]{String.valueOf(todo.getId())}
        );
        db.close();
        return res;
    }

    public int deleteTodo(Todo todo) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(
                Tables.TODO,
                BaseColumns._ID + " = ?",
                new String[]{String.valueOf(todo.getId())}
        );
        db.close();
        return res;
    }
}
