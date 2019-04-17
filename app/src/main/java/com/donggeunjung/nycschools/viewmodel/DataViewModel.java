package com.donggeunjung.nycschools.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.donggeunjung.nycschools.model.ApiNyc;
import com.donggeunjung.nycschools.model.NycSchool;
import com.donggeunjung.nycschools.model.SchoolDetail;
import com.donggeunjung.nycschools.model.SchoolScore;

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
    private MutableLiveData<ArrayList<NycSchool>> mListSchools;
    private MutableLiveData<ArrayList<NycSchool>> mTotalSchools;
    private MutableLiveData<NycSchool> mNycSchool;
    private MutableLiveData<SchoolScore> mSchoolScore;
    private MutableLiveData<SchoolDetail> mSchoolDetail;

    @Inject
    ApiNyc mApi;

    // Constructor - Inject Retrofit API object
    public DataViewModel() {
        ApiComponent component = DaggerApiComponent.builder().build();
        component.inject(this);
    }

    // Return searched School simple data list
    public MutableLiveData<ArrayList<NycSchool>> getListSchools() {
        if (mListSchools == null) {
            mListSchools = new MutableLiveData<ArrayList<NycSchool>>();
        }
        return mListSchools;
    }

    // Return total School simple data list
    public MutableLiveData<ArrayList<NycSchool>> getTotalSchools() {
        if (mTotalSchools == null) {
            mTotalSchools = new MutableLiveData<ArrayList<NycSchool>>();
        }
        return mTotalSchools;
    }

    // Return current school simple data
    public MutableLiveData<NycSchool> getNycSchool() {
        if (mNycSchool == null) {
            mNycSchool = new MutableLiveData<NycSchool>();
        }
        return mNycSchool;
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
    public void getSchools() {
        Call<ArrayList<NycSchool>> call = mApi.getSchools();
        call.enqueue(new Callback<ArrayList<NycSchool>>() {

            @Override
            public void onResponse(Call<ArrayList<NycSchool>> call, Response<ArrayList<NycSchool>> response) {
                // When completed, get data & save
                ArrayList<NycSchool> totalSchools = response.body();
                getTotalSchools().setValue(totalSchools);

                // Copy all school object link to new ArrayList
                ArrayList<NycSchool> listSchools = new ArrayList<NycSchool>();
                for( NycSchool school : totalSchools) {
                    listSchools.add(school);
                }
                getListSchools().setValue(listSchools);
            }

            @Override
            public void onFailure(Call<ArrayList<NycSchool>> call, Throwable t) {}
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
                    mSchoolDetail.setValue(detail);
                }
                // When data is not exist, make empty data object
                else {
                    NycSchool nycSchool = mNycSchool.getValue();
                    SchoolDetail detail = new SchoolDetail(nycSchool.getDbn(),
                            nycSchool.getSchool_name(), "", "", "", "", "", "", "", "", "", "");
                    mSchoolDetail.setValue(detail);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SchoolDetail>> call, Throwable t) {}
        });
    }

    // Request SAT score data to server
    public void getScore(String dbn) {
        Call<ArrayList<SchoolScore>> call = mApi.getScore(dbn);
        call.enqueue(new Callback<ArrayList<SchoolScore>>() {

            @Override
            public void onResponse(Call<ArrayList<SchoolScore>> call, Response<ArrayList<SchoolScore>> response) {
                ArrayList<SchoolScore> listGrades = response.body();
                int size = listGrades.size();
                // When completed, get data & save
                if( size > 0 ) {
                    SchoolScore score = listGrades.get(0);
                    mSchoolScore.setValue(score);
                }
                // When data is not exist, make empty data object
                else {
                    NycSchool nycSchool = mNycSchool.getValue();
                    SchoolScore score = new SchoolScore(nycSchool.getDbn(),
                            nycSchool.getSchool_name(), "0", "", "", "");
                    mSchoolScore.setValue(score);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SchoolScore>> call, Throwable t) {}
        });
    }

    // Search particular word in Name of School list
    public void searchSchoolName(String strSearch) {
        ArrayList<NycSchool> listSchools = getSearchedSchoolList(strSearch);
        getListSchools().setValue(listSchools);
    }

    // Make a ArrayList by searching School name in Total School list
    protected ArrayList<NycSchool> getSearchedSchoolList(String strSearch) {
        ArrayList<NycSchool> listSchools = new ArrayList<NycSchool>();
        for(NycSchool school : mTotalSchools.getValue()) {
            if( school.searchName(strSearch) )
                listSchools.add(school);
        }
        return listSchools;
    }

}
