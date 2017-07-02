package com.chdryra.android.pagesuitenewsfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chdryra.android.model.Article;
import com.chdryra.android.model.ArticleHeadlines;
import com.chdryra.android.model.Image;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

class ArticlesAdapter extends android.support.v7.widget.RecyclerView.Adapter {
    private static final int ITEM_LAYOUT = R.layout.list_item_article;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(ITEM_LAYOUT, parent, false);

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

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeadline;
        private TextView mAbstract;
        private ImageView mImage;
        private TextView mSection;
        private TextView mDate;
        private TextView mAuthor;

        private ItemViewHolder(View v) {
            super(v);
            mHeadline = (TextView) v.findViewById(R.id.item_headline);
            mAbstract = (TextView) v.findViewById(R.id.item_abstract);
            mImage = (ImageView) v.findViewById(R.id.item_thumbnail);
            mSection = (TextView) v.findViewById(R.id.item_section);
            mDate = (TextView) v.findViewById(R.id.item_date);
            mAuthor = (TextView) v.findViewById(R.id.item_author);
        }

        private void setData(Article article) {
            ArticleHeadlines headlines = article.getHeadlines();
            mHeadline.setText(headlines.getShortHeadline());
            mAbstract.setText(headlines.getSubHeadline());
            mSection.setText(article.getSection().getSectionName());
            DateFormat df = SimpleDateFormat.getDateInstance();
            mDate.setText(df.format(article.getDate().getUpdatedDate()));
            mAuthor.setText(article.getAuthors().getPrimaryAuthor().getName());
            setImage(article);
        }

        private void setImage(Article article) {
            Image image = article.getMedia().getImageOverride();
            if(image == null) image = article.getMedia().getImage();
            Glide.with(mImage.getContext())
                    .load(image.getThumbnail()) //need to look at updating this
                    .fitCenter()
                    .crossFade()
                    .into(mImage);
        }
    }
}
