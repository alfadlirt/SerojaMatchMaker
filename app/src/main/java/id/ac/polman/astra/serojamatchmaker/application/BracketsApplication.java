package id.ac.polman.astra.serojamatchmaker.application;

import android.app.Application;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsApplication extends Application {

    private int screenHeight;
    private static BracketsApplication applicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        //applicationInstance = this;
            applicationInstance = BracketsApplication.this;
    }

    public static synchronized BracketsApplication getInstance() {
        return applicationInstance;
    }



    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
}
