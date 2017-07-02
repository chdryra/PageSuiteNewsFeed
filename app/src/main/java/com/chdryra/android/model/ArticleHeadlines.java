package com.chdryra.android.model;

import org.parceler.Parcel;

@Parcel
public class ArticleHeadlines {
    private String mHeadline;
    private String mHeadlineOverride;
    private String mShortHeadline;
    private String mSubHeadline;
    private String mLocalCaption;

    public ArticleHeadlines() {
    }

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