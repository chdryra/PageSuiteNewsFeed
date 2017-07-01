package com.chdryra.android.model;

import android.support.annotation.NonNull;

import com.chdryra.android.jsoncapture.NewsFeedPOJO;
import com.chdryra.android.jsoncapture.PojoToModelConverter;

import retrofit2.Call;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class IndependentFetcher {
    private final IndependentApi mApi;
    private final RetrofitFetcher mFetcher;
    private final PojoToModelConverter mConverter;

    public interface FetcherCallback {
        void onFetched(NewsFeed response);

        void onFailed(String message);
    }

    public IndependentFetcher(IndependentApi api,
                              RetrofitFetcher fetcher,
                              PojoToModelConverter converter) {
        mApi = api;
        mFetcher = fetcher;
        mConverter = converter;
    }

    public void fetch(IndependentApi.Feed feed,
                      final FetcherCallback callback) {
        RetrofitService<IndependentApi.Service, NewsFeedPOJO> service
                = new RetrofitService<>(IndependentApi.Service.class, getServiceCall());

        mFetcher.fetch(mApi, feed.name(), service, new RetrofitFetcher.FetcherCallback<NewsFeedPOJO>() {
            @Override
            public void onFetched(NewsFeedPOJO newsFeedPOJO) {
                callback.onFetched(mConverter.convert(newsFeedPOJO));
            }

            @Override
            public void onFailed(String message) {
                callback.onFailed(message);
            }
        });
    }

    @NonNull
    private RetrofitService.ServiceCall<IndependentApi.Service, NewsFeedPOJO> getServiceCall() {
        return new RetrofitService.ServiceCall<IndependentApi.Service, NewsFeedPOJO>() {
            @Override
            public Call<NewsFeedPOJO> executeCall(IndependentApi.Service service, String path) {
                return service.getQueryResponse(path);
            }
        };
    }
}
