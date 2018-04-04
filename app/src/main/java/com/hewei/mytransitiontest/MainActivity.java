package com.hewei.mytransitiontest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView mImageView = findViewById(R.id.image);
        ViewCompat.setTransitionName(mImageView, Constants.TRANSITION_NAME);

        Picasso picasso = new Picasso.Builder(getApplicationContext()).loggingEnabled(true).build();
        Picasso.setSingletonInstance(picasso);

        Picasso.with(this).load(Uri.parse(Constants.IMAGE_URL)).into(mImageView);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startViewPager(mImageView);
            }
        });
    }

    private void startSingleImage(View shareView) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity.this, shareView, Constants.TRANSITION_NAME);

        Intent intent = new Intent(MainActivity.this, SingleImageViewerActivity.class);
        MainActivity.this.startActivity(intent, optionsCompat.toBundle());
    }

    private void startViewPager(View shareView) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity.this, shareView, Constants.TRANSITION_NAME);

        Intent intent = new Intent(MainActivity.this, MultiImageViewerActivity.class);
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add(Constants.IMAGE_URL);
        intent.putStringArrayListExtra(MultiImageViewerActivity.EXTRA_IMAGE_LIST, imageList);

        MainActivity.this.startActivity(intent, optionsCompat.toBundle());
    }
}
