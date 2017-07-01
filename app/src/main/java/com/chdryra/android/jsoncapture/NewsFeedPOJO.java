package com.chdryra.android.jsoncapture;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class NewsFeedPOJO {
    @SerializedName("feeds")
    private FeedsPOJO mFeeds;

    @SerializedName("articles")
    private List<ArticlePOJO> mArticles;

    @SerializedName("section")
    private String mSection;

    @SerializedName("url")
    private String mUrl;

    public FeedsPOJO getFeeds() {
        return mFeeds;
    }

    public List<ArticlePOJO> getArticles() {
        return mArticles;
    }

    public String getSection() {
        return mSection;
    }

    public String getUrl() {
        return mUrl;
    }
}
