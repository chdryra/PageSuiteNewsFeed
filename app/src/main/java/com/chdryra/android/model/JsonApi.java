package com.chdryra.android.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class JsonApi<T extends JsonApi> {
    private String mBaseUrl;
    private Map<String, Channel<T>> mChannels;

    JsonApi(String baseUrl) {
        mBaseUrl = baseUrl;
        mChannels = new HashMap<>();
    }

    void addChannel(String channelName, String channelPath) {
        mChannels.put(channelName, new Channel<T>(channelName, channelPath));
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public Channel<T> getChannel(String name) {
        return mChannels.containsKey(name) ? mChannels.get(name) : new Channel<T>("", "");
    }

    public Collection<Channel<T>> getChannels() {
        return mChannels.values();
    }

    public static class Channel<T extends JsonApi> {
        private String mChannelName;
        private String mChannelPath;

        private Channel(String channelName, String channelPath) {
            mChannelName = channelName;
            mChannelPath = channelPath;
        }

        public String getChannelName() {
            return mChannelName;
        }

        public String getChannelPath() {
            return mChannelPath;
        }
    }
}
