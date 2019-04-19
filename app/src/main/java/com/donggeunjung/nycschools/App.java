package com.donggeunjung.nycschools;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.donggeunjung.nycschools.viewmodel.DataViewModel;

public class App extends Application {
    static DataViewModel mViewModel;

    public void onCreate() {
        super.onCreate();
    }

    public DataViewModel getViewModel(FragmentActivity activity) {
        if( mViewModel == null )
            mViewModel = ViewModelProviders.of(activity).get(DataViewModel.class);
        return mViewModel;
    }

    public void onTerminate() {
        super.onTerminate();
    }
}
