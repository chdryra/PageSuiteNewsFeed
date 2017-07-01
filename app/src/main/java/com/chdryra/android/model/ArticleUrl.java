package com.chdryra.android.model;

import android.support.annotation.Nullable;

import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 *
 * Nullable Urls in case malformed from JSON.
 */
public class ArticleUrl {
    private final URL mUrl;
    private final URL mLink;

    public ArticleUrl(@Nullable URL url, @Nullable URL link) {
        mUrl = url;
        mLink = link;
    }

    @Nullable
    public URL getUrl() {
        return mUrl;
    }

    @Nullable
    public URL getLink() {
        return mLink;
    }

    public boolean hasUrl() {
        return getUrl() != null;
    }
}