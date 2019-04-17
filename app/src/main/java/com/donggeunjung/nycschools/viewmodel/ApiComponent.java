package com.donggeunjung.nycschools.viewmodel;

import com.donggeunjung.nycschools.model.ApiNyc;

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
    ApiNyc provideApi();

    void inject(DataViewModel viewModel);
}
