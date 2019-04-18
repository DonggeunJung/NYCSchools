package com.donggeunjung.nycschools.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donggeunjung.nycschools.model.SchoolSimple;
import com.donggeunjung.nycschools.viewmodel.DataViewModel;
import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.databinding.FragmentBodyBinding;
/*
 * BodyFragment.java : Detail infomation fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class BodyFragment extends Fragment {
    static BodyFragment fragment;
    FragmentBodyBinding mBinding;
    AppCompatActivity mActivity;
    DataViewModel mViewModel;

    // Make self fragment object and return
    public static BodyFragment makeObj() {
        // Make self fragment object, when it is not created yet
        if( fragment == null ) {
            fragment = new BodyFragment();
        }
        return fragment;
    }

    // Receive parent & ViewModel objects and save as member variable
    public void setProvider(AppCompatActivity activity, DataViewModel viewModel) {
        mActivity = activity;
        mViewModel = viewModel;
        // When Binding object is exist, set ViewModel
        if( mBinding != null ) {
            mBinding.setViewModel(mViewModel);
        }
    }

    // When Fragment view is created, load layout file
    // Bind view with ViewModel
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Save self fragment object as a member variable
        fragment = this;
        // Bind view with ViewModel
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_body, container, false);
        View v = mBinding.getRoot();
        mBinding.setLifecycleOwner(this);
        // When ViewModel is exist, connect with Binding object
        if( mViewModel != null ) {
            mBinding.setViewModel(mViewModel);
        }
        return v;
    }

    // Request particular school detail information to server.
    public void reqSchoolScore(SchoolSimple schoolSimple) {
        // Set received school simple data to ViewModel
        mViewModel.getSchoolSimple().setValue(schoolSimple);
        // Request particular school SAT score information to server.
        mViewModel.getSchoolScore(schoolSimple.getDbn());
        // Request particular school detail information to server.
        mViewModel.getSchoolDetail(schoolSimple.getDbn());
    }
}
