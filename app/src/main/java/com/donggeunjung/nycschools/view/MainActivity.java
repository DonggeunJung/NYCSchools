package com.donggeunjung.nycschools.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.model.SchoolSimple;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * MainActivity.java : Main activity class. Load 2 fragments
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class MainActivity extends BaseActivity
        implements SchoolRVAdapter.ItemListener {
    boolean mMultiPanel = false;
    ListFragment mListF;
    BodyFragment mBodyF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make school list fragment & details fragment
        initFragment();
    }

    // Make school list fragment & details fragment
    private void initFragment() {
        // Get List fragment from layout & save to member variable
        mListF = (ListFragment)getSupportFragmentManager().findFragmentById(R.id.fragList);

        // When 2nd panel is exist, it means multiple panel
        View panelBody = findViewById(R.id.panelBody);
        if( panelBody != null && panelBody.getVisibility() == View.VISIBLE ) {
            mMultiPanel = true;
            // Get Body fragment from layout & save to member variable
            mBodyF = (BodyFragment)getSupportFragmentManager().findFragmentById(R.id.fragBody);
        }
    }

    // Switch Body fragment
    private void switch2BodyFragment() {
        mBodyF = new BodyFragment();

        // Change fragment on container layout(ViewGroup)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.panelList, mBodyF)
                .addToBackStack(null)
                .commit();
    }

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

}
