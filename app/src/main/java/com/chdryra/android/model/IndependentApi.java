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

    public IndependentApi() {
        super(BASE_URL);
    }

    public interface Service {
        @GET("{path}")
        Call<NewsFeedPOJO> getQueryResponse(@Path(value = "path", encoded = true) String code);
    }

    public enum Subscriptions {
        FRONT_PAGE("Front Page", "11831/json"),
        WORLD("World", "11916/json"),
        SPORT("Sport", "12791/json"),
        BUSINESS("Business", "11981/json");

        private final String mName;
        private final String mPath;

        Subscriptions(String name, String path) {
            mName = name;
            mPath = path;
        }

        public String getName() {
            return mName;
        }

        public String getPath() {
            return mPath;
        }
    }
}
