package com.como.comolake.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.como.comolake.R;
import com.como.comolake.dto.FragmentStack;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseFragmentActivity extends FragmentActivity {

    Timer mTimer = new Timer();
    Handler mHandler = new Handler();

    protected Stack<FragmentStack> fragmentStacks = new Stack<>();

    @Override
    protected  void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        View decorView = getWindow().getDecorView();
                        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    }
                });
            }
        }, 0, 40);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (fragmentStacks.size() > 1) {
            backView();
        } else {
            Intent intent = new Intent(BaseFragmentActivity.this, HomeActivity.class);
            startActivity(intent);

            finish();
        }
    }

    protected void backToRootView() {
        while (!fragmentStacks.isEmpty()) {
            backView();
        }
    }

    public void openView(final Fragment fragment, final FragmentStack fragmentStack) {
        openView(fragment, fragmentStack, false);
    }

    protected void openView(final Fragment fragment, final FragmentStack fragmentStack, final boolean openAsRoot) {
        if (openAsRoot) {
            backToRootView();
        }

        //hide calendar
        if (!fragmentStacks.isEmpty()) {
            Fragment curFragment = getSupportFragmentManager().findFragmentByTag(fragmentStacks.peek().getId());
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .hide(curFragment)
                        .commit();
        }

        String id = System.currentTimeMillis() + "";
        fragmentStack.setId(id);
        fragmentStacks.push(fragmentStack);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .add(R.id.content, fragment, id)
                .commit();
    }

    protected void backView() {
        FragmentStack fragmentStack = fragmentStacks.pop();

        String id = fragmentStack.getId();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(id);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .remove(fragment)
                .commit();

        Fragment curFragment = getSupportFragmentManager().findFragmentByTag(fragmentStacks.peek().getId());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .show(curFragment)
                .commit();
    }
}
