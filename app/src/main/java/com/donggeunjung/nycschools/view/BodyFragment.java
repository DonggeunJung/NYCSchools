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
public class BodyFragment extends BaseFragment {
    static BodyFragment mFragment;
    FragmentBodyBinding mBinding;

    // Make self fragment object and return
    public static Fragment makeObj() {
        // Make self fragment object, when it is not created yet
        if( mFragment == null ) {
            mFragment = new BodyFragment();
        }
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Save self fragment object as a member variable
        mFragment = this;
    }

    // When Fragment view is created, load layout file
    // Bind view with ViewModel
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Bind view with ViewModel
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_body, container, false);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

}
