package com.app.kpb2.network;


//import com.example.app4g.App;
//import com.example.app4g.R;
//import com.example.app4g.server.AppController;

import com.app.kpb2.R;
import com.app.kpb2.server.App;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private static final String BASK_URL = App.getApplication().getString(R.string.end_point);
    private static final String BASK_URL_pasarBebas = App.getApplication().getString(R.string.end_point_pasarBebas);
    private static final String BASK_URL_pasarBebasDevelop = App.getApplication().getString(R.string.end_point_pasarBebasDevelop);
    private static Retrofit retrofit;
    private static Retrofit retrofit2;
    private static Retrofit retrofitDevelop;

    public static Retrofit getRetrofitInstanceDevelop() {
        if (retrofitDevelop == null){
            retrofitDevelop = new Retrofit
                    .Builder()
                    .baseUrl(BASK_URL_pasarBebasDevelop)
//                    .client(getUnsafeOkHttpClient("12421025" , "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZXNzYWdlIjoiMTI0MjEwMjUiLCJpYXQiOjE2MjU1Mzk4NjYsImV4cCI6MTYyNjUzOTg2Nn0.O0SPztcZuGMD3f2w0ql52PzuGWF5H7gQg4R5Yinswk4").build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitDevelop;
    }

    public static Retrofit getRetrofitInstancePasarBebas() {
        if (retrofit2 == null){
            retrofit2 = new Retrofit
                    .Builder()
                    .baseUrl(BASK_URL_pasarBebas)
//                    .client(getUnsafeOkHttpClient("12421025" , "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZXNzYWdlIjoiMTI0MjEwMjUiLCJpYXQiOjE2MjU1Mzk4NjYsImV4cCI6MTYyNjUzOTg2Nn0.O0SPztcZuGMD3f2w0ql52PzuGWF5H7gQg4R5Yinswk4").build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }

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



    public static OkHttpClient.Builder getUnsafeOkHttpClient(String nik , String token) {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("x-access-token", token)
                        .header("username", nik)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            });
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
