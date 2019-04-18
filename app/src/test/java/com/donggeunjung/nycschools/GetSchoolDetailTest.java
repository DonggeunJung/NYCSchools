package com.donggeunjung.nycschools;

import com.donggeunjung.nycschools.model.ApiSchool;
import com.donggeunjung.nycschools.model.SchoolDetail;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;
/*
 * GetSchoolDetailTest.java : Retrofit Unit test. Get particular school detail data.
 *                     When the response has particular data, the result is correct.
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.17.2019
 */
public class GetSchoolDetailTest extends BaseRetrofitTest {
    @Test
    public void login_Success() {
        // Make Retrofit API object
        ApiSchool api = getApiSchool();
        Call<ArrayList<SchoolDetail>> call = api.getSchoolDetail("02M260");

        try {
            // Request particular School data to server
            Response<ArrayList<SchoolDetail>> response = call.execute();
            // Get the 1st item of School list.
            ArrayList<SchoolDetail> schoolDetails = response.body();
            if( schoolDetails == null || schoolDetails.size() == 0 )
                return;
            SchoolDetail detail = schoolDetails.get(0);

            // Compare the Zip code of response with particular data
            assertTrue(response.isSuccessful()
                    && detail.getZip().equals("10003") );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
