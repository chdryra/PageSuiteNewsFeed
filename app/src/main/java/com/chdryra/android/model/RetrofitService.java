package com.chdryra.android.model;

import retrofit2.Call;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class RetrofitService<Service, Response> {
    private Class<Service> mServiceClass;
    private ServiceCall<Service, Response> mCall;

    public interface ServiceCall<Service, Response> {
        Call<Response> execute(Service service, String path);
    }

    public RetrofitService(Class<Service> serviceClass, ServiceCall<Service, Response> call) {
        mServiceClass = serviceClass;
        mCall = call;
    }

    public Class<Service> getServiceClass() {
        return mServiceClass;
    }

    public ServiceCall<Service, Response> getCall() {
        return mCall;
    }
}
