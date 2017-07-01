package com.chdryra.android.pagesuitenewsfeed;

import android.support.annotation.NonNull;

import com.chdryra.android.model.JsonApi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * For the async network calls to the Independent Api.
 *
 */
public class JsonApiTests {

    private static final String URL = "http://news.bbc.co.uk/";
    private static final String PATH_KEY = "key";
    private static final String PATH_STRING = "path";

    @Test
    public void get_baseurl_returns_correct_url() throws Exception {
        JsonApi api = newApi();
        assertThat(api.getBaseUrl(), is(URL));
    }

    @NonNull
    private JsonApi newApi() {
        return new JsonApi(URL);
    }

    @Test
    public void get_service_path_when_no_path_returns_not_null() throws Exception {
        JsonApi api = newApi();
        JsonApi.ServicePath path = api.getServicePath(PATH_KEY);
        assertThat(path, is(notNullValue()));
    }

    @Test
    public void get_service_path_when_no_path_returns_null_path_object() throws Exception {
        JsonApi api = newApi();
        JsonApi.ServicePath path = api.getServicePath(PATH_KEY);
        assertThat(path, is(JsonApi.ServicePath.NULL_PATH));
    }

    @Test
    public void add_and_get_service_path_return_correct_value() throws Exception {
        JsonApi api = newApi();
        api.addServicePath(PATH_KEY, PATH_STRING);
        JsonApi.ServicePath path = api.getServicePath(PATH_KEY);
        assertThat(path.getPathKey(), is(PATH_KEY));
        assertThat(path.getPath(), is(PATH_STRING));
    }

    @Test
    public void get_service_paths_when_no_paths_returns_empty_collection() throws Exception {
        JsonApi api = newApi();
        Collection<JsonApi.ServicePath> paths = api.getServicePaths();
        assertThat(paths.isEmpty(), is(true));
    }

    @Test
    public void get_service_paths_when_paths_returns_correct_collection() throws Exception {
        int num = 3;
        JsonApi api = newApi();
        ArrayList<String> pathKeys = new ArrayList<>();
        ArrayList<String> pathStrings = new ArrayList<>();
        for(int i = 0; i < num; ++i) {
            String pathString = PATH_STRING + String.valueOf(i);
            String pathKey = pathString + PATH_KEY;
            pathKeys.add(pathKey);
            pathStrings.add(pathString);
            api.addServicePath(pathKey, pathString);
        }

        Collection<JsonApi.ServicePath> paths = api.getServicePaths();
        assertThat(paths.isEmpty(), is(false));
        assertThat(paths.size(), is(num));
        int i = 0;
        for(JsonApi.ServicePath servicePath : paths) {
            String pathKey = servicePath.getPathKey();
            String pathString = servicePath.getPath();

            assertThat(pathKeys.contains(pathKey), is(true));
            assertThat(pathStrings.contains(pathString), is(true));

            pathKeys.remove(pathKey);
            pathStrings.remove(pathString);
        }
    }
}