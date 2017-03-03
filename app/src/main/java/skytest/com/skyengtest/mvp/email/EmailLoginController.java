package skytest.com.skyengtest.mvp.email;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skytest.com.skyengtest.R;
import skytest.com.skyengtest.mvp.login.LoginController;
import skytest.com.skyengtest.mvp.logout.LogOutController;
import skytest.com.skyengtest.utils.BaseMvpController;

public class EmailLoginController  extends BaseMvpController<EmailLoginView,EmailLoginPresenter> implements EmailLoginView {

    @BindView(R.id.emailView) EditText emailView;
    @BindView(R.id.passView)  EditText passView;
    @BindView(R.id.enterButton) Button enterButton;
    private ProgressDialog progressDialog;
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view =inflater.inflate(R.layout.controller_email_login, container, false);
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity(),R.style.SkyDialogStyle);
        return view;
    }

    @NonNull
    @Override
    public EmailLoginPresenter createPresenter() {
        return new EmailLoginPresenter();
    }

    @OnClick(R.id.enterButton)
    public void onEnter(){
        presenter.login();
    }

    @OnClick(R.id.simpleButton)
    public void onSimpleLogin(){
        navigateToSimpleLogin();
    }


    @Override
    public void navigateToSimpleLogin() {
        getRouter().pushController(RouterTransaction.with(new LoginController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void navigateToLogout() {
        getRouter().pushController(RouterTransaction.with(new LogOutController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public EditText getEmailView() {
        return emailView;
    }

    @Override
    public EditText getPassView() {
        return passView;
    }

    @Override
    public void onButtonEnable(boolean enable) {
        enterButton.setEnabled(enable);
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Выполняется вход.\nПодождите...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onLoginError() {
        Snackbar snackbar = Snackbar.make(getView(), "Неверный адрес электронной почты или пароль", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onConnectError() {
        Snackbar snackbar = Snackbar.make(getView(), "Не удалось подключиться. Проверьте интернет соединение.", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    protected String getTitle() {
        return "Вход";
    }
}
