package com.donggeunjung.nycschools.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.donggeunjung.nycschools.model.ApiSchool;
import com.donggeunjung.nycschools.model.SchoolDetail;
import com.donggeunjung.nycschools.model.SchoolScore;
import com.donggeunjung.nycschools.model.SchoolSimple;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
 * DataViewModel.java : ViewModel class. Manage data & request to server
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class DataViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SchoolSimple>> mListSchools;
    private MutableLiveData<ArrayList<SchoolSimple>> mTotalSchools;
    private MutableLiveData<SchoolSimple> mSchoolSimple;
    private MutableLiveData<SchoolScore> mSchoolScore;
    private MutableLiveData<SchoolDetail> mSchoolDetail;

    @Inject
    ApiSchool mApi;

    // Constructor - Inject Retrofit API object
    public DataViewModel() {
        ApiComponent component = DaggerApiComponent.builder().build();
        component.inject(this);
    }

    // Return searched School simple data list
    public MutableLiveData<ArrayList<SchoolSimple>> getListSchools() {
        if (mListSchools == null) {
            mListSchools = new MutableLiveData<ArrayList<SchoolSimple>>();
        }
        return mListSchools;
    }

    // Return total School simple data list
    public MutableLiveData<ArrayList<SchoolSimple>> getTotalSchools() {
        if (mTotalSchools == null) {
            mTotalSchools = new MutableLiveData<ArrayList<SchoolSimple>>();
        }
        return mTotalSchools;
    }

    // Return current school simple data
    public MutableLiveData<SchoolSimple> getSchoolSimple() {
        if (mSchoolSimple == null) {
            mSchoolSimple = new MutableLiveData<SchoolSimple>();
        }
        return mSchoolSimple;
    }

    // Return current school score data
    public MutableLiveData<SchoolScore> getSchoolScore() {
        if (mSchoolScore == null) {
            mSchoolScore = new MutableLiveData<SchoolScore>();
        }
        return mSchoolScore;
    }

    // Return current school detail data
    public MutableLiveData<SchoolDetail> getSchoolDetail() {
        if (mSchoolDetail == null) {
            mSchoolDetail = new MutableLiveData<SchoolDetail>();
        }
        return mSchoolDetail;
    }

    //===============================================

    // Request school data list to server
    public void getSchoolList() {
        Call<ArrayList<SchoolSimple>> call = mApi.getSchoolList();
        call.enqueue(new Callback<ArrayList<SchoolSimple>>() {

            @Override
            public void onResponse(Call<ArrayList<SchoolSimple>> call, Response<ArrayList<SchoolSimple>> response) {
                // When completed, get data & save
                ArrayList<SchoolSimple> totalSchools = response.body();
                getTotalSchools().setValue(totalSchools);

                // Copy all school object link to new ArrayList
                ArrayList<SchoolSimple> listSchools = new ArrayList<SchoolSimple>();
                for( SchoolSimple schoolSimple : totalSchools) {
                    listSchools.add(schoolSimple);
                }
                getListSchools().setValue(listSchools);

                // Show 1st item's detail data
                if( listSchools.size() > 0 ) {
                    reqSchoolScore(listSchools.get(0));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SchoolSimple>> call, Throwable t) {}
        });
    }

    // Request school data to server
    public void getSchoolDetail(String dbn) {
        Call<ArrayList<SchoolDetail>> call = mApi.getSchoolDetail(dbn);
        call.enqueue(new Callback<ArrayList<SchoolDetail>>() {

            @Override
            public void onResponse(Call<ArrayList<SchoolDetail>> call, Response<ArrayList<SchoolDetail>> response) {
                ArrayList<SchoolDetail> listDetails = response.body();
                int size = listDetails.size();
                // When completed, get data & save
                if( size > 0 ) {
                    SchoolDetail detail = listDetails.get(0);
                    getSchoolDetail().setValue(detail);
                }
                // When data is not exist, make empty data object
                else {
                    SchoolSimple schoolSimple = getSchoolSimple().getValue();
                    SchoolDetail detail = new SchoolDetail(schoolSimple.getDbn(),
                            schoolSimple.getSchool_name(), "", "", "", "", "", "", "", "", "", "");
                    getSchoolDetail().setValue(detail);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SchoolDetail>> call, Throwable t) {}
        });
    }

    // Request SAT score data to server
    public void getSchoolScore(String dbn) {
        Call<ArrayList<SchoolScore>> call = mApi.getSchoolScore(dbn);
        call.enqueue(new Callback<ArrayList<SchoolScore>>() {

            @Override
            public void onResponse(Call<ArrayList<SchoolScore>> call, Response<ArrayList<SchoolScore>> response) {
                ArrayList<SchoolScore> listGrades = response.body();
                int size = listGrades.size();
                // When completed, get data & save
                if( size > 0 ) {
                    SchoolScore score = listGrades.get(0);
                    getSchoolScore().setValue(score);
                }
                // When data is not exist, make empty data object
                else {
                    SchoolSimple schoolSimple = getSchoolSimple().getValue();
                    SchoolScore score = new SchoolScore(schoolSimple.getDbn(),
                            schoolSimple.getSchool_name(), "0", "", "", "");
                    getSchoolScore().setValue(score);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SchoolScore>> call, Throwable t) {}
        });
    }

    // Request particular school detail information to server.
    public void reqSchoolScore(int schoolIndex) {
        // Get School simple data list
        ArrayList<SchoolSimple> schoolSimples = getListSchools().getValue();
        if( schoolSimples == null || schoolSimples.size() <= schoolIndex )
            return;
        // Get school simple data from ViewModel
        SchoolSimple schoolSimple = schoolSimples.get(schoolIndex);
        // Request school score & datail data to server
        reqSchoolScore(schoolSimple);
    }

    // Request particular school detail information to server.
    public void reqSchoolScore(SchoolSimple schoolSimple) {
        // Set received school simple data to ViewModel
        getSchoolSimple().setValue(schoolSimple);
        // Request particular school SAT score information to server.
        getSchoolScore(schoolSimple.getDbn());
        // Request particular school detail information to server.
        getSchoolDetail(schoolSimple.getDbn());
    }

    // Text changing listener of Search EditText
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String strSearch = s.toString();
        if( strSearch.length() < 2 )
            return;
        ArrayList<SchoolSimple> listSchools = getSearchedSchoolList(strSearch);
        getListSchools().setValue(listSchools);
    }

    // Make a ArrayList by searching School name in Total School list
    protected ArrayList<SchoolSimple> getSearchedSchoolList(String strSearch) {
        ArrayList<SchoolSimple> listSchools = new ArrayList<SchoolSimple>();
        for(SchoolSimple schoolSimple : getTotalSchools().getValue()) {
            if( schoolSimple.searchName(strSearch) )
                listSchools.add(schoolSimple);
        }
        return listSchools;
    }

}
