package id.ac.polman.astra.serojamatchmaker.utils;

import android.util.DisplayMetrics;

import id.ac.polman.astra.serojamatchmaker.application.BracketsApplication;


/**
 * Created by Emil on 21/10/17.
 */

public class BracketsUtility {
    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = BracketsApplication.getInstance().getBaseContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
