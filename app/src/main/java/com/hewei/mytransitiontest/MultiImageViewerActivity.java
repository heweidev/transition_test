package com.hewei.mytransitiontest;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MultiImageViewerActivity extends AppCompatActivity {
    private List<String> mImageList;

    public static final String EXTRA_IMAGE_LIST = "EXTRA_IMAGE_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer2);

        mImageList = getIntent().getStringArrayListExtra(EXTRA_IMAGE_LIST);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // 不延时的话进入动画展现不完整
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();

            new Handler().postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    startPostponedEnterTransition();
                }
            }, 200);
        }
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(mImageList.get(position));
        }

        @Override
        public int getCount() {
            return mImageList != null ? mImageList.size() : 0;
        }
    }
}
