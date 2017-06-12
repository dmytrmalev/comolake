package com.como.comolake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.como.comolake.R;
import com.como.comolake.dto.FragmentStack;
import com.como.comolake.fragment.CategoryFragment;
import com.como.comolake.fragment.ListFragment;
import com.como.comolake.util.Constants;
import com.como.comolake.util.Variables;

public class MainActivity extends BaseFragmentActivity implements CategoryFragment.OnShowVideoListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Variables.nFragmentIndex == Constants.VIDEO_CATEGORY) {
            openView(CategoryFragment.newInstance(), new FragmentStack());
        }
        else if (Variables.nFragmentIndex == Constants.VIDEO_LIST) {
            openView(ListFragment.newInstance(), new FragmentStack());
        }

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.img_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    public void onShowVideoList() {
        openView(ListFragment.newInstance(), new FragmentStack());
    }
}
