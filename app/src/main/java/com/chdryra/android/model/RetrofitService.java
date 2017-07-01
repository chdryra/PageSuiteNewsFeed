package com.chdryra.android.model;

import retrofit2.Call;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class RetrofitService<Service, Response> {
    private Class<Service> mServiceClass;
    private ServiceCall<Service, Response> mCallExecuter;

    public interface ServiceCall<Service, Response> {
        Call<Response> executeCall(Service service, String path);
    }

    public RetrofitService(Class<Service> serviceClass, ServiceCall<Service, Response> callExecuter) {
        mServiceClass = serviceClass;
        mCallExecuter = callExecuter;
    }

    public Class<Service> getServiceClass() {
        return mServiceClass;
    }

    public ServiceCall<Service, Response> getCallExecuter() {
        return mCallExecuter;
    }
}
