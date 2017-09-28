package mumayank.mvp;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Mayank on 28-09-2017.
 */

class MainPresenter {

    private MainInterfaces.View view;
    private MainInterfaces.Model model;
    private BgAsyncTask bgAsyncTask;

    private ArrayList<Item> customizedItems;

    MainPresenter(MainInterfaces.View view) {
        this.view = view;
        model = new SmsHelper();
    }

    void attachView(MainInterfaces.View view) {
        this.view = view;
    }

    void detachView() {
        this.view = null;
    }

    void fetchData() {
        bgAsyncTask = new BgAsyncTask();
        bgAsyncTask.execute(null, null, null);
    }

    void stopAsyncTask() {
        if (bgAsyncTask != null) {
            bgAsyncTask.cancel(true);
        }
    }

    ArrayList<Item> getItems() {
        return customizedItems;
    }

    private class BgAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // call model to fetch data
            ArrayList<Item> items = model.getData(view.getActivity());

            // increase load 100 times
            customizedItems = new ArrayList<>();
            for (Item item : items) {
                for (int i=0; i<100; i++) {
                    item.setBody("SMS: " + item.getBody());
                    customizedItems.add(item);
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (view != null) {
                view.showData(customizedItems);
            }
        }
    }

}
