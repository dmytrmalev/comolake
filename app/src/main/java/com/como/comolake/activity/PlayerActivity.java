package com.como.comolake.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;
import com.como.comolake.R;
import com.como.comolake.util.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends FullScreenActivity implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback, MediaPlayer.OnVideoSizeChangedListener{

    MediaPlayer mVideoPlayer;
    MediaPlayer mAudioPlayer;

    int mVideoWidth;
    int mVideoHeight;

    boolean mIsVideoSizeKnown = false;
    boolean mIsVideoReadyToBePlayed = false;

    SurfaceView mPreview;
    SurfaceHolder holder;

    Timer mTimer = null;
    Handler mHandler = new Handler();

    int nCircleWidth = 0;
    int nCircleHeight = 0;

    int nSeekbarWidth = 0;
    int nPlaybarHeight = 0;

    boolean isPlaying = false;

    RelativeLayout.LayoutParams originLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        originLayoutParams = (RelativeLayout.LayoutParams) findViewById(R.id.surface_video).getLayoutParams();

        mPreview = (SurfaceView) findViewById(R.id.surface_video);

        holder = mPreview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        findViewById(R.id.img_circle).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (nCircleWidth == 0)
                {
                    nCircleWidth = right - left;
                }
                if (nCircleHeight == 0)
                {
                    nCircleHeight = bottom - top;
                }
            }
        });

        findViewById(R.id.img_seekbar).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (nSeekbarWidth == 0)
                {
                    nSeekbarWidth = right - left;
                }
            }
        });

        findViewById(R.id.layout_playbar).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                nPlaybarHeight = bottom - top;
            }
        });

        findViewById(R.id.img_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying == true) {
                    isPlaying = false;
                    ((ImageView) findViewById(R.id.img_play)).setImageResource(R.drawable.play_btn_play);

                    mVideoPlayer.pause();
                    mAudioPlayer.pause();
                }
                else
                {
                    isPlaying = true;
                    ((ImageView) findViewById(R.id.img_play)).setImageResource(R.drawable.play_btn_pause);

                    mVideoPlayer.start();
                    mAudioPlayer.start();
                }
            }
        });

        findViewById(R.id.img_fullscreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                findViewById(R.id.surface_video).setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            }
        });

        findViewById(R.id.surface_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.surface_video).setLayoutParams(originLayoutParams);
            }
        });

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.img_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerActivity.this, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        });

        findViewById(R.id.layout_playbar).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    float nVideoPosition = mVideoPlayer.getDuration() * (event.getX() - nCircleWidth / 2) / (nSeekbarWidth - 10);
                    mVideoPlayer.seekTo((int) nVideoPosition);
                    mAudioPlayer.seekTo((int) nVideoPosition);

                    setPlayPosition((int) nVideoPosition);
                }
                return true;
            }
        });

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (isPlaying == false || mVideoPlayer == null)
                            return;

                        setPlayPosition(mVideoPlayer.getCurrentPosition());

                        int nPlayTime = mVideoPlayer.getCurrentPosition() / 1000;
                        int nSecond = nPlayTime % 60;
                        int nMinute = nPlayTime / 60;

                        String strPlayTime = String.format("%d%d:%d%d", nMinute / 10, nMinute % 10, nSecond / 10, nSecond % 10);
                        ((TextView) findViewById(R.id.txt_playtime)).setText(strPlayTime);
                    }
                });
            }
        }, 0, 40);
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

        mVideoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlaying = false;
                ((ImageView) findViewById(R.id.img_play)).setImageResource(R.drawable.play_btn_play);

                setPlayPosition(0);
            }
        });
    }

    private void playAudio(int index) {
        mAudioPlayer = MediaPlayer.create(this, Constants.INTRO_AUDIOS[index]);
        try {
            mAudioPlayer.prepare();
        } catch (Exception e) { }

        mAudioPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();

        isPlaying = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        isPlaying = true;
    }

    @Override
    public void onDestroy() {
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

    public void setPlayPosition(int nPosition) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(nCircleWidth, nCircleHeight);
        layoutParams.setMargins((nSeekbarWidth - nCircleWidth) * nPosition / mVideoPlayer.getDuration(), (nPlaybarHeight - nCircleHeight) / 2, -nCircleWidth, -nCircleHeight);
        findViewById(R.id.img_circle).setLayoutParams(layoutParams);
    }
}
