package com.como.comolake.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.como.comolake.R;
import com.como.comolake.util.Constants;
import com.como.comolake.util.Variables;

import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;


public class IntroActivity extends FullScreenActivity implements OnPreparedListener, SurfaceHolder.Callback, OnVideoSizeChangedListener {

    private int mVideoWidth;
    private int mVideoHeight;
    private boolean mIsVideoSizeKnown = false;
    private boolean mIsVideoReadyToBePlayed = false;

    private MediaPlayer mVideoPlayer;
    private MediaPlayer mAudioPlayer;

    private SurfaceView mPreview;
    private SurfaceHolder holder;

    private Timer mTimer = null;
    private Handler mHandler = new Handler();

    private int nTimerCounter = 0;

    private int nMaxLayoutWidth = 0;
    private boolean bIsInitialLayoutChange = true;

    private String strPassword = "";

    AlertDialog alertDialog;

    private void manageAssetFolderToSDcard()
    {
        String offlineMapBasePath = Environment.getExternalStorageDirectory() + "/osmdroid/tiles/MapquestOSM";

        try
        {
            String strAssetPath = "MapquestOSM";
            String strSdCardPath = offlineMapBasePath;
            File dst = new File(offlineMapBasePath);
            if (!dst.exists())
            {
                copyDirOrFileFromAssetManager(strAssetPath, strSdCardPath);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void copyDirOrFileFromAssetManager(String strAssetPath, String strSdCardPath) throws IOException
    {
        File dstFile = new File(strSdCardPath);
        dstFile.mkdirs();

        String[] strSrcFiles = getAssets().list(strAssetPath);

        for (int i = 0; i < strSrcFiles.length; i++)
        {
            String strSubAssetPath = strAssetPath + "/" + strSrcFiles[i];
            String strSubSrcFiles[] = getAssets().list(strSubAssetPath);

            if (strSubSrcFiles.length == 0)
            {
                String strDstFilePath = strSdCardPath + "/" + strSrcFiles[i];
                copyAssetFile(strSubAssetPath, strDstFilePath);
            }
            else {
                copyDirOrFileFromAssetManager(strSubAssetPath, strSdCardPath + "/" + strSrcFiles[i]);
            }
        }
    }

    public void copyAssetFile(String strAssetPath, String strSdCardPath) throws IOException
    {
        InputStream in = getApplicationContext().getAssets().open(strAssetPath);
        OutputStream out = new FileOutputStream(strSdCardPath);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        in.close();
        out.close();
    }

    private class ManageAssetFolders extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... arg0)
        {
            manageAssetFolderToSDcard();
            return null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if (isPasswordPassed() == false) {

            LayoutInflater li = LayoutInflater.from(this);
            View promptView = li.inflate(R.layout.password_dialog, null);

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(promptView);

            final EditText password = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);

            builder .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            strPassword = password.getText().toString();

                            if (!strPassword.equals("abcdef")) {
                                finish();
                            }
                            else {
                                setPasswordPassed();
                            }
                        }
                    });

            alertDialog = builder.create();

            alertDialog.show();
        }

        new ManageAssetFolders().execute();

        for (int i = 0; i < 5; i++) {
            findViewById(Constants.INTRO_LANGUAGE_SELECTORS[i]).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (bIsInitialLayoutChange == true) {
                        if (nMaxLayoutWidth < right - left) {
                            nMaxLayoutWidth = right - left;
                        }
                    }
                }
            });
        }

        // Hide language selector.
        findViewById(R.id.layout_flags).setVisibility(View.INVISIBLE);

        // Add click listener to navigate
        findViewById(R.id.txt_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        });

        // Add click listener to show language selector.
        findViewById(R.id.txt_langsel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bIsInitialLayoutChange = false;

                for (int i = 0; i < 5; i++) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById(Constants.INTRO_LANGUAGE_SELECTORS[i]).getLayoutParams();
                    layoutParams.width = nMaxLayoutWidth;
                    findViewById(Constants.INTRO_LANGUAGE_SELECTORS[i]).setLayoutParams(layoutParams);
                }

                if (findViewById(R.id.layout_flags).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.layout_flags).setVisibility(View.INVISIBLE);
                }
                else if (findViewById(R.id.layout_flags).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.layout_flags).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.txt_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add language selector events.
        for (int i = 0; i < 5; i++) {
            final int index = i;
            findViewById(Constants.INTRO_LANGUAGE_SELECTORS[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Variables.nLanguageIndex = index;

                    findViewById(R.id.img_flag).setBackgroundResource(Constants.INTRO_FLAGS[index]);
                    findViewById(R.id.layout_flags).setVisibility(View.INVISIBLE);

                    ((TextView) findViewById(R.id.txt_exit)).setText(Constants.INTRO_EXIT[index]);
                    ((TextView) findViewById(R.id.txt_langsel)).setText(Constants.INTRO_LANGSEL[index]);
                    ((TextView) findViewById(R.id.txt_exit)).setText(Constants.INTRO_GO[index]);

                    releaseMediaPlayer();
                    playVideo();
                    playAudio(index);
                }
            });
        }

        findViewById(R.id.surfaceView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nTimerCounter = 0;
                findViewById(R.id.layout_logo).setVisibility(View.VISIBLE);
            }
        });

        mPreview = (SurfaceView) findViewById(R.id.surfaceView);

        holder = mPreview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }

        mTimer.scheduleAtFixedRate(new CounterTask(), 0, 1000);
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        if (width == 0 || height == 0) {  return;       }
        mIsVideoSizeKnown = true;
        mVideoWidth = width;
        mVideoHeight = height;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void onPrepared(MediaPlayer mediaplayer) {
        mIsVideoReadyToBePlayed = true;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        playVideo();
        playAudio(0);
    }

    private void playVideo() {
        doCleanUp();
        mVideoPlayer = MediaPlayer.create(this, R.raw.intro);
        mVideoPlayer.setDisplay(holder);
        mVideoPlayer.setOnVideoSizeChangedListener(this);
        mVideoPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mVideoPlayer.start();
    }

    private void playAudio(int index) {
        mAudioPlayer = MediaPlayer.create(this, Constants.INTRO_AUDIOS[index]);

        try {
            mAudioPlayer.prepare();
        } catch (Exception e) { }

        mAudioPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
    }

    private void releaseMediaPlayer() {
        if (mVideoPlayer != null) {
            mVideoPlayer.release();
            mVideoPlayer = null;
        }

        if (mAudioPlayer != null) {
            mAudioPlayer.release();
            mAudioPlayer = null;
        }
    }

    private void doCleanUp() {
        mIsVideoReadyToBePlayed = false;
        mIsVideoSizeKnown = false;
    }

    private void startVideoPlayback() {
        mVideoPlayer.start();
    }

    public boolean isPasswordPassed() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isPasswordPassed", false);
    }

    public void setPasswordPassed() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("isPasswordPassed", true);
        editor.apply();
    }

    private void fadeOutLogo(final View view) {

        int fadeOutDuration = 1000;

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(fadeOutDuration);

        view.startAnimation(fadeOut);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) { }
            public void onAnimationStart(Animation animation) { }
        });
    }

    class CounterTask extends TimerTask {
        @Override
        public void run(){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    nTimerCounter++;
                    if (nTimerCounter == 5) {
                        fadeOutLogo(findViewById(R.id.layout_logo));
                    }
                }
            });
        }
    }
}
