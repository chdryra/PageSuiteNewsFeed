package com.chdryra.android.model;

import com.chdryra.android.jsoncapture.NewsFeedPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class IndependentJsonFetcher {
    private final IndependentApi mApi;

    public interface FetcherCallback {
        void onFetched(NewsFeedPOJO response);

        void onFailed(String message);
    }

    public IndependentJsonFetcher(IndependentApi api) {
        mApi = api;
    }

    public void fetch(IndependentApi.Feed feed, final FetcherCallback callback) {
        IndependentApi.Service service = newService(newClient(mApi));
        JsonApi.ServicePath servicePath = mApi.getServicePath(feed);
        Call<NewsFeedPOJO> response = service.getQueryResponse(servicePath.getPath());

        response.enqueue(new Callback<NewsFeedPOJO>() {
            @Override
            public void onResponse(Call<NewsFeedPOJO> call, Response<NewsFeedPOJO> response) {
                NewsFeedPOJO body = response.body();
                if(body != null) {
                    callback.onFetched(body);
                } else {
                    callback.onFailed("Response is null");
                }
            }

            @Override
            public void onFailure(Call<NewsFeedPOJO> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    private IndependentApi.Service newService(Retrofit client) {
        return client.create(IndependentApi.Service.class);
    }

    private Retrofit newClient(IndependentApi api) {
        FactoryJsonClient clientFactory = new FactoryJsonClient();
        return clientFactory.newClient(api.getBaseUrl());
    }

}
