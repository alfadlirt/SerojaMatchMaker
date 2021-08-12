package id.ac.polman.astra.serojamatchmaker.remote;

import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.Event;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseChangePassword;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseEditUser;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseGetEvent;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("user/auth")
    @FormUrlEncoded
    Call<ResponseLogin> authlogin(@Field("username") String username,
                                  @Field("password") String password);


    @POST("user")
    Call<User> addUser(@Body User user);

    @GET("user/")
    Call<User> getUser();

    @GET("user/{id}")
    Call<User> getUserById(@Path("id") String id);

    //@Headers("Content-Type: application/x-www-form-urlencoded")
    @PUT("user/{id}")
    @FormUrlEncoded
    Call<ResponseEditUser> updateUser(@Path("id") String id,
                                      @Field("name") String name,
                                      @Field("username") String username);

    @PUT("user/updatepassword/{id}")
    @FormUrlEncoded
    Call<ResponseChangePassword> changePassword(@Path("id") String id,
                                                @Field("oldpassword") String oldpassword,
                                                @Field("newpassword") String newpassword);

    @GET("event")
    Call<ResponseGetEvent> getEvent();

    @GET("getEventCount/ongoing")
    Call<Integer> getCountEventOngoing();

    @GET("getEventCount/finished")
    Call<Integer> getCountEventfinished();
}
