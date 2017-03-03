package skytest.com.skyengtest.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Internet {
    public static boolean isOnline(Context contxt) {
        ConnectivityManager cm =
                (ConnectivityManager) contxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
