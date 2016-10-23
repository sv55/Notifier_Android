package com.vicky.notifier.database;

import static com.vicky.notifier.database.DatabaseConstants.EVENT_ID;
import static com.vicky.notifier.database.DatabaseConstants.EVENTS_TABLE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_DAY;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_MONTH;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_NAME;
import static com.vicky.notifier.database.DatabaseConstants.MONTH_NAMES;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vicky.notifier.view.EventDetails;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "events", null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + EVENTS_TABLE_NAME + " (" +
                EVENT_ID + " text primary key," +
                EVENT_DAY + " integer," +
                EVENT_MONTH + " integer," +
                EVENT_NAME + " text)");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public Cursor getAllEventsCursor() {
        return getReadableDatabase().query(true,
                EVENTS_TABLE_NAME,
                new String[] {EVENT_DAY,
                        EVENT_MONTH,
                        EVENT_NAME}, null, null, null, null, EVENT_MONTH, null);
    }

    public void insertEvent(EventDetails eventDetails) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_ID, eventDetails.getEventID());
        contentValues.put(EVENT_DAY, eventDetails.getDay());
        contentValues.put(EVENT_MONTH, eventDetails.getMonth());
        contentValues.put(EVENT_NAME, eventDetails.getEvent());
        getWritableDatabase().insert(EVENTS_TABLE_NAME, null, contentValues);
    }

    public EventDetails getEventDetail(String eventID) {
        EventDetails eventDetails = null;
        Cursor cursor = getReadableDatabase().query(EVENTS_TABLE_NAME,
                new String[] {EVENT_DAY,
                        EVENT_MONTH,
                        EVENT_NAME}, EVENT_ID + "=?",
                new String[] {eventID}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int day = cursor.getInt(cursor.getColumnIndex(EVENT_DAY));
            int month = cursor.getInt(cursor.getColumnIndex(EVENT_MONTH));
            eventDetails = new EventDetails(cursor.getString(cursor.getColumnIndex(EVENT_ID)),
                    day, MONTH_NAMES[month],
                    cursor.getString(cursor.getColumnIndex(EVENT_NAME)));
            cursor.close();
        }
        return eventDetails;
    }
}
