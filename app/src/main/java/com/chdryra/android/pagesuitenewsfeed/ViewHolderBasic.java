package com.chdryra.android.pagesuitenewsfeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public abstract class ViewHolderBasic<DataType> extends RecyclerView.ViewHolder {
    public abstract void updateData(DataType data);

    public ViewHolderBasic(View itemView) {
        super(itemView);
    }
}
