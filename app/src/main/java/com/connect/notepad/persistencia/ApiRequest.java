package com.connect.notepad.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.connect.notepad.modelo.Notes;

public class ApiRequest {
    private Database database;
    private SQLiteDatabase banco;

    public ApiRequest(Context context) {
        database = new Database(context);
        banco = database.getWritableDatabase();
    }

    public long insert(Notes notes){
        ContentValues values = new ContentValues();
        values.put("title", notes.getTitle());
        values.put("description", notes.getDescription());
        values.put("color", notes.getColor());
        return banco.insert("agenda", null, values);
    }

    public long update(Notes notes){
        ContentValues values = new ContentValues();
        values.put("title", notes.getTitle());
        values.put("description", notes.getDescription());
        values.put("color", notes.getColor());
        return banco.update("agenda",  values, "_id = ?", new String[]{String.valueOf(notes.getId())});
    }

    public boolean excluir(int id){
        return banco.delete("agenda", "_id = ?", new String[]{ id + "" }) > 0;
    }
}
