package com.chdryra.android.model;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class JsonApi {
    private final String mBaseUrl;

    public JsonApi(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }
}
