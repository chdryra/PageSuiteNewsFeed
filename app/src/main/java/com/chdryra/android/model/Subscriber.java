package com.chdryra.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */
public class Subscriber {
    private final List<JsonApi.Channel> mChannels;

    public Subscriber() {
        mChannels = new ArrayList<>();
    }

    public void subscribeTo(JsonApi.Channel channel) {
        String path = channel.getChannelPath();
        if (!mChannels.contains(channel)) mChannels.add(channel);
    }

    public List<JsonApi.Channel> getSubscribedTo() {
        return mChannels;
    }
}
