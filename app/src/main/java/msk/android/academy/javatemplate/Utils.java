package msk.android.academy.javatemplate;

import android.util.Log;
import android.view.View;

public class Utils {

    private static final String LOG_TAG = "myLogs";

    public static void log(String message) {
        Log.d(LOG_TAG, message);
    }

    public static void setVisible(View view, boolean show) {
        if (view == null) return;

        int visibility = show ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }
}
