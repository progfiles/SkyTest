package skytest.com.skyengtest.mvp.code;


import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface CodeView  extends MvpView {
    EditText getCodeView();
    void onButtonEnable(boolean ednable);
    void onTick(long time);
    void onResetButtonEnable(boolean enalble);
    void showProgress();
    void hideProgress();
    void onLoginError();
    void onConnectError();
}
