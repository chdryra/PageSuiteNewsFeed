package com.chdryra.android.model;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class Article {
    private long mGuid;

    private final ArticleSettings mSettings;
    private final ArticleUrl mUrl;
    private final ArticleSection mSection;
    private final ArticleHeadlines mHeadline;
    private final ArticleAuthors mAuthors;
    private final ArticleDate mDate;
    private final ArticleTopics mTopics;
    private final ArticleMedia mMedia;
    private final ArticleBody mBody;

    public Article(long guid, ArticleSettings settings, ArticleUrl url, ArticleSection section,
                   ArticleHeadlines headline, ArticleAuthors authors, ArticleDate date,
                   ArticleTopics topics, ArticleMedia media, ArticleBody body) {
        mGuid = guid;
        mSettings = settings;
        mUrl = url;
        mSection = section;
        mHeadline = headline;
        mAuthors = authors;
        mDate = date;
        mTopics = topics;
        mMedia = media;
        mBody = body;
    }

    public long getGuid() {
        return mGuid;
    }

    public ArticleSettings getSettings() {
        return mSettings;
    }

    public ArticleUrl getUrl() {
        return mUrl;
    }

    public ArticleSection getSection() {
        return mSection;
    }

    public ArticleHeadlines getHeadlines() {
        return mHeadline;
    }

    public ArticleAuthors getAuthors() {
        return mAuthors;
    }

    public ArticleDate getDate() {
        return mDate;
    }

    public ArticleTopics getTopics() {
        return mTopics;
    }

    public ArticleMedia getMedia() {
        return mMedia;
    }

    public ArticleBody getBody() {
        return mBody;
    }
}
