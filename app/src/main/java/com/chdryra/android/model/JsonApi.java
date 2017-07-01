package com.chdryra.android.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class JsonApi {
    private final String mBaseUrl;
    private final Map<String, ServicePath> mServicePaths;

    public JsonApi(String baseUrl) {
        mBaseUrl = baseUrl;
        mServicePaths = new HashMap<>();
    }

    public void addServicePath(String pathKey, String path) {
        mServicePaths.put(pathKey, new ServicePath(pathKey, path));
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public ServicePath getServicePath(String pathKey) {
        return mServicePaths.containsKey(pathKey) ? mServicePaths.get(pathKey) : ServicePath.NULL_PATH;
    }

    public Collection<ServicePath> getServicePaths() {
        return mServicePaths.values();
    }

    public static class ServicePath {
        public static final ServicePath NULL_PATH = new ServicePath("", "");

        private String mPathKey;
        private String mPath;

        private ServicePath(String pathKey, String path) {
            mPathKey = pathKey;
            mPath = path;
        }

        public String getPathKey() {
            return mPathKey;
        }

        public String getPath() {
            return mPath;
        }
    }
}
