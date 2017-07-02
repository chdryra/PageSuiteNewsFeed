package com.chdryra.android.pagesuitenewsfeed;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class ActivityItemView extends ActivitySingleFragment {
    @Override
    protected Fragment createFragment(Bundle savedInstanceState) {
        return FragmentItemView.newInstance();
    }
}
