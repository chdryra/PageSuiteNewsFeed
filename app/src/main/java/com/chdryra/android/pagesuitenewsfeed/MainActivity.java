package com.chdryra.android.pagesuitenewsfeed;

import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends ActivitySingleFragment {
    @Override
    protected Fragment createFragment(Bundle savedInstanceState) {
        return FragmentListView.newInstance();
    }
}
