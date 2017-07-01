package com.chdryra.android.model;

import java.util.Date;

public class ArticleDate {
    private final Date mPublishDate;
    private final Date mUpdatedDate;

    public ArticleDate(Date publishDate, Date updatedDate) {
        mPublishDate = publishDate;
        mUpdatedDate = updatedDate;
    }

    public Date getPublishDate() {
        return mPublishDate;
    }

    public Date getUpdatedDate() {
        return mUpdatedDate;
    }
}