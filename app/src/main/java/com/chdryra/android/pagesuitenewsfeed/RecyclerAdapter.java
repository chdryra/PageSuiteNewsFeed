package com.chdryra.android.pagesuitenewsfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

class RecyclerAdapter<T> extends android.support.v7.widget.RecyclerView.Adapter {
    private final FactoryViewHolder<T> mFactory;
    private final List<T> mData;

    public RecyclerAdapter(FactoryViewHolder<T> factory, List<T> data) {
        mFactory = factory;
        mData = data;
    }

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(mFactory.getViewHolderLayout(), parent, false);

        return mFactory.newViewHolder(v);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderBasic<T> vh = (ViewHolderBasic<T>) holder; //we know the type here.
        vh.updateData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
