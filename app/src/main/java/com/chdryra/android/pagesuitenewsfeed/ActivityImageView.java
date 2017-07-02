package com.chdryra.android.pagesuitenewsfeed;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class ActivityImageView extends ActivitySingleFragment {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected Fragment createFragment(Bundle savedInstanceState) {
        return FragmentImageView.newInstance();
    }
}
