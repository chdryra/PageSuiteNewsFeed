package com.chdryra.android.model;

import org.parceler.Parcel;

@Parcel
public class ArticleBody {
    private String mBody;

    public ArticleBody() {
    }

    public ArticleBody(String body) {
        mBody = body;
    }

    public String getBody() {
        return mBody;
    }
}