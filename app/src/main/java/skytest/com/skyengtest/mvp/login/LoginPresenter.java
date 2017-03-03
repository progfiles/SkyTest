package skytest.com.skyengtest.mvp.login;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import skytest.com.skyengtest.utils.Validator;


public class LoginPresenter extends MvpBasePresenter<LoginView> {



    @Override
    public void attachView(LoginView view) {
        super.attachView(view);

        if (getView()!=null)
        RxTextView.textChanges(getView().getEmailView())
        .map(Validator::isEmailValid)
        .subscribe(it->{getView().onButtonEnable(it);});
    }



}
