//package msk.android.academy.javatemplate.network;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RestApi {
//    private static final String BASE_URL = "http://timetabata.com/api/hackathon/";
//    private static final int TIMEOUT_IN_SECOND = 2;
//
//    private static RestApi restApi;
//    private static EndPoint endPoint;
//
//    private RestApi() {
//        OkHttpClient okHttpClient = buildOkHttpClient();
//        Retrofit sRetrofit = buildRetrofit(okHttpClient);
//
//        endPoint = sRetrofit.create(EndPoint.class);
//    }
//
//    public static synchronized RestApi getInstance() {
//        if (restApi == null) {
//            restApi = new RestApi();
//        }
//        return restApi;
//    }
//
//    public EndPoint getEndPoint() {
//        return endPoint;
//    }
//
//    private OkHttpClient buildOkHttpClient() {
//        return new OkHttpClient.Builder()
//                .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
//                .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
//                .build();
//    }
//
//    private Retrofit buildRetrofit(OkHttpClient client) {
//        return new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//}
