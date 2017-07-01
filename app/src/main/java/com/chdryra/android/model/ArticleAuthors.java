package com.chdryra.android.model;

import java.util.List;

public class ArticleAuthors {
    private final List<Author> mAuthors;
    private final Author mPrimaryAuthor;
    private final String mAuthorLocation;

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