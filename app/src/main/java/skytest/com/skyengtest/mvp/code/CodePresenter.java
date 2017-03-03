package skytest.com.skyengtest.mvp.code;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import skytest.com.skyengtest.utils.Validator;

import static java.lang.System.currentTimeMillis;

public class CodePresenter extends MvpBasePresenter<CodeView> {

    private long timestamp=0;
    private Subscription timerSubscription;
    private final int CODETIMEOUT=60;

    @Override
    public void attachView(CodeView view) {
        super.attachView(view);
        RxTextView.textChanges(getView().getCodeView())
                .map(Validator::isCodeValid)
                .subscribe(it->{getView().onButtonEnable(it);});

        startTimer();
    }

    public void refetchCode(){
        startTimer();
    }

    public void login(){
        getView().showProgress();
    }

    private void startTimer(){
        timestamp = currentTimeMillis();
        timerSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it-> tick());
    }


    private void tick(){
       long dif = timestamp  - System.currentTimeMillis();
       long coundown = CODETIMEOUT + dif/1000;

        if (getView()!=null) {
            getView().onTick(coundown);
            if (coundown < 1) {
                getView().onResetButtonEnable(true);
                timerSubscription.unsubscribe();
            } else {
                getView().onResetButtonEnable(false);
            }
        }
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (timerSubscription!=null){
            timerSubscription.unsubscribe();
            timerSubscription=null;
        }

    }
}
