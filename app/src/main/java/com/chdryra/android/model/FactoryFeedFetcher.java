package com.chdryra.android.model;

import com.chdryra.android.jsoncapture.PojoToModelConverter;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class FactoryFeedFetcher {
    public IndependentFetcher newIndependentFetcher() {
        return new IndependentFetcher(new IndependentApi(), new RetrofitFetcher(), new PojoToModelConverter());
    }
}
