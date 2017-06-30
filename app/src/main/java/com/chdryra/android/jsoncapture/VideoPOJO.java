package com.chdryra.android.jsoncapture;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class VideoPOJO {
    @SerializedName("thumbnail")
    private String mThumbnail;
    @SerializedName("id")
    private long mId;
    @SerializedName("title")
    private String mTitle;

    public String getThumbnail() {
        return mThumbnail;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
