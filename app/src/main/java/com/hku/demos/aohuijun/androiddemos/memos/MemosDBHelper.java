package com.hku.demos.aohuijun.androiddemos.memos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aohuijun on 16/2/7.
 */
public class MemosDBHelper extends SQLiteOpenHelper {

    public MemosDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Memos.CREATE_TABLE_MEMO);
        Log.d("Memos", "Database created. ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE memos ADD COLUMN time VARCHAR(20)");
    }
}
