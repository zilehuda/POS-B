package com.example.zilay.pos_b;

import com.example.zilay.pos_b.models.Products;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zilay on 5/1/18.
 */

public interface ApiInterface {
    @GET("product")
    Call<List<Products>> getProducts();

    @POST("product") @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8",
            "Cache-Control: max-age=640000"
    })

    Call<Products> postProducts(@Body JsonObject product);


    @GET("order/getproduct")
    Call<List<Products>> getSingleProduct(@Query("id") String id);


}
