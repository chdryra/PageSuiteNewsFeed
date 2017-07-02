package com.chdryra.android.pagesuitenewsfeed;

import android.support.annotation.Nullable;
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
    private final OnItemClickListener<T> mClickListener;
    private RecyclerView mRecyclerView;

    public interface OnItemClickListener<T> {
        void onItemClick(T datum);
    }

    public RecyclerAdapter(FactoryViewHolder<T> factory,
                           List<T> data,
                           @Nullable OnItemClickListener<T> clickListener) {
        mFactory = factory;
        mData = data;
        mClickListener = clickListener;
    }

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(mFactory.getViewHolderLayout(), parent, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v);
            }
        });

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

    private void onItemClick(View v) {
        if (mClickListener == null) return;
        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
        mClickListener.onItemClick(mData.get(itemPosition));
    }
}
