package com.donggeunjung.nycschools;

import com.donggeunjung.nycschools.model.ApiSchool;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 * BaseRetrofitTest.java : Retrofit Unit test base class.
 *                     Contains function about Retrofit API.
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class BaseRetrofitTest {

    // Make Retrofit Api object & return
    public static ApiSchool getApiSchool() {
        // Make Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSchool.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        // Make Retrofit API object
        ApiSchool api = retrofit.create(ApiSchool.class);
        return api;
    }

}
