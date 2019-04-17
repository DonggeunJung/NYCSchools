package com.donggeunjung.nycschools.viewmodel;

import com.donggeunjung.nycschools.model.ApiNyc;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/*
 * ApiComponent.java : Dagger class for Retrofit object injection
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    ApiNyc provideApi() {
        // Make Retrofit API object & return
        return ApiNyc.retrofit.create(ApiNyc.class);
    }
}
