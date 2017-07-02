package com.chdryra.android.model;

import org.parceler.Parcel;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

@Parcel
public class Article {
    private long mGuid;

    private ArticleSettings mSettings;
    private ArticleUrl mUrl;
    private ArticleSection mSection;
    private ArticleHeadlines mHeadline;
    private ArticleAuthors mAuthors;
    private ArticleDate mDate;
    private ArticleTopics mTopics;
    private ArticleMedia mMedia;
    private ArticleBody mBody;

    public Article() {
    }

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
