package sg.edu.rp.c346.id20025312.oursingapore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shows.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SHOW = "Shows";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSGTableSql = "CREATE TABLE " + TABLE_SHOW + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createSGTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOW);
        onCreate(db);
    }

    public long insertShows(String name, String description, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);

        long result = db.insert(TABLE_SHOW, null, values);
        db.close();
        return result;
    }

    public ArrayList<Show> getAllShows() {
        ArrayList<Show> showsList = new ArrayList<Show>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS + " FROM " + TABLE_SHOW;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Show newShow = new Show(id, name, description, year, stars);
                showsList.add(newShow);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return showsList;
    }

    public ArrayList<Show> getAllShowsByStars(int starsFilter) {
        ArrayList<Show> showsList = new ArrayList<Show>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_YEAR, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_SHOW, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Show newShow = new Show(id, name, description, year, stars);
                showsList.add(newShow);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return showsList;
    }

    public int updateShow(Show data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SHOW, values, condition, args);
        db.close();
        return result;
    }

    public int deleteShow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SHOW, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getYear() {
        ArrayList<String> codes = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_YEAR};

        Cursor cursor;
        cursor = db.query(true, TABLE_SHOW, columns, null, null, null, null, null, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return codes;
    }

    public ArrayList<Show> getAllShowsByYear(int showFilter) {
        ArrayList<Show> showsList = new ArrayList<Show>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_YEAR, COLUMN_STARS};
        String condition = COLUMN_YEAR + "= ?";
        String[] args = {String.valueOf(showFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_SHOW, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Show newShow = new Show(id, name, description, year, stars);
                showsList.add(newShow);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return showsList;
    }

}
