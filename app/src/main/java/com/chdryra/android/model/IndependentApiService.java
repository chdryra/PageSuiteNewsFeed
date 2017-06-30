package com.chdryra.android.model;

import com.chdryra.android.jsoncapture.ResponsePOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public interface IndependentApiService {
    @GET("{code}/json")
    Call<ResponsePOJO> getChannelResponse(@Path("code") String code);
}
