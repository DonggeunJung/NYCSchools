package com.donggeunjung.nycschools.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
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
    static ListFragment mFragment;
    static View.OnClickListener mListener;
    FragmentListBinding mBinding;
    SchoolRVAdapter rvAdapter;

    // Make self fragment object and return
    public static Fragment makeObj() {
        // Make self fragment object, when it is not created yet
        if( mFragment == null ) {
            mFragment = new ListFragment();
        }
        return mFragment;
    }

    // Receive event listener & ViewModel objects and save as member variable
    //public void setProvider(View.OnClickListener listener, DataViewModel viewModel) {
    public static void setProvider(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragment = this;
        // Request School list to server
        mViewModel.getSchoolList();
    }

    // When Fragment view is created, load layout file
    // Request School list to server
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        // Save self fragment object as a member variable
        // Bind view with ViewModel
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        mBinding.setLifecycleOwner(this);
        initList();

        // Set Text changing listener of Search EditText
        mBinding.etSearch.addTextChangedListener(mSearchTextWatcher);
        return mBinding.getRoot();
    }

    // Init RecyclerView adapter & Request School list to server
    //protected void initList(boolean loadData) {
    protected void initList() {
        // Init RecyclerView adapter
        rvAdapter = new SchoolRVAdapter(mViewModel, this, mListener);
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
