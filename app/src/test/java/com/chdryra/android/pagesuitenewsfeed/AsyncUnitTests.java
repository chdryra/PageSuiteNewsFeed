package com.chdryra.android.pagesuitenewsfeed;

import android.support.annotation.NonNull;

import com.chdryra.android.jsoncapture.NewsFeedPOJO;
import com.chdryra.android.model.FactoryJsonClient;
import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.JsonApi;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * For the async network calls to the Independent Api.
 *
 */
public class AsyncUnitTests {
    private static final int WAIT_TIME = 1000;

    @Test
    public void test_frontpage_response_is_notnull() throws Exception {
        test_api_feed_response_is_notnull(IndependentApi.Feed.FRONT_PAGE);
    }

    @Test
    public void test_world_response_is_notnull() throws Exception {
        test_api_feed_response_is_notnull(IndependentApi.Feed.WORLD);
    }

    @Test
    public void test_business_response_is_notnull() throws Exception {
        test_api_feed_response_is_notnull(IndependentApi.Feed.BUSINESS);
    }

    @Test
    public void test_sport_response_is_notnull() throws Exception {
        test_api_feed_response_is_notnull(IndependentApi.Feed.SPORT);
    }

    private void test_api_feed_response_is_notnull(IndependentApi.Feed feed) {
        IndependentApi api = newApi();
        IndependentApi.Service service = newService(newClient(api));
        JsonApi.ServicePath servicePath = api.getServicePath(feed);
        Call<NewsFeedPOJO> response = service.getQueryResponse(servicePath.getPath());

        final AsyncLatch latch = newAsyncLatch();

        response.enqueue(new Callback<NewsFeedPOJO>() {
            @Override
            public void onResponse(Call<NewsFeedPOJO> call, Response<NewsFeedPOJO> response) {
                assertThat(response.body(), notNullValue());
                latch.trigger();
            }

            @Override
            public void onFailure(Call<NewsFeedPOJO> call, Throwable t) {
                fail("Response failed: " + t.toString());
                latch.trigger();
            }
        });

        waitForTrigger(latch);
    }

    private void waitForTrigger(AsyncLatch latch) {
        latch.waitForTrigger();
        assertThat(latch.wasTriggered(), is(true));
    }

    @NonNull
    private IndependentApi newApi() {
        return new IndependentApi();
    }

    private IndependentApi.Service newService(Retrofit client) {
        return client.create(IndependentApi.Service.class);
    }

    private Retrofit newClient(IndependentApi api) {
        FactoryJsonClient clientFactory = new FactoryJsonClient();
        return clientFactory.newClient(api.getBaseUrl());
    }

    private AsyncLatch newAsyncLatch() {
        return new AsyncLatch(WAIT_TIME);
    }

}