package com.dronesolutions.appgamarra.ui.services;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VANESSA on 28/01/2017.
 */
public class RestClient {

    public IServices iServices;

    public RestClient(String uri) {
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request original = chain.request();

                                // Request customization: add request headers
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Accept", "application/json; charset=UTF-8")
                                        .method(original.method(), original.body());


                                Request request = requestBuilder.build();
                                Log.e("request", request.toString());
                                return chain.proceed(request);
                            }
                        })
                .build();

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(uri)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okClient)
                    .build();
            iServices = retrofit.create(IServices.class);
        } catch (Exception e) {
            iServices = null;
        }
    }


    public IServices getServices() {
        return iServices;
    }
}
