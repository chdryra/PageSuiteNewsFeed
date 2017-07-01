package com.chdryra.android.pagesuitenewsfeed;

import android.support.annotation.NonNull;

import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.JsonApi;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * For the async network calls to the Independent Api.
 *
 */
public class IndependentApiTests {
    @Test
    public void feed_enum_has_correct_codes() throws Exception {
        assertThat(IndependentApi.Feed.FRONT_PAGE.getFeedCode(), is("11831"));
        assertThat(IndependentApi.Feed.WORLD.getFeedCode(), is("11916"));
        assertThat(IndependentApi.Feed.SPORT.getFeedCode(), is("12791"));
        assertThat(IndependentApi.Feed.BUSINESS.getFeedCode(), is("11981"));
    }

    @Test
    public void get_baseurl_returns_correct_url() throws Exception {
        IndependentApi api = newApi();
        assertThat(api.getBaseUrl(), is("http://www.independent.co.uk/api/v1/"));
    }

    @Test
    public void get_service_path_frontpage_feed_is_correct() throws Exception {
        get_service_path_is_correct_for_feed(IndependentApi.Feed.FRONT_PAGE);
    }

    @Test
    public void get_service_path_world_feed_is_correct() throws Exception {
        get_service_path_is_correct_for_feed(IndependentApi.Feed.WORLD);
    }

    @Test
    public void get_service_path_business_feed_is_correct() throws Exception {
        get_service_path_is_correct_for_feed(IndependentApi.Feed.BUSINESS);
    }

    @Test
    public void get_service_path_sport_feed_is_correct() throws Exception {
        get_service_path_is_correct_for_feed(IndependentApi.Feed.SPORT);
    }

    private void get_service_path_is_correct_for_feed(IndependentApi.Feed feed) {
        IndependentApi api = newApi();
        JsonApi.ServicePath path = api.getServicePath(feed);
        assertThat(path, is(notNullValue()));
        assertThat(path.getPathKey(), is(feed.name()));
        assertThat(path.getPath(), is(feed.getFeedCode()));
    }

    @NonNull
    private IndependentApi newApi() {
        return new IndependentApi();
    }
}