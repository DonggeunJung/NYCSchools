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
public class MainActivity extends BaseActivity {
    boolean mMultiPanel = false;
    int mCurrentFragIndex = 0;
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
        // When 2nd panel is exist, it means multiple panel
        View panelBody = findViewById(R.id.panelBody);
        if( panelBody != null && panelBody.getVisibility() == View.VISIBLE ) {
            mMultiPanel = true;
            // Get Body fragment from layout & save to member variable
            mBodyF = (BodyFragment)getSupportFragmentManager().findFragmentById(R.id.fragBody);
        }
        // Show the 1st fragment
        switchFragment(0);
        ListFragment.setProvider(mClickListener);
    }

    // RecyclerView item click event listener
    // Send school simple data to details fragment
    View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            // When single panel mode, switch to 2nd fragment
            if( mMultiPanel == false )
                switchFragment(1);

            // Get clicked item's index number
            int index = Integer.parseInt((String)v.getTag());
            ArrayList<SchoolSimple> schoolSimples = mViewModel.getListSchools().getValue();
            if( schoolSimples == null || schoolSimples.size() <= index )
                return;
            // Get school simple data from ViewModel
            SchoolSimple schoolSimple = schoolSimples.get(index);
            // Request school score & datail data to server
            mViewModel.reqSchoolScore(schoolSimple);
        }
    };

    // Switch fragment
    private void switchFragment(int fragIndex) {
        // Save new fragment index number
        mCurrentFragIndex = fragIndex;
        // Get the new fragment object & save to member variable
        Fragment fragment;
        if( fragIndex == 0 ) {
            fragment = new ListFragment();
            mListF = (ListFragment)fragment;
        }
        else {
            fragment = new BodyFragment();
            mBodyF = (BodyFragment)fragment;
        }

        // Change fragment on container layout(ViewGroup)
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.panelFragment, fragment);
        // When 2nd fragment, arrow return to 1st fragment by Hardware Back button
        if( fragIndex == 1) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
