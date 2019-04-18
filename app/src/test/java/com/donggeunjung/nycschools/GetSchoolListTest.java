package com.donggeunjung.nycschools;

import com.donggeunjung.nycschools.model.ApiSchool;
import com.donggeunjung.nycschools.model.SchoolSimple;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;
import static junit.framework.TestCase.assertTrue;
/*
 * GetSchoolListTest.java : Retrofit Unit test. Get all School simple data list
 *                     When the response has over 100 items of data, the result is correct.
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.17.2019
 */
public class GetSchoolListTest extends BaseRetrofitTest {
    @Test
    public void login_Success() {
        // Make Retrofit API object
        ApiSchool api = getApiSchool();
        Call<ArrayList<SchoolSimple>> call = api.getSchoolList();

        try {
            // Request School data list to server
            Response<ArrayList<SchoolSimple>> response = call.execute();
            // Get the School simple data list.
            ArrayList<SchoolSimple> schoolSimples = response.body();
            if( schoolSimples == null || schoolSimples.size() == 0 )
                return;

            // Check whether enough items exist in School list
            assertTrue(response.isSuccessful()
                    && schoolSimples.size() > 100 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
