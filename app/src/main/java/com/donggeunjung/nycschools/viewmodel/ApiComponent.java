package com.donggeunjung.nycschools.viewmodel;

import android.support.v4.app.FragmentActivity;

import com.donggeunjung.nycschools.model.ApiSchool;

import javax.inject.Singleton;

import dagger.Component;
/*
 * ApiComponent.java : Dagger interface for Retrofit object injection
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
@Singleton
@Component(modules = {ApiModule.class})
interface ApiComponent {
    ApiSchool provideApi();

    void inject(DataViewModel viewModel);
}
