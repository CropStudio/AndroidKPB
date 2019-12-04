package com.example.app4g.network;


//import com.example.app4g.App;
//import com.example.app4g.R;
//import com.example.app4g.server.AppController;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private static final String BASK_URL ="http://103.230.48.151:5050/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static RestService getAPIService(){
//        return RetrofitClient.getClient(BASK_URL).create(RestService.class);
//
//    }
}
