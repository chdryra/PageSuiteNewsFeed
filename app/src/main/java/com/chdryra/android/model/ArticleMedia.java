package com.chdryra.android.model;

import android.support.annotation.Nullable;

import org.parceler.Parcel;

@Parcel
public class ArticleMedia {
    private Image mImage;
    private Image mImageOverride;
    private Video mVideo;

    public ArticleMedia() {
    }

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