package com.chdryra.android.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class ArticleTopics {
    private List<String> mTopics;
    private List<String> mTags;

    public ArticleTopics() {
    }

    public ArticleTopics(List<String> topics, List<String> tags) {
        mTopics = topics;
        mTags = tags;
    }

    public List<String> getTopics() {
        return mTopics;
    }

    public List<String> getTags() {
        return mTags;
    }
}