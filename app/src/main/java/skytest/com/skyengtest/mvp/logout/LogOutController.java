package skytest.com.skyengtest.mvp.logout;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import skytest.com.skyengtest.R;
import skytest.com.skyengtest.mvp.ButterKnifeController;

public class LogOutController extends ButterKnifeController {
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_logout, container, false);
    }
}
