package com.chdryra.android.pagesuitenewsfeed;

import android.view.View;

import com.chdryra.android.model.Article;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class FactoryArticleViewHolder implements FactoryViewHolder<Article>{
    @Override
    public ArticleViewHolder newViewHolder(View v) {
        return new ArticleViewHolder(v);
    }

    @Override
    public int getViewHolderLayout() {
        return ArticleViewHolder.getLayout();
    }
}
