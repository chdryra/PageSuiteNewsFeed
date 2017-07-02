package com.chdryra.android.model;

import org.parceler.Parcel;

@Parcel
public class ArticleSettings {
    private int mState;
    private int mEditorialPriority;
    private boolean mCommentsSetting;

    public ArticleSettings() {
    }

    public ArticleSettings(int state, int editorialPriority, boolean commentsSetting) {
        mState = state;
        mEditorialPriority = editorialPriority;
        mCommentsSetting = commentsSetting;
    }

    public int getState() {
        return mState;
    }

    public int getEditorialPriority() {
        return mEditorialPriority;
    }

    public boolean isCommentsSetting() {
        return mCommentsSetting;
    }
}