package com.chdryra.android.pagesuitenewsfeed;

import android.support.annotation.NonNull;

import com.chdryra.android.model.IndependentApi;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

/**
 * For the async network calls to the Independent Api.
 *
 */
public class IndependentApiTests {
    @Test
    public void feed_enum_has_correct_paths() throws Exception {
        assertThat(IndependentApi.Subscriptions.FRONT_PAGE.getPath(), is("11831/json"));
        assertThat(IndependentApi.Subscriptions.WORLD.getPath(), is("11916/json"));
        assertThat(IndependentApi.Subscriptions.SPORT.getPath(), is("12791/json"));
        assertThat(IndependentApi.Subscriptions.BUSINESS.getPath(), is("11981/json"));
    }

    @Test
    public void get_baseurl_returns_correct_url() throws Exception {
        IndependentApi api = newApi();
        assertThat(api.getBaseUrl(), is("http://www.independent.co.uk/api/v1/"));
    }

    @NonNull
    private IndependentApi newApi() {
        return new IndependentApi();
    }
}