package mumayank.mvp;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

/**
 * Created by Mayank on 28-09-2017.
 */

public class MyAlertDialog {

    public static void show(Activity activity, String title, String message, String positiveButtonText, String negativeButtonText, int iconDrawableResourceId) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }

        final MainInterfaces.Dialog dialogs = (MainInterfaces.Dialog) activity;

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialogs.permissionGrantedProceed();
                    }
                })
                .setIcon(iconDrawableResourceId);

        if (negativeButtonText != null) {
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogs.permissionDeniedExit();
                }
            });
        }

        builder.show();
    }

}
