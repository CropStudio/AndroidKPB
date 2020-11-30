package com.app.kpb2.network;


public class UtilsApi {

    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "http://103.230.48.151:5050/";
//    http://103.230.48.151:5050/

    public static NetworkService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(NetworkService.class);
    }
}
