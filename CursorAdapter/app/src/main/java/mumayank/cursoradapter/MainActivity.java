package mumayank.cursoradapter;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    public static final int CONTACT_LOADER_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityPermissionsDispatcher.callPhoneWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    void callPhone() {
        setupCursorAdapter();

        getLoaderManager().initLoader(CONTACT_LOADER_ID, new Bundle(), contactsLoader);

        ListView lvContacts = (ListView) findViewById(R.id.listView);
        lvContacts.setAdapter(adapter);
    }

    private void setupCursorAdapter() {
        // Column data from cursor to bind views from
        String[] uiBindFrom = { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_URI };
        // View IDs which will have the respective column data inserted
        int[] uiBindTo = { R.id.textView, R.id.imageView };
        // Create the simple cursor adapter to use for our list
        // specifying the template to inflate (item_contact),
        adapter = new SimpleCursorAdapter(
                this, R.layout.item_listview,
                null, uiBindFrom, uiBindTo,
                0);
    }

    // Defines the asynchronous callback for the contacts data loader
    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                // Create and return the actual cursor loader for the contacts data
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    // Define the columns to retrieve
                    String[] projectionFields = new String[] { ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            ContactsContract.Contacts.PHOTO_URI };
                    // Construct the loader
                    CursorLoader cursorLoader = new CursorLoader(MainActivity.this,
                            ContactsContract.Contacts.CONTENT_URI, // URI
                            projectionFields, // projection fields
                            null, // the selection criteria
                            null, // the selection args
                            null // the sort order
                    );
                    // Return the loader for use
                    return cursorLoader;
                }

                // When the system finishes retrieving the Cursor through the CursorLoader,
                // a call to the onLoadFinished() method takes place.
                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    // The swapCursor() method assigns the new Cursor to the adapter
                    adapter.swapCursor(cursor);
                }

                // This method is triggered when the loader is being reset
                // and the loader data is no longer available. Called if the data
                // in the provider changes and the Cursor becomes stale.
                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    // Clear the Cursor we were using with another call to the swapCursor()
                    adapter.swapCursor(null);
                }
            };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
