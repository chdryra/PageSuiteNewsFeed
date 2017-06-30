package com.chdryra.android.pagesuitenewsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.chdryra.android.jsoncapture.ArticlePOJO;
import com.chdryra.android.jsoncapture.FeedsPOJO;
import com.chdryra.android.jsoncapture.ResponsePOJO;
import com.chdryra.android.model.FactoryJsonClient;
import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.IndependentApiService;
import com.chdryra.android.model.JsonApi;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.chdryra.android.pagesuitenewsfeed", appContext.getPackageName());
    }

    @Test
    public void checkFrontPage() throws Exception {
        IndependentApi api = newApi();
        Retrofit client = newClient(api);
        IndependentApiService service = newService(client);
        JsonApi.Channel<IndependentApi> channel
                = api.getChannel(IndependentApi.Feed.FRONT_PAGE);
        Call<ResponsePOJO> response = service.getChannelResponse(channel.getChannelPath());

        final CountDownLatch latch = new CountDownLatch(1);
        response.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                ResponsePOJO body = response.body();
                latch.countDown();
                if(body != null) {
                    FeedsPOJO feeds = body.getFeeds();
                    List<ArticlePOJO> articles = body.getArticles();
                }
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                latch.countDown();
            }
        });

        latch.await(5000, TimeUnit.MILLISECONDS);
    }

    @NonNull
    private IndependentApi newApi() {
        return new IndependentApi();
    }

    private IndependentApiService newService(Retrofit client) {
        return client.create(IndependentApiService.class);
    }

    private Retrofit newClient(IndependentApi api) {
        FactoryJsonClient clientFactory = new FactoryJsonClient();
        return clientFactory.newClient(api.getBaseUrl());
    }
}
