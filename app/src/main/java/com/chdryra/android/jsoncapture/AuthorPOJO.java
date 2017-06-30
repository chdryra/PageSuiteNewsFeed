package com.chdryra.android.jsoncapture;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class AuthorPOJO {
    @SerializedName("name")
    private String mName;
    @SerializedName("job_title")
    @Nullable
    private String mJobTitle;
    @SerializedName("twitter_handle")
    @Nullable
    private String mTwitterHandle;

    public String getName() {
        return mName;
    }

    @Nullable
    public String getJobTitle() {
        return mJobTitle;
    }

    @Nullable
    public String getTwitterHandle() {
        return mTwitterHandle;
    }
}
