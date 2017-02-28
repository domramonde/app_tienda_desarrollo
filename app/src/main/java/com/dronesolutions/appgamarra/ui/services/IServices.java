package com.dronesolutions.appgamarra.ui.services;

import com.dronesolutions.appgamarra.ui.models.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by VANESSA on 28/01/2017.
 */
public interface IServices {

    @FormUrlEncoded
    @POST(Services.LOGIN)
    Call<ResponseBody> setLogin(@Field("usuario") String usu, @Field("contrasena") String pass);

    @POST(Services.REGISTRAR_USUARIO)
    Call<ResponseBody> setRegistrarUsuario(@Body Usuario usuario/*, @Query("api_token") String api_token*/);

}
