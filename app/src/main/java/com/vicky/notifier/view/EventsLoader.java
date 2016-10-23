package com.vicky.notifier.view;

import android.content.Context;
import android.database.Cursor;
import com.vicky.notifier.database.DatabaseConstants;
import com.vicky.notifier.database.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class EventsLoader {
    public static EventsAdapter initEventsAdapter(Context context) {
        Cursor results = new DatabaseHelper(context).getAllEventsCursor();
        List<EventDetails> eventDetailsList = new ArrayList();
        if (results.getCount() > 0) {
            results.moveToFirst();
            do {
                int day = results.getInt(results.getColumnIndex(DatabaseConstants.EVENT_DAY));
                int month = results.getInt(results.getColumnIndex(DatabaseConstants.EVENT_MONTH));
                eventDetailsList.add(new EventDetails(day, DatabaseConstants.MONTH_NAMES[month], results.getString(results.getColumnIndex(DatabaseConstants.EVENT_NAME))));
            } while (results.moveToNext());
        }
        results.close();
        return new EventsAdapter(eventDetailsList);
    }
}
