package mrdoank.blogspot.co.id.quotes.base;

import android.app.Application;

import mrdoank.blogspot.co.id.quotes.utility.DatabaseHandler;
import mrdoank.blogspot.co.id.quotes.utility.SessionManager;

/**
 * Created by root on 03/12/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
        DatabaseHandler.init(this);
    }
}
