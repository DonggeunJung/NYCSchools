package com.donggeunjung.nycschools.viewmodel;

import com.donggeunjung.nycschools.model.ApiSchool;
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
    ApiSchool provideApi() {
        // Make Retrofit API object & return
        return ApiSchool.retrofit.create(ApiSchool.class);
    }
}
