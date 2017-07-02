package com.chdryra.android.model;

import android.support.annotation.Nullable;

import org.parceler.Parcel;

import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 *
 * Nullable Urls in case malformed from JSON.
 */

@Parcel
public class ArticleSection {
    private String mSectionName;
    private URL mSectionUrl;

    public ArticleSection() {
    }

    public ArticleSection(String sectionName, @Nullable URL sectionUrl) {
        mSectionName = sectionName;
        mSectionUrl = sectionUrl;
    }

    public String getSectionName() {
        return mSectionName;
    }

    @Nullable
    public URL getSectionUrl() {
        return mSectionUrl;
    }
}