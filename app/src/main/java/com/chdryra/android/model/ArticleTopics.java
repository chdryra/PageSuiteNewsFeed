package com.chdryra.android.model;

import java.util.List;

public class ArticleTopics {
    private final List<String> mTopics;
    private final List<String> mTags;

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