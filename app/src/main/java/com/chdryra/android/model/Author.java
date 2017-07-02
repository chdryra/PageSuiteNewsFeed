package com.chdryra.android.model;

import org.parceler.Parcel;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

@Parcel
public class Author {
    private String mName;
    private String mJobTitle;
    private String mTwitterHandle;

    public Author() {
    }

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
