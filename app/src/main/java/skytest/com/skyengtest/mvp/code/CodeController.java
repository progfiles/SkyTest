package skytest.com.skyengtest.mvp.code;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skytest.com.skyengtest.R;
import skytest.com.skyengtest.utils.BaseMvpController;



public class CodeController  extends BaseMvpController<CodeView,CodePresenter> implements CodeView {

    @BindView(R.id.enterButton) Button enterButton;
    @BindView(R.id.codeView) EditText codeView;
    @BindView(R.id.resendButton) Button resendButton;
    private ProgressDialog progressDialog;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view=inflater.inflate(R.layout.controller_code, container, false);
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity(),R.style.SkyDialogStyle);
        return  view;
    }

    @NonNull
    @Override
    public CodePresenter createPresenter() {
        return new CodePresenter();
    }

    @Override
    public EditText getCodeView() {
        return codeView;
    }

    @Override
    public void onButtonEnable(boolean enabled) {
        enterButton.setEnabled(enabled);
    }

    @OnClick(R.id.resendButton)
    public void fetchCode(){
        presenter.refetchCode();
    }


    @OnClick(R.id.enterButton)
    public void onEnter(){
        presenter.login();
    }

    @Override
    public void onTick(long time) {
        if (time>0)
        resendButton.setText(String.format("Выслать код повторно (%dc)", time));
        else resendButton.setText("Выслать код повторно");
    }

    @Override
    public void onResetButtonEnable(boolean enalble) {
        resendButton.setEnabled(enalble);
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
        Snackbar snackbar = Snackbar.make(getView(), "Не верный код", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onConnectError() {
        Snackbar snackbar = Snackbar.make(getView(), "Не удалось подключиться. Проверьте интернет соединение.", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    protected String getTitle() {
        return "Введите код";
    }

}
