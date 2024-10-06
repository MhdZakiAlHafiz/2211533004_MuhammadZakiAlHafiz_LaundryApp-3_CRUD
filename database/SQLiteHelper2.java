package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.ModelLayanan;
import model.ModelPelanggan;

public class SQLiteHelper2 extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "my_laundry2.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_LAYANAN = "layanan";
    public static final String KEY_LAYANAN_ID = "id";
    public static final String KEY_LAYANAN_NAMA = "nama";
    public static final String KEY_LAYANAN_HARGA = "harga";
    private static final String CREATE_TABLE_LAYANAN = "CREATE TABLE " + TABLE_LAYANAN + " ("
            + KEY_LAYANAN_ID + " TEXT PRIMARY KEY, " // ID layanan sebagai PRIMARY KEY
            + KEY_LAYANAN_NAMA + " TEXT NOT NULL, "   // Nama layanan
            + KEY_LAYANAN_HARGA + " TEXT NOT NULL" +  // Harga layanan
            ");";

    public SQLiteHelper2(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LAYANAN);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAYANAN);
        onCreate(db);
    }
    public boolean insertlayanan(ModelLayanan ml){
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_LAYANAN_ID, ml.getId());
        contentValues.put(KEY_LAYANAN_NAMA, ml.getNama());
        contentValues.put(KEY_LAYANAN_HARGA, ml.getHarga());
        long id = database.insert(TABLE_LAYANAN, null, contentValues);
        database.close();
        if (id != -1){
            return true;
        }else{
            return false;
        }
    }
    public List<ModelLayanan> getModelLayanan(){
        List<ModelLayanan> pel = new ArrayList<ModelLayanan>();
        String query = "SELECT * FROM " + TABLE_LAYANAN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                ModelLayanan k = new ModelLayanan();
                k.setId(cursor.getString(0));
                k.setNama(cursor.getString(1));
                k.setHarga(cursor.getString(2));
                pel.add(k);
            }while (cursor.moveToNext());
        }
        return pel;
    }
}
