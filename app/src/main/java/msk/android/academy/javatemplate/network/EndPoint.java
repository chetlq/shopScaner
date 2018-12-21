package msk.android.academy.javatemplate.network;

import io.reactivex.Single;
import msk.android.academy.javatemplate.model.Product;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPoint {
    @GET("?get=1&")
    Single<Product> getData(@Query("code") long productCode);

   /* @GET()
    Single<List<Product>> getAll();

    @POST("user.json")
    Call<Product> addData(Product response);

    @DELETE
    Call<Product> deleteData(Product response);*/
}
