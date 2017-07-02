package com.chdryra.android.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class JsonSubs<T extends JsonApi> {
    private final T mApi;
    private final Map<String, String> mSubs;

    public JsonSubs(T api) {
        mApi = api;
        mSubs = new HashMap<>();
    }

    public T getApi() {
        return mApi;
    }

    public void addSubscription(String name, String path) {
        mSubs.put(name, path);
    }

    public String getPath(String name) {
        return mSubs.containsKey(name) ? mSubs.get(name) : "";
    }

    public Subscription getSubscription(String name) {
        return new Subscription(name);
    }

    public ArrayList<String> getSubscriptionNames() {
        ArrayList<String> names = new ArrayList<>();
        names.addAll(mSubs.keySet());

        return names;
    }

    public class Subscription {
        private final String mName;

        private Subscription(String name) {
            mName = name;
        }

        public T getApi() {
            return JsonSubs.this.getApi();
        }

        public String getName() {
            return mName;
        }

        public String getPath() {
            return JsonSubs.this.getPath(mName);
        }
    }
}
