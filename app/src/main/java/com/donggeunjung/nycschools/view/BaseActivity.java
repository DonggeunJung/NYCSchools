package com.donggeunjung.nycschools.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.donggeunjung.nycschools.viewmodel.DataViewModel;
/*
* BaseActivity.java : Super class of activitys
* Author : DONGGEUN JUNG (Dennis)
* Date : Apr.16.2019
*/
public class BaseActivity extends AppCompatActivity {
    DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the ViewModel's object and save as member variable
        mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
    }

}
