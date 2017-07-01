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

public class Image {
    @Nullable
    private final URL mUrl;
    @Nullable
    private final URL mThumbnail;
    private final String mCopyright;
    private final String mCaption;
    private final String mTitle;

    public Image(@Nullable URL url, @Nullable URL thumbnail, String copyright, String caption,
                 String title) {
        mUrl = url;
        mThumbnail = thumbnail;
        mCopyright = copyright;
        mCaption = caption;
        mTitle = title;
    }

    @Nullable
    public URL getUrl() {
        return mUrl;
    }

    @Nullable
    public URL getThumbnail() {
        return mThumbnail;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public String getCaption() {
        return mCaption;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean hasUrl() {
        return getUrl() != null;
    }

    public boolean hasThumbnail() {
        return getThumbnail() != null;
    }
}
