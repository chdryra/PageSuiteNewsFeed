package com.chdryra.android.pagesuitenewsfeed;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chdryra.android.model.Article;
import com.chdryra.android.model.ArticleHeadlines;
import com.chdryra.android.model.Image;
import com.chdryra.android.utils.BitmapFetcher;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

class ArticlesAdapter extends android.support.v7.widget.RecyclerView.Adapter {
    private final List<Article> mArticles;

    public ArticlesAdapter(List<Article> articles) {
        mArticles = articles;
    }

    public void setArticles(List<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_article, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder vh = (ItemViewHolder) holder;
        vh.setData(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder implements BitmapFetcher.BitmapFetcherCallback{
        private TextView mHeadline;
        private TextView mAbstract;
        private ImageView mImage;

        private ItemViewHolder(View v) {
            super(v);
            mHeadline = (TextView) v.findViewById(R.id.item_headline);
            mAbstract = (TextView) v.findViewById(R.id.item_abstract);
            mImage = (ImageView) v.findViewById(R.id.item_thumbnail);
        }

        private void setData(Article article) {
            ArticleHeadlines headlines = article.getHeadlines();
            mHeadline.setText(headlines.getShortHeadline());
            mAbstract.setText(headlines.getSubHeadline());
            setImage(article);

        }

        private void setImage(Article article) {
            Image image = article.getMedia().getImageOverride();
            if(image == null) image = article.getMedia().getImage();
            BitmapFetcher fetcher = new BitmapFetcher(this);
            fetcher.load(image.getThumbnail());
        }

        @Override
        public void onBitmapFetched(Bitmap bitmap) {
            mImage.setImageBitmap(bitmap);
        }
    }
}
