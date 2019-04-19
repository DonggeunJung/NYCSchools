package com.donggeunjung.nycschools.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.donggeunjung.nycschools.App;
import com.donggeunjung.nycschools.viewmodel.DataViewModel;

import javax.inject.Inject;

/*
* BaseActivity.java : Super class of activitys
* Author : DONGGEUN JUNG (Dennis)
* Date : Apr.16.2019
*/
public class BaseActivity extends AppCompatActivity {
    App mApp;
    public DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (App)this.getApplication();
        mViewModel = mApp.getViewModel(this);
    }

}
