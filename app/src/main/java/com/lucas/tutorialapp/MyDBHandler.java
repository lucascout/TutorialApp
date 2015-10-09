package com.lucas.tutorialapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Lucas on 09/10/2015.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_TUTOSTEPS = "tutosteps";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LAYOUT = "layout";
    public static final String COLUMN_STEP = "step";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STEPS_TABLE = "CREATE TABLE " +
                TABLE_TUTOSTEPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_LAYOUT
                + " TEXT UNIQUE," + COLUMN_STEP + " INTEGER" + ")";
        db.execSQL(CREATE_STEPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTOSTEPS);
        onCreate(db);
    }

    public void addStep(tutoSteps steps) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_LAYOUT, steps.getLayout());
        values.put(COLUMN_STEP, steps.getStep());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_TUTOSTEPS, null, values);
        db.close();
    }

    public tutoSteps findProduct(String layout) {
        String query = "Select * FROM " + TABLE_TUTOSTEPS + " WHERE " + COLUMN_LAYOUT + " =  \"" + layout + "\" order by 3 desc";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        tutoSteps step = new tutoSteps();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            step.setID(Integer.parseInt(cursor.getString(0)));
            step.setLayout(cursor.getString(1));
            step.setStep(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            step = null;
        }
        db.close();
        return step;
    }

    public boolean deleteProduct(String layout) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_TUTOSTEPS + " WHERE " + COLUMN_LAYOUT + " =  \"" + layout + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        tutoSteps product = new tutoSteps();

        if (cursor.moveToFirst()) {
            product.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_TUTOSTEPS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(product.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}