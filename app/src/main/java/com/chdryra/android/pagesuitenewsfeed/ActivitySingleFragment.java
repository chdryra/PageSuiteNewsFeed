/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.pagesuitenewsfeed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivitySingleFragment extends AppCompatActivity {
    public static final int FRAGMENT_ID = R.id.fragmentContainer;
    private Fragment mFragment;

    protected abstract Fragment createFragment(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getFragmentManager();
        mFragment = fm.findFragmentById(FRAGMENT_ID);

        if (mFragment == null) {
            mFragment = createFragment(savedInstanceState);
            fm.beginTransaction().add(FRAGMENT_ID, mFragment).commit();
        }
    }

    protected Fragment getFragment() {
        return mFragment;
    }
}
