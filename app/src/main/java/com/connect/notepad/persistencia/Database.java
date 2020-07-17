package com.connect.notepad.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
        private static final String db_name = "bancos.db";
        private static final int version = 1;

    public Database(@Nullable Context context) {
            super(context, db_name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE agenda(_id integer primary key autoincrement, title text not null, description text not null, color int not null, date text NOT NULL DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime'))) ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE agenda");
            onCreate(db);
        }
}
