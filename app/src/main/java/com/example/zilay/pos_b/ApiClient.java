package com.example.zilay.pos_b;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zilay on 5/1/18.
 */

public class ApiClient {
    private static String BASE_URL = "http://www.posb.somee.com/api/";

    public static Retrofit retrofit = null;


    public static Retrofit getApiClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
