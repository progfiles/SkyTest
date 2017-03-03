package skytest.com.skyengtest.mvp.email;


import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface EmailLoginView extends MvpView {
    void navigateToSimpleLogin();
    void navigateToLogout();
    EditText getEmailView();
    EditText getPassView();
    void onButtonEnable(boolean enable);
    void showProgress();
    void hideProgress();
    void onLoginError();
    void onConnectError();

}
