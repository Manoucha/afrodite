package com.imene.afrodite.retrofit;

import com.imene.afrodite.models.Cadeau;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface INodeJS {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("nom") String nom,
                                    @Field("prenom") String prenom,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("latitude") double latitude,
                                    @Field("longitude") double longitude);

    @GET("login/{email}/{password}")
    Call<String> loginUser(@Path("email")  String email,
                           @Path("password")  String password);

    @GET("cadeaux/")
    Call<List<Cadeau>> getCadeaux();

    @PUT("ajoutercadeaupanier/{idClient}/{idCadeau}")
    Call<Void> ajoutercadeaupanier(@Path(value="idClient") String idClient , @Path(value="idCadeau") String idCadeau );


    @GET("cadeaux/{idClient}")
    Call<List<Cadeau>>  getCadeauDansLePanier(@Path(value="idClient") String id);

    @PUT("/echanger/{idClient}/{idCadeau}/{quantite}/{points}")
    Call<Void> echanger(@Path(value="idClient") String idClient , @Path(value="idCadeau") String idCadeau ,@Path(value="quantite") int quantite,@Path(value = "points") int points);


}
