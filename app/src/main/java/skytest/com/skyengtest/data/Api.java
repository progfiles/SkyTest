package skytest.com.skyengtest.data;


import com.auth0.android.jwt.JWT;
import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {
    @POST("api/login")
    Observable<JWT> signIn(@Body JsonObject body);
}
