package com.chdryra.android.model;

import android.support.annotation.Nullable;

import org.parceler.Parcel;

import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 *
 * Nullable Urls in case malformed from JSON.
 */

@Parcel
public class ArticleUrl {
    private URL mUrl;
    private URL mLink;

    public ArticleUrl() {
    }

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