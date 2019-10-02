package com.connectlifes.admin.oauth2.client;

import android.content.Context;

import com.connectlifes.admin.oauth2.constant.OauthConstant;
import com.connectlifes.admin.oauth2.response.AccessTokenResponse;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder;

    private static Context mContext;
    private static AccessTokenResponse mToken;

    public static <S> S oAuthService(Class<S> serviceClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(new OauthHeaderInteceptor()).addInterceptor(interceptor);
        builder = new Retrofit.Builder()
                .baseUrl(OauthConstant.AUTHENTICATION_SERVER_URL+"/auth/")
                .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S ApiService(Class<S> serviceClass, Context c) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(new APIHeaderInterceptor(c)).addInterceptor(interceptor);;
        builder = new Retrofit.Builder()
                .baseUrl(OauthConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
