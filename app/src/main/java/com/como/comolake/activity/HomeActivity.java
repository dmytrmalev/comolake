package com.como.comolake.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.como.comolake.R;
import com.como.comolake.util.Constants;
import com.como.comolake.util.Variables;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends FullScreenActivity {

    public int nImageIndex = 0;

    Timer timer;
    TimerTask task;
    View slideView;

    ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {
                AnimateandSlideShow();
            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 8000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                mHandler.post(mUpdateResults);
            }

        }, delay, period);

        mViewFlipper = ((ViewFlipper) findViewById(R.id.viewFlipper));

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.layout_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.nFragmentIndex = Constants.VIDEO_CATEGORY;
                Variables.nVideoIndex = 0;
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.layout_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ((TextView) findViewById(R.id.txt_map)).setText(Constants.HOME_MAP[Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_video)).setText(Constants.HOME_VIDEO[Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_header_normal)).setText(Constants.HOME_DESCRIPTION_HEADER_NORMAL[Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_header_bold)).setText(Constants.HOME_DESCRIPTION_HEADER_BOLD[Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_content)).setText(Constants.HOME_DESCRIPTION_CONTENT[Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_footer)).setText(Constants.HOME_DESCRIPTION_FOOTER[Variables.nLanguageIndex]);
    }

    private void AnimateandSlideShow() {

        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.left_in));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));
        mViewFlipper.showNext();

    }
}
