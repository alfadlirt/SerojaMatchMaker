package id.ac.polman.astra.serojamatchmaker.remote;

import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.Event;
import id.ac.polman.astra.serojamatchmaker.entity.BracketResponse;
import id.ac.polman.astra.serojamatchmaker.entity.EventInput;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseAddEvent;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseBracketGet;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseBracketPut;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseChangePassword;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseEditUser;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseEventGet;
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

public interface
APIService {

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

    @POST("event")
    Call<ResponseAddEvent> addEventAndTeam(@Body EventInput eventInput);

    @GET("match/{id}")
    Call<ResponseBracketGet> getEventBracket(@Path("id") String id);

    @PUT("match/inputscore/{id}")
    @FormUrlEncoded
    Call<ResponseBracketPut> updatescore(@Path("id") String id,
                                         @Field("skor_a") Integer skor_a,
                                         @Field("skor_b") Integer skor_b);

    @GET("event/{id}")
    Call<ResponseEventGet> getEvent(@Path("id") String id);
}
