package com.chdryra.android.model;

import com.chdryra.android.jsoncapture.NewsFeedPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class IndependentApi extends JsonApi {
    private static final String BASE_URL = "http://www.independent.co.uk/api/v1/";

    public enum Feed {
        FRONT_PAGE("11831"),
        WORLD("11916"),
        SPORT("12791"),
        BUSINESS("11981");

        private final String mFeedCode;

        Feed(String feedCode) {
            mFeedCode = feedCode;
        }

        public String getFeedCode() {
            return mFeedCode;
        }
    }

    public IndependentApi() {
        super(BASE_URL);
        for(Feed feed : Feed.values()) {
            addServicePath(feed.name(), feed.getFeedCode());
        }
    }

    public ServicePath getServicePath(Feed feed) {
        return getServicePath(feed.name());
    }

    public interface Service {
        @GET("{servicePath}/json")
        Call<NewsFeedPOJO> getQueryResponse(@Path("servicePath") String code);
    }
}
