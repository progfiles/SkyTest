package skytest.com.skyengtest.mvp.login;

import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpView;


interface LoginView extends MvpView {
    void navigateToCode();
    void navigateToEmailLogin();
    void onButtonEnable(boolean enable);
    EditText getEmailView();
    void showProgress();
    void hideProgress();
    void onLoginError();
    void onConnectError();
}
