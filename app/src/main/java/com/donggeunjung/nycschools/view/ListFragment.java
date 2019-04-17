package com.donggeunjung.nycschools.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donggeunjung.nycschools.viewmodel.DataViewModel;
import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.databinding.FragmentListBinding;
import com.donggeunjung.nycschools.model.NycSchool;

import java.util.ArrayList;
/*
 * ListFragment.java : School list fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class ListFragment extends Fragment {
    static ListFragment fragment;
    FragmentListBinding mBinding;
    View.OnClickListener mListener;
    DataViewModel mViewModel;
    RvAdapter rvAdapter;

    // Make self fragment object and return
    public static ListFragment makeObj() {
        // Make self fragment object, when it is not created yet
        if( fragment == null ) {
            fragment = new ListFragment();
        }
        return fragment;
    }

    // Receive event listener & ViewModel objects and save as member variable
    public void setProvider(View.OnClickListener listener, DataViewModel viewModel) {
        mListener = listener;
        mViewModel = viewModel;
    }

    // When Fragment view is created, load layout file
    // Request School list to server
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        // Save self fragment object as a member variable
        fragment = this;
        // Bind view with ViewModel
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        View v = mBinding.getRoot();

        mBinding.setLifecycleOwner(this);
        // Request School list to server
        if( mViewModel != null ) {
            ArrayList<NycSchool> schools = mViewModel.getListSchools().getValue();
            // Init RecyclerView adapter
            if( schools == null || schools.size() == 0 )
                // When School list data is not exist, reqest to server
                initData(true);
            else
                // When School list data is exist, do not reqest
                initData(false);
        }

        // Set Text changing listener of Search EditText
        mBinding.etSearch.addTextChangedListener(mSearchTextWatcher);
        return v;
    }

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

    // Init RecyclerView adapter & Request School list to server
    public void initData() {
        initData(true);
    }

    // Init RecyclerView adapter & Request School list to server
    protected void initData(boolean loadData) {
        // Init RecyclerView adapter
        rvAdapter = new RvAdapter(mViewModel, mListener, this);
        mBinding.rvSchool.setAdapter( rvAdapter );
        mBinding.rvSchool.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        // Request School list to server
        if( loadData )
            mViewModel.getSchools();
    }
}