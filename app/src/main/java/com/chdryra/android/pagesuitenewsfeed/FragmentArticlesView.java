/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.pagesuitenewsfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chdryra.android.model.Article;
import com.chdryra.android.model.FactoryFeedFetcher;
import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.IndependentFetcher;
import com.chdryra.android.model.JsonSubs;
import com.chdryra.android.model.NewsFeed;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 23/01/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FragmentArticlesView extends Fragment implements IndependentFetcher.FetcherCallback{
    private static final int LAYOUT = R.layout.fragment_list_articles;

    private RecyclerView mRecyclerView;
    private ArticlesAdapter mAdapter;

    public static FragmentArticlesView newInstance() {
        return new FragmentArticlesView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onFetched(NewsFeed response) {
        mAdapter.setArticles(response.getArticles());
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(LAYOUT, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_articles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ArticlesAdapter(new ArrayList<Article>());
        mRecyclerView.setAdapter(mAdapter);

        JsonSubs<IndependentApi> subs = new JsonSubs<>(new IndependentApi());
        IndependentApi.Subscriptions frontPage = IndependentApi.Subscriptions.FRONT_PAGE;
        subs.addSubscription(frontPage.name(), frontPage.getPath());
        IndependentFetcher fetcher = new FactoryFeedFetcher().newIndependentFetcher();
        fetcher.fetch(subs.getSubscription(frontPage.name()), this);

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, android.view.MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
