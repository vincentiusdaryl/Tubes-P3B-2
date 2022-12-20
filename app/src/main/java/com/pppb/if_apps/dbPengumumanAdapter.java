package com.pppb.if_apps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbPengumumanAdapter extends SQLiteOpenHelper {

    private static final String DB_Name = "Pengumuman.db";
    private static final String DB_TABLE = "PENGUMUMAN_TABLE";

    //Kolom
    public static final String KEY_ID = "_id";
    public static final String KEY_JUDUL = "judulTEXT";
    public static final String KEY_TAGS = "tagsTEXT";
    public static final String KEY_DESKRIPSI= "deskripsiTEXT";

    public static final String[] ALL_KEYS = new String[]{KEY_ID,KEY_JUDUL,KEY_TAGS,KEY_DESKRIPSI};
    //bikinTabel
    public static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_JUDUL+ " TEXT,"+
            KEY_TAGS+ " TEXT,"+
            KEY_DESKRIPSI+ " TEXT"+
            ")" ;

    public dbPengumumanAdapter(Context context){
        super(context,DB_Name,null,6);
    }
    public void open(){
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertBaris(String judul,String tags,String deskripsi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_JUDUL,judul);
        initialValues.put(KEY_TAGS,tags);
        initialValues.put(KEY_DESKRIPSI,deskripsi);
        //Insert data ke DB
        return db.insert(DB_TABLE,null,initialValues);
    }

    public boolean deleteBaris(long rowID){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID + "=" + rowID;
        return db.delete(DB_TABLE,where,null)!=0;
    }

    public boolean deleteBarisNama(String namaDokter){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE, KEY_JUDUL + "=?", new String[]{String.valueOf(namaDokter)})>0;
    }

    public void deleteAll(){

        Cursor c = getSemuaBaris();
        long rowID = c.getColumnIndexOrThrow(KEY_ID);
        if(c.moveToFirst()){
            do{
                deleteBaris(c.getLong((int) rowID));
            } while (c.moveToNext());
        }
        c.close();
    }
    //Return data-data di DB
    public Cursor getSemuaBaris(){
        SQLiteDatabase db = this.getReadableDatabase();
        String where = null;
        Cursor c = db.query(true,DB_TABLE,ALL_KEYS, where ,null,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public ArrayList<pengumuman> getpengumuman()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<pengumuman> arrayList = new ArrayList<>();
        String query = "Select * from " + DB_TABLE;
        Cursor cursor = db.query(true,DB_TABLE,ALL_KEYS, null ,null,null,null,null,null);
        while (cursor.moveToNext()){
            String judul = cursor.getString(0);
            String tags = cursor.getString(1);
            String deskripsi = cursor.getString(2);
            pengumuman pengumuman = new pengumuman(judul,tags,deskripsi);
            arrayList.add(pengumuman);
        }
        return arrayList;
    }

    public Cursor getBaris(long rowID){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID + "=" + rowID;
        Cursor c = db.query(true,DB_TABLE,ALL_KEYS,where,null,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public boolean updateBaris(long rowID,String judul,String tags,String deskripsi){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID + "=" + rowID;
        ContentValues isi = new ContentValues();
        isi.put(KEY_JUDUL,judul);
        isi.put(KEY_TAGS,tags);
        isi.put(KEY_DESKRIPSI,deskripsi);
        //insert ke DB
        return db.update(DB_TABLE,isi,where,null)!=0;
    }


}
