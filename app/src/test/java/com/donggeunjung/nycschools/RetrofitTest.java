package com.donggeunjung.nycschools;

import com.donggeunjung.nycschools.model.ApiSchool;
import com.donggeunjung.nycschools.model.SchoolDetail;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.TestCase.assertTrue;

/*
 * RetrofitTest.java : Retrofit Unit test.
 *                     When the response has particular data, the result is correct.
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class RetrofitTest {
    @Test
    public void login_Success() {
        // Make Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSchool.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        // Make Retrofit API object
        ApiSchool api = retrofit.create(ApiSchool.class);
        Call<ArrayList<SchoolDetail>> call = api.getSchoolDetail("02M260");

        try {
            // Request particular School data to server
            Response<ArrayList<SchoolDetail>> response = call.execute();
            // Get the 1st item of School list.
            ArrayList<SchoolDetail> details = response.body();
            if( details == null || details.size() == 0 )
                return;
            SchoolDetail detail = details.get(0);

            // Compare the Zip code of response with particular data
            assertTrue(response.isSuccessful()
                    && detail.getZip().equals("10003") );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
