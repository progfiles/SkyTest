package skytest.com.skyengtest.data;


import com.auth0.android.jwt.JWT;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import retrofit2.http.Body;
import rx.Observable;

public class MokeWebservice implements Api{
    @Override
    public Observable<JWT> signIn(@Body JsonObject body) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";
        JWT jwt = new JWT(token);

        if (body.get("email").getAsString().equals("progfiles@mail.ru"))
        return Observable.just(jwt).delay(3500, TimeUnit.MILLISECONDS);
        return Observable.just(jwt).delay(3500, TimeUnit.MILLISECONDS).flatMap(it->Observable.error(new Throwable()));
    }
}
