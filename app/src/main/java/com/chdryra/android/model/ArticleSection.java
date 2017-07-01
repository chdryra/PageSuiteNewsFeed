package com.chdryra.android.model;

import android.support.annotation.Nullable;

import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 *
 * Nullable Urls in case malformed from JSON.
 */

public class ArticleSection {
    private final String mSectionName;
    private final URL mSectionUrl;

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