package com.donggeunjung.nycschools.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.donggeunjung.nycschools.App;
import com.donggeunjung.nycschools.viewmodel.DataViewModel;

public class BaseFragment extends Fragment {
    DataViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = App.getViewModel(getActivity());
    }

}
