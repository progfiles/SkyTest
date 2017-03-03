package skytest.com.skyengtest.mvp.login;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
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
import skytest.com.skyengtest.mvp.code.CodeController;
import skytest.com.skyengtest.mvp.email.EmailLoginController;
import skytest.com.skyengtest.utils.BaseMvpController;


public class LoginController extends BaseMvpController<LoginView,LoginPresenter> implements LoginView {

    @BindView(R.id.emailView)
    EditText emailView;
    @BindView(R.id.codeButton)
    Button codeButton;
    private ProgressDialog progressDialog;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_login, container, false);
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity(),R.style.SkyDialogStyle);
        return view;
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @OnClick(R.id.codeButton)
    public void getCode() {
       // presenter.fetchCode();
        getRouter().pushController(RouterTransaction.with(new CodeController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @OnClick(R.id.defEnterView)
    public void onDefaultClick(){
        navigateToEmailLogin();
    }


    @Override
    public void navigateToCode() {
       // presenter.fetchCode();
        getRouter().pushController(RouterTransaction.with(new CodeController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void navigateToEmailLogin() {
        getRouter().pushController(RouterTransaction.with(new EmailLoginController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void onButtonEnable(boolean enable) {
        codeButton.setEnabled(enable);
    }


    @Override
    public EditText getEmailView() {
        return emailView;
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage(getResources().getString(R.string.title_enter_stream));
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onLoginError() {

    }

    @Override
    public void onConnectError() {

    }
    @Override
    protected String getTitle() {
        return "Вход";
    }

}
