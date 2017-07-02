package com.chdryra.android.pagesuitenewsfeed;

import com.chdryra.android.jsoncapture.ArticlePOJO;
import com.chdryra.android.jsoncapture.AuthorPOJO;
import com.chdryra.android.jsoncapture.ImagePOJO;
import com.chdryra.android.jsoncapture.NewsFeedPOJO;
import com.chdryra.android.jsoncapture.PojoToModelConverter;
import com.chdryra.android.jsoncapture.VideoPOJO;
import com.chdryra.android.model.Article;
import com.chdryra.android.model.ArticleAuthors;
import com.chdryra.android.model.ArticleBody;
import com.chdryra.android.model.ArticleDate;
import com.chdryra.android.model.ArticleHeadlines;
import com.chdryra.android.model.ArticleMedia;
import com.chdryra.android.model.ArticleSettings;
import com.chdryra.android.model.ArticleTopics;
import com.chdryra.android.model.ArticleUrl;
import com.chdryra.android.model.Author;
import com.chdryra.android.model.FactoryJsonClient;
import com.chdryra.android.model.Image;
import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.JsonApi;
import com.chdryra.android.model.NewsFeed;
import com.chdryra.android.model.Video;

import org.junit.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class PojoToModelConverterTests {
    @Test
    public void convert_pojo_to_model_works() {
        final AsyncLatch latch = new AsyncLatch();
        IndependentApi api = new IndependentApi();
        IndependentApi.Service service = newService(newClient(api));
        IndependentApi.Subscriptions frontPage = IndependentApi.Subscriptions.FRONT_PAGE;
        Call<NewsFeedPOJO> response = service.getQueryResponse(frontPage.getPath());

        response.enqueue(new Callback<NewsFeedPOJO>() {
            @Override
            public void onResponse(Call<NewsFeedPOJO> call, Response<NewsFeedPOJO> response) {
                NewsFeedPOJO body = response.body();
                assertThat(body, is(notNullValue()));
                testResponse(latch, body);
            }

            @Override
            public void onFailure(Call<NewsFeedPOJO> call, Throwable t) {
                fail(t.getMessage());
                latch.trigger();
            }
        });
        

        latch.waitForTrigger();
        assertThat(latch.wasTriggered(), is(true));
    }

    private IndependentApi.Service newService(Retrofit client) {
        return client.create(IndependentApi.Service.class);
    }

    private Retrofit newClient(JsonApi api) {
        FactoryJsonClient clientFactory = new FactoryJsonClient();
        return clientFactory.newClient(api.getBaseUrl());
    }
    
    private void testResponse(AsyncLatch latch, NewsFeedPOJO response) {
        PojoToModelConverter converter = new PojoToModelConverter();
        NewsFeed model = converter.convert(response);
        testFeedsAndSection(model, response);
        testArticles(model, response);
        latch.trigger();
    }

    private void testArticles(NewsFeed model, NewsFeedPOJO response) {
        List<ArticlePOJO> articlePojos = response.getArticles();
        List<Article> articleModels = model.getArticles();
        assertThat(articleModels.size(), is(articlePojos.size()));
        for(int i = 0; i < articlePojos.size(); ++i) {
            testArticle(articleModels.get(i), articlePojos.get(i));
        }
    }

    private void testArticle(Article article, ArticlePOJO articlePOJO) {
        assertThat(article.getGuid(), is(articlePOJO.getGuid()));
        testSettings(article.getSettings(), articlePOJO);
        testUrl(article.getUrl(), articlePOJO);
        testHeadline(article.getHeadlines(), articlePOJO);
        testAuthors(article.getAuthors(), articlePOJO);
        testDate(article.getDate(), articlePOJO);
        testTopics(article.getTopics(), articlePOJO);
        testMedia(article.getMedia(), articlePOJO);
        testBody(article.getBody(), articlePOJO);
    }

    private void testBody(ArticleBody body, ArticlePOJO articlePOJO) {
        assertThat(body.getBody(), is(articlePOJO.getBody()));
    }

    private void testMedia(ArticleMedia media, ArticlePOJO articlePOJO) {
        Image image = media.getImage();
        Image imageOverride = media.getImageOverride();
        Video video = media.getVideo();

        ImagePOJO imagePojo = articlePOJO.getImage();
        ImagePOJO imageOverridePojo = articlePOJO.getImageOverride();
        VideoPOJO videoPojo = articlePOJO.getVideo();

        checkImage(image, imagePojo);
        if(imageOverride != null) {
            checkImage(imageOverride, imageOverridePojo);
        } else {
            assertThat(imageOverridePojo, is(nullValue()));
        }

        if(video != null) {
            assertThat(videoPojo, is(notNullValue()));
            if(videoPojo!= null) {
                assertThat(video.getId(), is(videoPojo.getId()));
                assertThat(video.getTitle(), is(videoPojo.getTitle()));
                checkUrl(video.getThumbnail(), videoPojo.getThumbnail());

            }
        } else {
            assertThat(videoPojo, is(nullValue()));
        }
    }

    private void checkImage(Image image, ImagePOJO imagePojo) {
        checkUrl(image.getUrl(), imagePojo.getUrl());
        checkUrl(image.getThumbnail(), imagePojo.getThumbnail());
        checkNullableString(image.getCopyright(), imagePojo.getCopyright());
        checkNullableString(image.getCaption(), imagePojo.getCaption());
        assertThat(image.getTitle(), is(imagePojo.getTitle()));
    }

    private void testTopics(ArticleTopics topics, ArticlePOJO articlePOJO) {
        assertThat(topics.getTopics(), is(articlePOJO.getTopics()));
        assertThat(topics.getTags(), is(articlePOJO.getTags()));
    }

    private void testDate(ArticleDate date, ArticlePOJO articlePOJO) {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        assertThat(df.format(date.getPublishDate()), is(articlePOJO.getPublishDate()));
        assertThat(df.format(date.getUpdatedDate()), is(articlePOJO.getUpdatedDate()));
    }

    private void testAuthors(ArticleAuthors authors, ArticlePOJO articlePOJO) {
        Author primaryAuthor = authors.getPrimaryAuthor();
        assertThat(primaryAuthor.getName(), is(articlePOJO.getAuthor()));

        List<Author> authorsList = authors.getAuthors();
        List<AuthorPOJO> pojosList = articlePOJO.getAuthors();
        assertThat(authorsList.size(), is(pojosList.size()));
        for(int i = 0; i < authorsList.size(); ++i) {
            Author author = authorsList.get(i);
            AuthorPOJO pojo = pojosList.get(i);
            assertThat(author.getName(), is(pojo.getName()));
            checkNullableString(author.getJobTitle(), pojo.getJobTitle());
            checkNullableString(author.getTwitterHandle(), pojo.getTwitterHandle());
            if(author.getName().equals(primaryAuthor.getName())) {
                assertThat(primaryAuthor, is(author));
            }
        }

        checkNullableString(authors.getAuthorLocation(), articlePOJO.getAuthorLocation());
    }

    private void testHeadline(ArticleHeadlines headline, ArticlePOJO articlePOJO) {
        assertThat(headline.getHeadline(), is(articlePOJO.getHeadline()));
        assertThat(headline.getShortHeadline(), is(articlePOJO.getShortHeadline()));
        checkNullableString(headline.getSubHeadline(), articlePOJO.getSubHeadline());
        checkNullableString(headline.getHeadlineOverride(), articlePOJO.getHeadlineOverride());
        checkNullableString(headline.getLocalCaption(), articlePOJO.getLocalCaption());
    }

    private void checkNullableString(String model, String pojo) {
        if(model.length() > 0) {
            assertThat(model, is(pojo));
        } else {
            assertThat(pojo, isEmptyOrNullString());
        }
    }

    private void testUrl(ArticleUrl url, ArticlePOJO articlePOJO) {
        URL modelUrl = url.getUrl();
        URL modelLink = url.getLink();
        assertThat(modelUrl, is(notNullValue()));
        checkUrl(modelUrl, articlePOJO.getUrl());
        checkUrl(modelLink, articlePOJO.getLink());
    }

    private void checkUrl(URL model, String pojo) {
        if (model != null) assertThat(model.toString(), is(pojo));
    }

    private void testSettings(ArticleSettings settings, ArticlePOJO articlePOJO) {
        assertThat(settings.getEditorialPriority(), is(articlePOJO.getEditorialPriority()));
        assertThat(settings.getState(), is(articlePOJO.getState()));
        boolean commentsSetting = settings.isCommentsSetting();
        if(commentsSetting) {
            assertThat(articlePOJO.getCommentsSetting(), is("on"));
        } else {
            assertThat(articlePOJO.getCommentsSetting(), is("off"));
        }
    }

    private void testFeedsAndSection(NewsFeed model, NewsFeedPOJO response) {
        assertThat(model.getSection(), is(response.getSection()));
        assertThat(model.getUrl().toString(), is(response.getUrl()));
        assertThat(model.getFeeds().getSectionName(), is(response.getFeeds().getSectionName()));
        URL sectionUrl = model.getFeeds().getSectionUrl();
        if(sectionUrl != null) assertThat(sectionUrl.toString(), is(response.getFeeds().getSectionUrl()));
    }
}
