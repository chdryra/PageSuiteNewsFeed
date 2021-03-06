package com.chdryra.android.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class RetrofitFetcher {
    public interface FetcherCallback<Response> {
        void onFetched(Response response);

        void onFailed(String message);
    }

    public <Api extends JsonApi, Service, ReturnType>
    void fetch(JsonSubs<Api>.Subscription sub,
               RetrofitService<Service, ReturnType> service,
               final FetcherCallback<ReturnType> callback) {
        Service fetcherService = newService(newClient(sub.getApi()), service);
        Call<ReturnType> response = service.getCall().execute(fetcherService, sub.getPath());

        response.enqueue(new Callback<ReturnType>() {
            @Override
            public void onResponse(Call<ReturnType> call, Response<ReturnType> response) {
                ReturnType body = response.body();
                if(body != null) {
                    callback.onFetched(body);
                } else {
                    callback.onFailed("Response is null");
                }
            }

            @Override
            public void onFailure(Call<ReturnType> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    private <Service> Service newService(Retrofit client, 
                                         RetrofitService<Service, ?> service) {
        return client.create(service.getServiceClass());
    }

    private Retrofit newClient(JsonApi api) {
        FactoryJsonClient clientFactory = new FactoryJsonClient();
        return clientFactory.newClient(api.getBaseUrl());
    }

}
