//package com.app.kpb2.network;
//
//import com.app.kpb2.R;
//import com.app.kpb2.server.App;
//
//import java.security.cert.CertificateException;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RestAdapter {
//    private static final String BASK_URL_pasarBebas = App.getApplication().getString(R.string.end_point_pasarBebas);
//    private static Retrofit retrofit = null;
//    private static NetworkService apiInterface;
//
//    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//
//            // Create an ssl socket factory with our all-trusting manager
//            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
////            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
//                Request original = chain.request();
//                Request request = original.newBuilder()
//                        .header("x-access-token", token)
//                        .header("username", nik)
//                        .method(original.method(), original.body())
//                        .build();
//
//                return chain.proceed(request);
//            }).build();
//            okHttpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            okHttpClient.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
//            return okHttpClient;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static NetworkService getApiClient() {
//        if (apiInterface == null) {
//
//            try {
//                retrofit = new Retrofit.Builder()
//                        .baseUrl(BASK_URL_pasarBebas)
//                        .client(getUnsafeOkHttpClient().build())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//
//            apiInterface = retrofit.create(NetworkService.class);
//        }
//        return apiInterface;
//    }
//
//}
