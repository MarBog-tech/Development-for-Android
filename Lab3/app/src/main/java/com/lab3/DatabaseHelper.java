package com.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "products"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRM = "firm";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PRODUCT = "product";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE products (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRM
                + " TEXT, " + COLUMN_TYPE
                + " TEXT, " + COLUMN_PRODUCT + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_FIRM
                + ", " + COLUMN_TYPE
                + ", " + COLUMN_PRODUCT + ") VALUES ('Samsung', 'laptop', 'samsung laptop');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}