package mumayank.dialogee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Mayank on 30-09-2017.
 */

public class Dialogee {

    /**
     * Interfaces
     */
    public interface PositiveButton {
        void action();
    }

    public interface NegativeButton {
        void action();
    }

    public interface NeutralButton {
        void action();
    }

    /**
     * Actual class
     */
    Activity activity;
    String title;
    String description;
    boolean isCancellable;
    String positiveButtonText;
    String negativeButtonText;
    String neutralButtonText;
    int icon;
    PositiveButton positiveButton;
    NegativeButton negativeButton;
    NeutralButton neutralButton;

    static final int NO_ICON = -111;

    AlertDialog dialog;

    Dialogee(Activity activity, String title, String description, boolean isCancellable, String positiveButtonText, String negativeButtonText, String neutralButtonText, int icon, final PositiveButton positiveButton, final NegativeButton negativeButton, final NeutralButton neutralButton) {
        this.activity = activity;
        this.title = title;
        this.description = description;
        this.isCancellable = isCancellable;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText = negativeButtonText;
        this.neutralButtonText = neutralButtonText;
        this.icon = icon;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.neutralButton = neutralButton;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(title)
                .setMessage(description);

        if (icon != NO_ICON) {
            builder.setIcon(icon);
        }

        if (neutralButtonText != null) {
            builder.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    neutralButton.action();
                }
            });
        }

        if (negativeButtonText != null) {
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    negativeButton.action();
                }
            });
        }

        if (positiveButtonText != null) {
            builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    positiveButton.action();
                }
            });
        }

        dialog = builder.create();
        dialog.setCancelable(isCancellable);
    }

    public void show() {
        dialog.show();
    }

    public static class Builder {

        Activity activity;
        String title;
        String description;
        boolean isCancellable = true; // default
        String positiveButtonText;
        String negativeButtonText;
        String neutralButtonText;
        int icon = NO_ICON; // default
        PositiveButton positiveButton;
        NegativeButton negativeButton;
        NeutralButton neutralButton;

        public Builder(Activity activity, String title) {
            this.activity = activity;
            this.title = title;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCancellable(boolean isCancellable) {
            this.isCancellable = isCancellable;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setNeutralButtonText(String neutralButtonText) {
            this.neutralButtonText = neutralButtonText;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setPositiveButtonAction(PositiveButton positiveButton) {
            this.positiveButton = positiveButton;
            return this;
        }

        public Builder setNegativeButtonAction(NegativeButton negativeButton) {
            this.negativeButton = negativeButton;
            return this;
        }

        public Builder setNeutralButtonAction(NeutralButton neutralButton) {
            this.neutralButton = neutralButton;
            return this;
        }

        public Dialogee build() {
            return new Dialogee(
                    activity,
                    title,
                    description,
                    isCancellable,
                    positiveButtonText,
                    negativeButtonText,
                    neutralButtonText,
                    icon,
                    positiveButton,
                    negativeButton,
                    neutralButton);
        }
    }
}
