package com.example.parcialnumero3samuel.persistences.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.parcialnumero3samuel.Util.Util;

import java.util.Objects;

public class ConnectHelper extends SQLiteOpenHelper {

    public ConnectHelper(@Nullable Context context) {
        super(context, Util.DB_NAME, null, Util.VERSION_SQL);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Util.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Util.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
