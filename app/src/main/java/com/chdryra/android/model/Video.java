package com.chdryra.android.model;

import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class Video {
    private long mId;
    private final URL mThumbnail;
    private final String mTitle;

    public Video(long id, URL thumbnail, String title) {
        mThumbnail = thumbnail;
        mId = id;
        mTitle = title;
    }

    public long getId() {
        return mId;
    }

    public URL getThumbnail() {
        return mThumbnail;
    }

    public String getTitle() {
        return mTitle;
    }
}
