package mumayank.mvp;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Mayank on 28-09-2017.
 */

public interface MainInterfaces {

    interface View {
        void showData(ArrayList<Item> items);
        Activity getActivity();
    }

    interface Model {
        ArrayList<Item> getData(Activity activity);
    }

    interface Dialog {
        void permissionGrantedProceed();
        void permissionDeniedExit();
    }

}
