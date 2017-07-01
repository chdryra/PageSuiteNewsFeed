package com.chdryra.android.model;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class Author {
    private final String mName;
    private final String mJobTitle;
    private final String mTwitterHandle;

    public Author(String name, String jobTitle, String twitterHandle) {
        mName = name;
        mJobTitle = jobTitle;
        mTwitterHandle = twitterHandle;
    }

    public String getName() {
        return mName;
    }

    public String getJobTitle() {
        return mJobTitle;
    }

    public String getTwitterHandle() {
        return mTwitterHandle;
    }
}
