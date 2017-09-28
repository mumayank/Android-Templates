package mumayank.mvp;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Mayank on 28-09-2017.
 */

class SmsHelper implements MainInterfaces.Model {

    @Override
    public ArrayList<Item> getData(Activity activity) {
        // get sms from inbox
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= activity.getContentResolver().query(uri, null, null ,null,null);

        ArrayList<Item> items = new ArrayList<>();

        if (c!=null) {
            if(c.moveToFirst()) {
                for(int i=0; i < c.getCount(); i++) {
                    String sender = c.getString(c.getColumnIndexOrThrow("address")).toString();
                    String date = c.getString(c.getColumnIndexOrThrow("date")).toString();
                    String body = c.getString(c.getColumnIndexOrThrow("body")).toString();

                    Item item = new Item(sender, date, body);
                    items.add(item);

                    c.moveToNext();
                }
            }

            c.close();
        }

        return items;
    }

}
