package com.chdryra.android.model;

import org.parceler.Parcel;

import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

@Parcel
public class Video {
    private long mId;
    private URL mThumbnail;
    private String mTitle;

    public Video() {
    }

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
