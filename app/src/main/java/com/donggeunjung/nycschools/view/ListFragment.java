package com.donggeunjung.nycschools.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donggeunjung.nycschools.model.SchoolSimple;
import com.donggeunjung.nycschools.viewmodel.DataViewModel;
import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.databinding.FragmentListBinding;

import java.util.ArrayList;
/*
 * ListFragment.java : School list fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class ListFragment extends BaseFragment {
    FragmentListBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Request School list to server
        mViewModel.getSchoolList();
    }

    // When Fragment view is created, load layout file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        // Save self fragment object as a member variable
        // Bind view with ViewModel
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        mBinding.setLifecycleOwner(this);
        // Init RecyclerView adapter & Request School list to server
        initList();

        // Set Text changing listener of Search EditText
        mBinding.etSearch.addTextChangedListener(mSearchTextWatcher);
        return mBinding.getRoot();
    }

    // Init RecyclerView adapter & Request School list to server
    protected void initList() {
        SchoolRVAdapter.ItemListener listener = (SchoolRVAdapter.ItemListener)getActivity();
        // Init RecyclerView adapter
        SchoolRVAdapter rvAdapter = new SchoolRVAdapter(mViewModel, this, listener);
        mBinding.rvSchool.setAdapter( rvAdapter );
        mBinding.rvSchool.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        // Show Item Divider on RecyclerView
        mBinding.rvSchool.addItemDecoration(new DividerItemDecoration(getContext(), 1));
    }

    // Text changing listener of Search EditText
    TextWatcher mSearchTextWatcher = new TextWatcher() {
        // When input text changing
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Get search word from EditText
            String strSearch = mBinding.etSearch.getText().toString();
            // Search in school name of Total school list
            if( mViewModel != null ) {
                mViewModel.searchSchoolName(strSearch);
            }
        }

        // When input text change finished
        @Override
        public void afterTextChanged(Editable arg0) {}

        // When input text change started
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };
}
