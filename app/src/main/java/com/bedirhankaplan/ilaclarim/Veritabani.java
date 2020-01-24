package com.bedirhankaplan.ilaclarim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Ilac";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLO_ILAC = "ilac";
    private static final String ROW_ID = "id";
    private static final String ROW_ILAC = "ilacAd";
    private static final String ROW_ADET = "ilacAdet";
    private static final String ROW_TARİH = "tarih";

    public Veritabani(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_ILAC + "("
                    + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ROW_ILAC + " TEXT NOT NULL, "
                    + ROW_ADET + " TEXT NOT NULL, "
                    + ROW_TARİH + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_ILAC);
        onCreate(db);
    }

    public  void VeriEkle(String ilac, String adet, String tarih) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_ILAC, ilac);
            cv.put(ROW_ADET, adet);
            cv.put(ROW_TARİH, tarih);
            db.insert(TABLO_ILAC,null,cv);
        }catch (Exception e){
        }
        db.close();
    }
    public void VeriDuzenle(int id, String ilac, String adet, String tarih) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_ID, id);
            cv.put(ROW_ILAC, ilac);
            cv.put(ROW_ADET, adet);
            cv.put(ROW_TARİH, tarih);
            db.update(TABLO_ILAC,cv, "id=" + id,null);
        }catch (Exception e){}
        db.close();
    }
    public void VeriSilme(int id) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            String where = ROW_ID  + " = '" + id + "'";
            db.delete(TABLO_ILAC,where, null);
        }catch (Exception e) {}
        db.close();
    }

    public List<String> VeriListele() {
        List<String> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String [] sutunlar = {ROW_ID, ROW_ILAC, ROW_ADET, ROW_TARİH};
            Cursor cursor = db.query(TABLO_ILAC,sutunlar,null,null,null,null,null);
            while (cursor.moveToNext()) {
                veriler.add( cursor.getInt(0) + " - " +
                             cursor.getString(1) + " - " +
                             cursor.getString(2) + " - " +
                             cursor.getString(3));
            }
        }catch (Exception e){
        }
        db.close();
        return veriler;
    }
}