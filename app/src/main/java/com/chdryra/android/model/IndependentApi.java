package com.chdryra.android.model;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class IndependentApi extends JsonApi<IndependentApi> {
    private static final String BASE_URL = "http://www.independent.co.uk/api/v1/";
    public enum Feed {
        FRONT_PAGE("11831"),
        WORLD("11916"),
        SPORT("12791"),
        BUSINESS("11981");

        private final String mPath;

        Feed(String path) {
            mPath = path;
        }

        private String getPath() {
            return mPath;
        }
    }

    public IndependentApi() {
        super(BASE_URL);
        for(Feed feed : Feed.values()) {
            addChannel(feed.name(), feed.getPath());
        }
    }

    public Channel<IndependentApi> getChannel(Feed channel) {
        return getChannel(channel.name());
    }
}
