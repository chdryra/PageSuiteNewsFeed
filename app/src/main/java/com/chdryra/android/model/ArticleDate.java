package com.chdryra.android.model;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class ArticleDate {
    private Date mPublishDate;
    private Date mUpdatedDate;

    public ArticleDate() {
    }

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