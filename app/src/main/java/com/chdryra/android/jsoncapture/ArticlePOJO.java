package com.chdryra.android.jsoncapture;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 30/06/2017
 * Email: rizwan.choudrey@gmail.com
 * <p>
 * This is the POJO for capturing articles. Adding GSON SerializedName annotations for
 * reference purposes to match original JSON fields, and so that any serialization will match the
 * original source JSON.
 *
 * I have not been able to find any documentation for the JSON API so data types and nullability
 * are inferred from the raw JSON.
 * </p>
 */

public class ArticlePOJO {
    @SerializedName("guid")
    private long mGuid;

    @SerializedName("state")
    private int mState;

    @SerializedName("headline_override")
    private String mHeadlineOverride;

    @SerializedName("headline")
    private String mHeadline;

    //url and link seem to be the same...
    @SerializedName("url")
    private String mUrl;

    @SerializedName("link")
    private String mLink;

    @SerializedName("authors")
    private List<AuthorPOJO> mAuthors;

    //seems to be interchangeable with job title in AuthorPOJO
    @SerializedName("author_location")
    @Nullable
    private String mAuthorLocation;

    @SerializedName("updated_date")
    private String mUpdatedDate;

    @SerializedName("editorial_policy")
    private int mEditorialPriority;

    @SerializedName("short_headline")
    private String mShortHeadline;

    @SerializedName("sub_headline")
    @Nullable
    private String mSubHeadline;

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("local_caption")
    @Nullable
    private String mLocalCaption;

    @SerializedName("comments_setting")
    private String mCommentsSetting;

    @SerializedName("publish_date")
    private String mPublishDate;

    // topics and tags seem to be the same...
    @SerializedName("topics")
    private List<String> mTopics;

    @SerializedName("tags")
    private List<String> mTags;

    @SerializedName("section")
    private String mSection;

    @SerializedName("section_url")
    private String mSectionUrl;

    @SerializedName("image")
    private ImagePOJO mImage;

    @SerializedName("image_override")
    @Nullable
    private ImagePOJO mImageOverride;

    @SerializedName("video")
    @Nullable
    private VideoPOJO mVideo;

    @SerializedName("body")
    private String mBody;

    public long getGuid() {
        return mGuid;
    }

    public int getState() {
        return mState;
    }

    public String getHeadlineOverride() {
        return mHeadlineOverride;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getLink() {
        return mLink;
    }

    @Nullable
    public List<AuthorPOJO> getAuthors() {
        return mAuthors;
    }

    @Nullable
    public String getAuthorLocation() {
        return mAuthorLocation;
    }

    public String getUpdatedDate() {
        return mUpdatedDate;
    }

    public int getEditorialPriority() {
        return mEditorialPriority;
    }

    public String getShortHeadline() {
        return mShortHeadline;
    }

    @Nullable
    public String getSubHeadline() {
        return mSubHeadline;
    }

    public String getAuthor() {
        return mAuthor;
    }

    @Nullable
    public String getLocalCaption() {
        return mLocalCaption;
    }

    public String getCommentsSetting() {
        return mCommentsSetting;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public List<String> getTopics() {
        return mTopics;
    }

    public List<String> getTags() {
        return mTags;
    }

    public String getSection() {
        return mSection;
    }

    public String getSectionUrl() {
        return mSectionUrl;
    }

    public ImagePOJO getImage() {
        return mImage;
    }

    @Nullable
    public ImagePOJO getImageOverride() {
        return mImageOverride;
    }

    @Nullable
    public VideoPOJO getVideo() {
        return mVideo;
    }

    public String getBody() {
        return mBody;
    }
}
