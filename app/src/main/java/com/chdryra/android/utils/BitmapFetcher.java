/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */

public class BitmapFetcher {
    private final BitmapFetcherCallback mListener;

    public interface BitmapFetcherCallback {
        void onBitmapFetched(Bitmap bitmap);
    }

    public BitmapFetcher(BitmapFetcherCallback listener) {
        mListener = listener;
    }

    public void load(URL url) {
        new BitmapFetcherTask().execute(url);
    }

    private class BitmapFetcherTask extends AsyncTask<URL, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(URL... params) {
            URL url = params[0];
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mListener.onBitmapFetched(bitmap);
        }
    }
}
