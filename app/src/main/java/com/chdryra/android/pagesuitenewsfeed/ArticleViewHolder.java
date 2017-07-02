package com.chdryra.android.pagesuitenewsfeed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chdryra.android.model.Article;
import com.chdryra.android.model.ArticleHeadlines;
import com.chdryra.android.model.Image;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */
public class ArticleViewHolder extends ViewHolderBasic<Article> {
    private static final int LAYOUT = R.layout.list_item_article;

    private TextView mHeadline;
    private TextView mAbstract;
    private ImageView mImage;
    private TextView mSection;
    private TextView mDate;
    private TextView mAuthor;

    ArticleViewHolder(View v) {
        super(v);
        mHeadline = (TextView) v.findViewById(R.id.item_headline);
        mAbstract = (TextView) v.findViewById(R.id.item_abstract);
        mImage = (ImageView) v.findViewById(R.id.item_thumbnail);
        mSection = (TextView) v.findViewById(R.id.item_section);
        mDate = (TextView) v.findViewById(R.id.item_date);
        mAuthor = (TextView) v.findViewById(R.id.item_author);
    }

    public static int getLayout() {
        return LAYOUT;
    }

    @Override
    public void updateData(Article article) {
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
        if (image == null) image = article.getMedia().getImage();
        Glide.with(mImage.getContext())
                .load(image.getThumbnail()) //need to look at updating this
                .fitCenter()
                .crossFade()
                .into(mImage);
    }
}
