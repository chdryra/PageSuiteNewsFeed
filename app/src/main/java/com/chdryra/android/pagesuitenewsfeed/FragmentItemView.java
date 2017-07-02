/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.pagesuitenewsfeed;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chdryra.android.model.Article;
import com.chdryra.android.model.ArticleAuthors;
import com.chdryra.android.model.ArticleHeadlines;
import com.chdryra.android.model.ArticleSection;
import com.chdryra.android.model.Author;
import com.chdryra.android.model.Image;
import com.chdryra.android.utils.TextUtils;

import org.parceler.Parcels;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by: Rizwan Choudrey
 * On: 23/01/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FragmentItemView extends Fragment {
    private static final int LAYOUT = R.layout.fragment_item_view;
    private static final int MENU = R.menu.menu_item_view;

    private Article mArticle;

    private TextView mHeadline;
    private TextView mSubHeadline;
    private TextView mAuthor;
    private TextView mPosition;
    private TextView mDate;
    private ImageView mImage;
    private TextView mCaption;
    private TextView mBody;
    private TextView mTags;

    public static FragmentItemView newInstance() {
        return new FragmentItemView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(LAYOUT, container, false);

        mHeadline = (TextView) v.findViewById(R.id.headline);
        mSubHeadline = (TextView) v.findViewById(R.id.sub_headline);
        mAuthor = (TextView) v.findViewById(R.id.author);
        mPosition = (TextView) v.findViewById(R.id.position);
        mDate = (TextView) v.findViewById(R.id.date);
        mImage = (ImageView) v.findViewById(R.id.image);
        mCaption = (TextView) v.findViewById(R.id.caption);
        mBody = (TextView) v.findViewById(R.id.body);
        mTags = (TextView) v.findViewById(R.id.tags);

        displayArticle();

        return v;
    }

    private void displayArticle() {
        getArticle();
        setSection();
        setHeadlines();
        setAuthor();
        setDate();
        setImage();
        setBody();
        setTags();
    }

    private void setTags() {
        StringBuilder builder = new StringBuilder();
        for(String tag : mArticle.getTopics().getTags()) {
            builder.append("#");
            builder.append(tag);
            builder.append(" ");
        }
        mTags.setText(builder.toString().trim());
    }

    private void setBody() {
        mBody.setText(TextUtils.fromHtml(mArticle.getBody().getBody()));
        mBody.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setDate() {
        DateFormat df = SimpleDateFormat.getDateInstance();
        mDate.setText(df.format(mArticle.getDate().getUpdatedDate()));
    }

    private void setAuthor() {
        ArticleAuthors authors = mArticle.getAuthors();
        Author author = authors.getPrimaryAuthor();
        mAuthor.setText(author.getName());
        String position = author.getJobTitle();
        if(position.length() == 0) position = authors.getAuthorLocation();
        mPosition.setText(position);
    }

    private void setHeadlines() {
        mHeadline.setText(mArticle.getHeadlines().getHeadline());
        mSubHeadline.setText(mArticle.getHeadlines().getSubHeadline());
    }

    private void setSection() {
        ArticleSection section = mArticle.getSection();
        getActivity().setTitle(section.getSectionName());
    }

    private void setImage() {
        Image image = mArticle.getMedia().getImage();
        Glide.with(mImage.getContext())
                .load(image.getThumbnail()) //need to look at updating this
                .fitCenter()
                .crossFade()
                .into(mImage);
        String caption = image.getCaption();
        String copyright = image.getCopyright();
        if(copyright.length() > 1) {
            caption += " (Copyright: " + copyright + ")";
        }
        mCaption.setText(caption);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, android.view.MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(MENU, menu);
        URL url = mArticle.getUrl().getUrl();
        if(url == null) menu.findItem(R.id.menu_browser).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_browser:
                launchBrowser();
                return true;
            case R.id.menu_share:
                launchShare();
                return true;default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void launchShare() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, formatForSharing());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_with)));
    }

    private String formatForSharing() {
        ArticleHeadlines headlines = mArticle.getHeadlines();
        URL url = mArticle.getUrl().getUrl();

        String headline = headlines.getShortHeadline();
        String text = headline +"\n\n";
        if(url == null) {
            text += headlines.getSubHeadline();
        } else {
            text += url.toString();
        }

        return text;
    }

    private void launchBrowser() {
        URL url = mArticle.getUrl().getUrl();
        if(url != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url.toString()));
            startActivity(i);
        } else {
            makeToast("Link not working...");
        }
    }

    private void makeToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

    private void getArticle() {
        Parcelable data = getActivity().getIntent().getParcelableExtra(FragmentListView.ARTICLE);
        mArticle = Parcels.unwrap(data);
    }
}

