package com.chdryra.android.jsoncapture;

import android.support.annotation.Nullable;

import com.chdryra.android.model.Article;
import com.chdryra.android.model.ArticleAuthors;
import com.chdryra.android.model.ArticleBody;
import com.chdryra.android.model.ArticleDate;
import com.chdryra.android.model.ArticleHeadlines;
import com.chdryra.android.model.ArticleMedia;
import com.chdryra.android.model.ArticleSection;
import com.chdryra.android.model.ArticleSettings;
import com.chdryra.android.model.ArticleTopics;
import com.chdryra.android.model.ArticleUrl;
import com.chdryra.android.model.Author;
import com.chdryra.android.model.Image;
import com.chdryra.android.model.NewsFeed;
import com.chdryra.android.model.Video;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class PojoToModelConverter {
    public NewsFeed convert(NewsFeedPOJO pojo) {
        List<Article> articles = new ArrayList<>();
        for(ArticlePOJO articlePojo : pojo.getArticles()) {
            articles.add(convert(articlePojo));
        }
        FeedsPOJO feeds = pojo.getFeeds();
        return new NewsFeed(newSection(feeds.getSectionName(), asUrl(feeds.getSectionUrl())),
                articles, pojo.getSection(), asUrl(pojo.getUrl()));
    }

    private ArticleSection newSection(String sectionName, @Nullable URL sectionUrl) {
        return new ArticleSection(sectionName, sectionUrl);
    }

    private ArticleSettings newSettings(ArticlePOJO pojo) {
        return new ArticleSettings(pojo.getState(),
                pojo.getEditorialPriority(), pojo.getCommentsSetting().equals("on"));
    }
    private Article convert(ArticlePOJO pojo) {
        return new Article(pojo.getGuid(),
                newSettings(pojo),
                newUrl(pojo),
                newHeadline(pojo),
                newAuthors(pojo),
                newDate(pojo),
                newTopics(pojo),
                newMedia(pojo),
                newBody(pojo));
    }

    private ArticleTopics newTopics(ArticlePOJO pojo) {
        return new ArticleTopics(pojo.getTopics(), pojo.getTags());
    }

    private ArticleBody newBody(ArticlePOJO pojo) {
        return new ArticleBody(pojo.getBody());
    }

    private ArticleMedia newMedia(ArticlePOJO pojo) {
        return new ArticleMedia(convert(pojo.getImage()), convert(pojo.getImageOverride()), convert(pojo.getVideo()));
    }

    private ArticleDate newDate(ArticlePOJO pojo) {
        Date publishDate = asDate(pojo.getPublishDate());
        Date updatedDate = asDateNullable(pojo.getUpdatedDate());
        return new ArticleDate(publishDate, updatedDate == null ? publishDate : updatedDate);
    }

    @Nullable
    private Date asDate(String stringDate) {
        Date date = asDateNullable(stringDate);
        return date == null ? new Date() : date;
    }

    @Nullable
    private Date asDateNullable(String stringDate) {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date date = null;
        try {
            date = df.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private ArticleAuthors newAuthors(ArticlePOJO pojo) {
        List<AuthorPOJO> authorPojos = pojo.getAuthors();
        List<Author> authors = new ArrayList<>();
        Author primary = new Author(pojo.getAuthor(), "", "");
        for(AuthorPOJO authorPojo : authorPojos) {
            Author author = convert(authorPojo);
            authors.add(author);
            if(author.getName().equals(primary.getName())) primary = author;
        }

        return new ArticleAuthors(authors, primary, asString(pojo.getAuthorLocation()));
    }

    private Author convert(AuthorPOJO pojo) {
        return new Author(asString(pojo.getName()), asString(pojo.getJobTitle()), asString(pojo.getTwitterHandle()));
    }

    private ArticleHeadlines newHeadline(ArticlePOJO pojo) {
        return new ArticleHeadlines(asString(pojo.getHeadline()),
                asString(pojo.getHeadlineOverride()),
                asString(pojo.getShortHeadline()),
                asString(pojo.getSubHeadline()),
                asString(pojo.getLocalCaption()));
    }

    private ArticleUrl newUrl(ArticlePOJO pojo) {
        return new ArticleUrl(asUrl(pojo.getUrl()), asUrl(pojo.getLink()));
    }

    @Nullable
    private Image convert(@Nullable ImagePOJO pojo) {
        if(pojo == null) return null;
        return new Image(asUrl(pojo.getUrl()), asUrl(pojo.getThumbnail()),
                asString(pojo.getCopyright()), asString(pojo.getCaption()), pojo.getTitle());
    }

    @Nullable
    private Video convert(@Nullable VideoPOJO pojo) {
        if(pojo == null) return null;
        URL thumbnail = asUrl(pojo.getThumbnail());
        return thumbnail == null ? null : new Video(pojo.getId(), thumbnail, pojo.getTitle());
    }

    @Nullable
    private URL asUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private String asString(@Nullable String nullableString) {
        return nullableString == null ? "" : nullableString;
    }
}
