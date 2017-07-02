package com.chdryra.android.pagesuitenewsfeed;

import android.view.View;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public interface FactoryViewHolder<DataType> {
    ViewHolderBasic<DataType> newViewHolder(View v);

    int getViewHolderLayout();
}
