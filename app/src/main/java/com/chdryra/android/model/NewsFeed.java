package com.chdryra.android.model;

import java.net.URL;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class NewsFeed {
    private final ArticleSection mFeeds;
    private final List<Article> mArticles;
    private final String mSection;
    private final URL mUrl;

    public NewsFeed(ArticleSection feeds, List<Article> articles, String section, URL url) {
        mFeeds = feeds;
        mArticles = articles;
        mSection = section;
        mUrl = url;
    }

    public ArticleSection getFeeds() {
        return mFeeds;
    }

    public List<Article> getArticles() {
        return mArticles;
    }

    public String getSection() {
        return mSection;
    }

    public URL getUrl() {
        return mUrl;
    }
}
