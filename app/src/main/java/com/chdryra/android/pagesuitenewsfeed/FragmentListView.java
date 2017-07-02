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
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chdryra.android.model.Article;
import com.chdryra.android.model.FactoryFeedFetcher;
import com.chdryra.android.model.IndependentApi;
import com.chdryra.android.model.IndependentFetcher;
import com.chdryra.android.model.JsonSubs;
import com.chdryra.android.model.NewsFeed;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 23/01/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FragmentListView extends Fragment
        implements IndependentFetcher.FetcherCallback, RecyclerAdapter.OnItemClickListener<Article>{
    public static final String ARTICLE = "com.chdryra.android.pagesuitenewsfeed.article";
    private static final int LAYOUT = R.layout.fragment_list_view;

    private TextView mTitle;
    private RecyclerAdapter<Article> mAdapter;
    private DrawerLayout mDrawerLayout;
    private JsonSubs<IndependentApi> mSubscriptions;

    public static FragmentListView newInstance() {
        return new FragmentListView();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);

        setSubscriptions();
    }

    @Override
    public void onFetched(NewsFeed response) {
        mAdapter.setData(response.getArticles());
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

        mTitle = (TextView) v.findViewById(R.id.title);

        setDataView(v);
        setNavigationDrawer(v);

        fetchNewsFeed(mSubscriptions.getSubscription(IndependentApi.Subscriptions.FRONT_PAGE.getName()));

        return v;
    }

    private void setDataView(View v) {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_articles);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        mAdapter = new RecyclerAdapter<>(new FactoryArticleViewHolder(), new ArrayList<Article>()
                , this);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
                manager.getOrientation());
        recyclerView.addItemDecoration(divider);
    }

    private void setNavigationDrawer(View v) {
        mDrawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        ListView drawerList = (ListView) v.findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_list_item_1, mSubscriptions.getSubscriptionNames()));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = mSubscriptions.getSubscriptionNames().get(position);
                fetchNewsFeed(mSubscriptions.getSubscription(name));
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void fetchNewsFeed(JsonSubs<IndependentApi>.Subscription sub) {
        mTitle.setText(sub.getName());
        IndependentFetcher fetcher = new FactoryFeedFetcher().newIndependentFetcher();
        fetcher.fetch(sub, this);
    }

    @NonNull
    private void setSubscriptions() {
        mSubscriptions = new JsonSubs<>(new IndependentApi());
        for(IndependentApi.Subscriptions sub : IndependentApi.Subscriptions.values())
            mSubscriptions.addSubscription(sub.getName(), sub.getPath());
    }

    @Override
    public void onItemClick(Article datum) {
        Intent intent = new Intent(getActivity(), ActivityItemView.class);
        Parcelable data = Parcels.wrap(datum);
        intent.putExtra(ARTICLE, data);
        startActivity(intent);
    }
}

