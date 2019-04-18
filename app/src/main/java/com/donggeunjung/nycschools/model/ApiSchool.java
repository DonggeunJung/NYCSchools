package com.donggeunjung.nycschools.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * ApiNyc.java : Retrofit API interface
 * School List Api : https://data.cityofnewyork.us/Education/DOE-High-School-Directory-2017/s3k6-pzi2
 * School Score Api : https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public interface ApiSchool {

    // Make Retrofit object
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiSchool.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();

    // The base URL address of server
    String BASE_URL = "https://data.cityofnewyork.us/resource/";

    // Request school data list
    @GET("s3k6-pzi2.json")
    Call<ArrayList<SchoolSimple>> getSchoolList();

    // Request particular school data
    @GET("s3k6-pzi2.json")
    Call<ArrayList<SchoolDetail>> getSchoolDetail(@Query("dbn") String dbn);

    // Request SAT score data
    @GET("f9bf-2cp4.json")
    Call<ArrayList<SchoolScore>> getSchoolScore(@Query("dbn") String dbn);

}
