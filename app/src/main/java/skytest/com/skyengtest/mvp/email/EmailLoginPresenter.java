package skytest.com.skyengtest.mvp.email;


import com.google.gson.JsonObject;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import skytest.com.skyengtest.data.MokeWebservice;
import skytest.com.skyengtest.utils.Validator;

public class EmailLoginPresenter extends MvpBasePresenter<EmailLoginView> {

    private Subscription requestSubscription;

    @Override
    public void attachView(EmailLoginView view) {
        super.attachView(view);
        if (getView()!=null) {
            Observable<Boolean> emailValid = RxTextView.textChanges(getView().getEmailView())
                    .map(Validator::isEmailValid);

            Observable<Boolean> passValid = RxTextView.textChanges(getView().getPassView())
                    .map(Validator::isPasValid);

            Observable<Boolean> registerEnabled =
                    Observable.combineLatest(emailValid, passValid, (a, b) -> a && b);
            registerEnabled.subscribe(enabled -> getView().onButtonEnable(enabled));
        }
    }


     public void login(){

             String email = getView().getEmailView().getText().toString();
             String passwd = getView().getPassView().getText().toString();

             MokeWebservice webservice = new MokeWebservice();

             JsonObject jsonObject = new JsonObject();
             jsonObject.addProperty("email", email);
             jsonObject.addProperty("password", passwd);

             getView().showProgress();
             requestSubscription = webservice.signIn(jsonObject)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(it -> {
                         getView().hideProgress();
                         getView().navigateToLogout();
                     }, throwable -> {


                         getView().hideProgress();
                         getView().onLoginError();
                     });

     }

    @Override
    public void detachView(boolean retainInstance) {
       if (requestSubscription!=null) {
           requestSubscription.unsubscribe();
           requestSubscription = null;
       }
        super.detachView(retainInstance);
    }
}
