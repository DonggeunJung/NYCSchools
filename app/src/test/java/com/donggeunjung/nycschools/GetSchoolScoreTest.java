package com.donggeunjung.nycschools;

import com.donggeunjung.nycschools.model.ApiSchool;
import com.donggeunjung.nycschools.model.SchoolDetail;
import com.donggeunjung.nycschools.model.SchoolScore;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;
/*
 * GetSchoolScoreTest.java : Retrofit Unit test. Get particular school score data.
 *                     When the response has particular data, the result is correct.
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.17.2019
 */
public class GetSchoolScoreTest extends BaseRetrofitTest {
    @Test
    public void login_Success() {
        // Make Retrofit API object
        ApiSchool api = getApiSchool();
        Call<ArrayList<SchoolScore>> call = api.getSchoolScore("21K728");

        try {
            // Request particular School score data to server
            Response<ArrayList<SchoolScore>> response = call.execute();
            // Get the 1st item of School list.
            ArrayList<SchoolScore> schoolScores = response.body();
            if( schoolScores == null || schoolScores.size() == 0 )
                return;
            SchoolScore score = schoolScores.get(0);

            // Check whether the number of SAT test takers are bigger than 0
            assertTrue(response.isSuccessful()
                    && Integer.parseInt(score.getNum_of_sat_test_takers()) > 0 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
