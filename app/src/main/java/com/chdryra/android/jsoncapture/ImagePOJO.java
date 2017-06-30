package com.chdryra.android.jsoncapture;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class ImagePOJO {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("thumbnail")
    private String mThumbnail;
    @SerializedName("copyright")
    @Nullable
    private String mCopyright;
    @SerializedName("caption")
    @Nullable
    private String mCaption;
    @SerializedName("title")
    private String mTitle;

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    @Nullable
    public String getCopyright() {
        return mCopyright;
    }

    @Nullable
    public String getCaption() {
        return mCaption;
    }

    public String getTitle() {
        return mTitle;
    }
}
