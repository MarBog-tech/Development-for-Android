package com.lab3.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.lab3.DatabaseHelper;
import com.lab3.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_FIRM, DatabaseHelper.COLUMN_TYPE, DatabaseHelper.COLUMN_PRODUCT};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String firm = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FIRM));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE));
            String product = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT));
            products.add(new Product(id, firm, year, product));
        }
        cursor.close();
        return  products;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public Product getProduct(long id){
        Product products = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String firm = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FIRM));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE));
            String product = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT));
            products = new Product((int) id, firm, type, product);
        }
        cursor.close();
        return  products;
    }

    public long insert(Product product){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_FIRM, product.getFirm());
        cv.put(DatabaseHelper.COLUMN_TYPE, product.getType());
        cv.put(DatabaseHelper.COLUMN_PRODUCT, product.getProduct());

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Product product){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + product.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_FIRM, product.getFirm());
        cv.put(DatabaseHelper.COLUMN_TYPE, product.getType());
        cv.put(DatabaseHelper.COLUMN_PRODUCT, product.getProduct());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}
