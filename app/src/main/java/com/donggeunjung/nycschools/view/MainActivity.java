package com.donggeunjung.nycschools.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.model.SchoolSimple;

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

        initFragment();
    }

    // Make school list fragment & details fragment
    private void initFragment() {
        // When 2nd panel is exist, it means multiple panel
        View panelBody = findViewById(R.id.panelBody);
        if( panelBody != null && panelBody.getVisibility() == View.VISIBLE ) {
            mMultiPanel = true;
        }
        // When only single panel exists, show the 1st fragment
        else {
            switchFragment(0);
        }

        // Send event listener & ViewModel objects to School list fragment
        mListF = ListFragment.makeObj();
        mListF.setProvider(mClickListener, mViewModel);
        // When multiple panels mode, request school list to server
        if( mMultiPanel )
            mListF.initList();

        // Send event listener & ViewModel objects to details fragment
        mBodyF = BodyFragment.makeObj();
        mBodyF.setProvider(this, mViewModel);
    }

    // When single panel mode, switch fragment
    private void switchFragment(int fragIndex) {
        // Save new fragment index number
        mCurrentFragIndex = fragIndex;
        // Get the new fragment object
        Fragment fragment = (fragIndex == 0) ? ListFragment.makeObj() : BodyFragment.makeObj();

        // Show new fragment on screen
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.panelFragment, fragment)
                .commit();
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
            if( mViewModel.getListSchools().getValue().size() <= index )
                return;
            // Get school simple data from ViewModel
            SchoolSimple schoolSimple = mViewModel.getListSchools().getValue().get(index);
            // Request school score & datail data to server
            mViewModel.reqSchoolScore(schoolSimple);
        }
    };

    // Hardware Back key event function
    // When single panel mode & details fragment is shown, switch to list fragment
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // When hardware Back key is pressed
            case KeyEvent.KEYCODE_BACK:
                // When single panel mode & details fragment is shown,
                // switch to list fragment
                if( mMultiPanel == false && mCurrentFragIndex == 1 ) {
                    switchFragment(0);
                    return true;
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
