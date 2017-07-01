package com.chdryra.android.model;

import android.support.annotation.Nullable;

public class ArticleMedia {
    private final Image mImage;
    private final Image mImageOverride;
    private final Video mVideo;

    public ArticleMedia(Image image, @Nullable Image imageOverride, @Nullable Video video) {
        mImage = image;
        mImageOverride = imageOverride;
        mVideo = video;
    }

    public Image getImage() {
        return mImage;
    }

    @Nullable
    public Image getImageOverride() {
        return mImageOverride;
    }

    @Nullable
    public Video getVideo() {
        return mVideo;
    }
}