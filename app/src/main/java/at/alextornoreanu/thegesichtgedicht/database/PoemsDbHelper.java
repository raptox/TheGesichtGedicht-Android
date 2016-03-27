package at.alextornoreanu.thegesichtgedicht.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by alex on 27.03.16.
 */
public class PoemsDbHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "Poems.db";
    private static final int DATABASE_VERSION = 1;

    private static final String POEMS_TABLE_NAME = "Poems";
    private static final String POEMS_COLUMN_AUTOR = "autor";
    private static final String POEMS_COLUMN_TITLE = "titel";
    private static final String POEMS_COLUMN_TEXT = "text";

    private static final int POEMS_RANDOM_NUMBER = 10;

    private SQLiteDatabase database;
    SQLiteQueryBuilder queryBuilder;
    String [] sqlSelect = {POEMS_COLUMN_AUTOR, POEMS_COLUMN_TITLE, POEMS_COLUMN_TEXT};

    public PoemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        readyUpDatabase();
    }

    private void readyUpDatabase() {
        database = getReadableDatabase();
        queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(POEMS_TABLE_NAME);
    }

    public Cursor getRandomPoems() {
        String sqlQuery = "SELECT * FROM " + POEMS_TABLE_NAME + " ORDER BY RANDOM() LIMIT " + POEMS_RANDOM_NUMBER;
        Cursor cursor = database.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getAllPoems() {
        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
}
