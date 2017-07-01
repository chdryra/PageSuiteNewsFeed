package com.chdryra.android.model;

public class ArticleHeadlines {
    private final String mHeadline;
    private final String mHeadlineOverride;
    private final String mShortHeadline;
    private final String mSubHeadline;
    private final String mLocalCaption;

    public ArticleHeadlines(String headline, String headlineOverride, String shortHeadline, String
            subHeadline, String localCaption) {
        mHeadline = headline;
        mHeadlineOverride = headlineOverride;
        mShortHeadline = shortHeadline;
        mSubHeadline = subHeadline;
        mLocalCaption = localCaption;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public String getHeadlineOverride() {
        return mHeadlineOverride;
    }

    public String getShortHeadline() {
        return mShortHeadline;
    }

    public String getSubHeadline() {
        return mSubHeadline;
    }

    public String getLocalCaption() {
        return mLocalCaption;
    }
}