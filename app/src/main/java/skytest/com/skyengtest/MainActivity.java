package skytest.com.skyengtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import skytest.com.skyengtest.mvp.email.EmailLoginController;
import skytest.com.skyengtest.utils.ActionBarProvider;

public final class MainActivity extends AppCompatActivity implements ActionBarProvider {
    @BindView(R.id.container) ViewGroup container;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private Router router;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new EmailLoginController()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}
