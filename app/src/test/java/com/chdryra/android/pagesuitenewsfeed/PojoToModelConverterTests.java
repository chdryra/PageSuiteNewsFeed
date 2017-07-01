package com.chdryra.android.pagesuitenewsfeed;

import com.chdryra.android.jsoncapture.NewsFeedPOJO;
import com.chdryra.android.jsoncapture.PojoToModelConverter;
import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.IndependentJsonFetcher;
import com.chdryra.android.model.NewsFeed;

import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class PojoToModelConverterTests {
    @Test
    public void convert_pojo_to_model_works() {
        IndependentJsonFetcher fetcher = new IndependentJsonFetcher(new IndependentApi());

        final AsyncLatch latch = new AsyncLatch();

        fetcher.fetch(IndependentApi.Feed.FRONT_PAGE, new IndependentJsonFetcher.FetcherCallback() {
            @Override
            public void onFetched(NewsFeedPOJO response) {
                testResponse(latch, response);
            }

            @Override
            public void onFailed(String message) {
                fail(message);
                latch.trigger();
            }
        });

        latch.waitForTrigger();
        assertThat(latch.wasTriggered(), is(true));
    }

    private void testResponse(AsyncLatch latch, NewsFeedPOJO response) {
        PojoToModelConverter converter = new PojoToModelConverter();
        NewsFeed model = converter.convert(response);
        latch.trigger();
    }
}
