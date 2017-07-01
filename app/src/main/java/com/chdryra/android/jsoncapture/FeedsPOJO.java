package com.chdryra.android.jsoncapture;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class FeedsPOJO {
    @SerializedName("section")
    private String mSection;

    @SerializedName("url")
    private String mUrl;

    public String getSectionName() {
        return mSection;
    }

    public String getSectionUrl() {
        return mUrl;
    }
}
