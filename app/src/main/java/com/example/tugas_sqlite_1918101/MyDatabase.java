package com.example.tugas_sqlite_1918101;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_kaktus";

    private static final String tb_cactus = "tb_cactus";

    private static final String tb_cactus_id = "id";
    private static final String tb_cactus_nama = "nama";
    private static final String tb_cactus_jenis = "jenis";

    private static final String CREATE_TABLE_CACTUS = "CREATE " +
            "TABLE "
            + tb_cactus +"("
            + tb_cactus_id + " INTEGER PRIMARY KEY ,"
            + tb_cactus_nama + " TEXT ,"
            + tb_cactus_jenis + " TEXT " + ")";

    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CACTUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {

    }

    public void CreateCactus(Cactus data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_cactus_id, data.get_id());
        values.put(tb_cactus_nama, data.get_nama());
        values.put(tb_cactus_jenis, data.get_jenis());
        db.insert(tb_cactus, null, values);
        db.close();
    }

    public List<Cactus> ReadCactus() {
        List<Cactus> listCts = new ArrayList<Cactus>();
        String selectQuery = "SELECT  * FROM " + tb_cactus;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cactus data = new Cactus();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_jenis(cursor.getString(2));
                listCts.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listCts;
    }

    public int UpdateCactus (Cactus data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_cactus_nama, data.get_nama());
        values.put(tb_cactus_jenis, data.get_jenis());

        return db.update(tb_cactus, values, tb_cactus_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }

    public void DeleteCactus(Cactus data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_cactus,tb_cactus_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
