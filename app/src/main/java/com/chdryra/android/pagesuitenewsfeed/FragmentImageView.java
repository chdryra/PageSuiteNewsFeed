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
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chdryra.android.model.Image;

import org.parceler.Parcels;

/**
 * Created by: Rizwan Choudrey
 * On: 23/01/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FragmentImageView extends Fragment {
    private static final int LAYOUT = R.layout.fragment_image_view;

    private ImageView mImage;
    private TextView mCaption;

    public static FragmentImageView newInstance() {
        return new FragmentImageView();
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

        mImage = (ImageView) v.findViewById(R.id.image);
        mCaption = (TextView) v.findViewById(R.id.caption);

        getActivity().setTitle("Image");

        displayImage();

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayImage() {
        Parcelable data = getActivity().getIntent().getParcelableExtra(FragmentItemView.IMAGE);
        Image image = Parcels.unwrap(data);

        Glide.with(mImage.getContext())
                .load(image.getUrl()) //need to look at updating this
                .fitCenter()
                .crossFade()
                .into(mImage);

        String caption = image.getCaption();
        String copyright = image.getCopyright();
        if (copyright.length() > 1) {
            caption += " (Copyright: " + copyright + ")";
        }

        mCaption.setText(caption);
    }
}

