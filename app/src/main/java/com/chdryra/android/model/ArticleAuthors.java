package com.chdryra.android.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class ArticleAuthors {
    private List<Author> mAuthors;
    private Author mPrimaryAuthor;
    private String mAuthorLocation;

    public ArticleAuthors() {
    }

    public ArticleAuthors(List<Author> authors, Author primaryAuthor, String authorLocation) {
        mAuthors = authors;
        mPrimaryAuthor = primaryAuthor;
        mAuthorLocation = authorLocation;
    }

    public List<Author> getAuthors() {
        return mAuthors;
    }

    public Author getPrimaryAuthor() {
        return mPrimaryAuthor;
    }

    public String getAuthorLocation() {
        return mAuthorLocation;
    }
}