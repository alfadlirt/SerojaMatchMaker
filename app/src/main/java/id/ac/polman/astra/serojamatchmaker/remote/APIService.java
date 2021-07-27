package id.ac.polman.astra.serojamatchmaker.remote;

import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("user/auth")
    @FormUrlEncoded
    Call<ResponseLogin> authlogin(@Field("username") String username,
                                  @Field("password") String password);

    @POST("user")
    Call<User> addUser(@Body User user);

    @GET("user/")
    Call<User> getUser();
}
