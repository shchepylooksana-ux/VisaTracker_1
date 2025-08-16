package com.visacenter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import com.visacenter.model.VisaEntry;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "visa_center.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE visas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "from_person TEXT," +
                "name TEXT," +
                "country TEXT," +
                "visaType TEXT," +
                "requestDate TEXT," +
                "submitDate TEXT," +
                "resultDate TEXT," +
                "paid REAL," +
                "expenses REAL," +
                "debt REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS visas");
        onCreate(db);
    }

    public long insert(VisaEntry e) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("from_person", e.from);
        cv.put("name", e.name);
        cv.put("country", e.country);
        cv.put("visaType", e.visaType);
        cv.put("requestDate", e.requestDate);
        cv.put("submitDate", e.submitDate);
        cv.put("resultDate", e.resultDate);
        cv.put("paid", e.paid);
        cv.put("expenses", e.expenses);
        cv.put("debt", e.debt);
        return db.insert("visas", null, cv);
    }

    public List<VisaEntry> getAll() {
        ArrayList<VisaEntry> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM visas ORDER BY id DESC", null);
        try {
            while (c.moveToNext()) {
                VisaEntry e = new VisaEntry();
                e.id = c.getLong(c.getColumnIndexOrThrow("id"));
                e.from = c.getString(c.getColumnIndexOrThrow("from_person"));
                e.name = c.getString(c.getColumnIndexOrThrow("name"));
                e.country = c.getString(c.getColumnIndexOrThrow("country"));
                e.visaType = c.getString(c.getColumnIndexOrThrow("visaType"));
                e.requestDate = c.getString(c.getColumnIndexOrThrow("requestDate"));
                e.submitDate = c.getString(c.getColumnIndexOrThrow("submitDate"));
                e.resultDate = c.getString(c.getColumnIndexOrThrow("resultDate"));
                e.paid = c.getDouble(c.getColumnIndexOrThrow("paid"));
                e.expenses = c.getDouble(c.getColumnIndexOrThrow("expenses"));
                e.debt = c.getDouble(c.getColumnIndexOrThrow("debt"));
                list.add(e);
            }
        } finally {
            c.close();
        }
        return list;
    }

    public Totals getTotals() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) AS cnt, IFNULL(SUM(paid),0) AS paid, IFNULL(SUM(expenses),0) AS expenses FROM visas", null);
        try {
            if (c.moveToFirst()) {
                int count = c.getInt(c.getColumnIndexOrThrow("cnt"));
                double paid = c.getDouble(c.getColumnIndexOrThrow("paid"));
                double expenses = c.getDouble(c.getColumnIndexOrThrow("expenses"));
                return new Totals(count, paid, expenses, paid - expenses);
            }
        } finally {
            c.close();
        }
        return new Totals(0,0,0,0);
    }

    public static class Totals {
        public final int count;
        public final double paid;
        public final double expenses;
        public final double net;
        public Totals(int c, double p, double e, double n){ count=c; paid=p; expenses=e; net=n; }
    }
}