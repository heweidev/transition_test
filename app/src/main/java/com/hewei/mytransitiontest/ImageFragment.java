package com.hewei.mytransitiontest;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by fengyinpeng on 2018/4/4.
 */

public class ImageFragment extends Fragment {
    private static final String EXTRA_URL = "EXTRA_URL";

    private String mUrl;

    public static ImageFragment newInstance(@NonNull String url) {
        Bundle args = new Bundle();
        args.putString(EXTRA_URL, url);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args;
        if (savedInstanceState != null) {
            args = savedInstanceState;
        } else {
            args = getArguments();
        }
        if (args != null) {
            mUrl = args.getString(EXTRA_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_image_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.largeImage);
        ViewCompat.setTransitionName(imageView, Constants.TRANSITION_NAME);
        Picasso.with(getContext()).load(Uri.parse(mUrl)).noFade().into(imageView);
    }
}
