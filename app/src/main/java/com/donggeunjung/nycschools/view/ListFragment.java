package com.donggeunjung.nycschools.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donggeunjung.nycschools.model.SchoolSimple;
import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.databinding.FragmentListBinding;

import java.util.ArrayList;
/*
 * ListFragment.java : School list fragment source file
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class ListFragment extends BaseFragment
        implements SchoolRVAdapter.ItemListener {
    FragmentListBinding mBinding;
    boolean mMultiPanel = false;

    // When Fragment view is created, load layout file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        // Request School list to server
        mViewModel.getSchoolList();
        // Save self fragment object as a member variable
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        mBinding.setLifecycleOwner(this);
        // Init RecyclerView adapter & Request School list to server
        initList();

        // Set Text changing listener of Search EditText
        mBinding.etSearch.addTextChangedListener(mSearchTextWatcher);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Check whether multiple panel mode
        initBodyFragment();
    }

    // Check whether multiple panel mode
    private void initBodyFragment() {
        // Get Body fragment from layout & save to member variable
        BodyFragment bodyF = (BodyFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragBody);
        // When 2nd panel is exist, it means multiple panel
        if( bodyF != null && bodyF.getView().getVisibility() == View.VISIBLE ) {
            mMultiPanel = true;
        }
    }

    // Init RecyclerView adapter & Request School list to server
    protected void initList() {
        // Init RecyclerView adapter
        SchoolRVAdapter rvAdapter = new SchoolRVAdapter(mViewModel, this);
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

    // School RecyclerView item selection event
    public void onSchoolSelected(int position) {
        // When single panel mode, switch to 2nd fragment
        if( mMultiPanel == false )
            switch2BodyFragment();

        // Get School simple data list
        ArrayList<SchoolSimple> schoolSimples = mViewModel.getListSchools().getValue();
        if( schoolSimples == null || schoolSimples.size() <= position )
            return;
        // Get school simple data from ViewModel
        SchoolSimple schoolSimple = schoolSimples.get(position);
        // Request school score & datail data to server
        mViewModel.reqSchoolScore(schoolSimple);
    }

    // Switch Body fragment
    private void switch2BodyFragment() {
        BodyFragment bodyF = new BodyFragment();

        // Change fragment on container layout(ViewGroup)
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.panelList, bodyF)
                .addToBackStack(null)
                .commit();
    }
}
