package com.como.comolake.fragment;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.como.comolake.R;
import com.como.comolake.util.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerFragment extends Fragment  implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback, MediaPlayer.OnVideoSizeChangedListener {

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

    View convertView;

    int nCircleWidth = 0;
    int nCircleHeight = 0;

    int nSeekbarWidth = 0;
    int nPlaybarHeight = 0;

    boolean isPlaying = true;

    public PlayerFragment() {
        // Required empty public constructor
    }

    public static PlayerFragment newInstance() {
        PlayerFragment fragment = new PlayerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View convertView = inflater.inflate(R.layout.fragment_player, container, false);
        mPreview = (SurfaceView) convertView.findViewById(R.id.surface_video);

        holder = mPreview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // Inflate the layout for this fragment

        this.convertView = convertView;

        convertView.findViewById(R.id.img_circle).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
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

        convertView.findViewById(R.id.img_seekbar).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (nSeekbarWidth == 0)
                {
                    nSeekbarWidth = right - left;
                }
            }
        });

        convertView.findViewById(R.id.layout_playbar).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                nPlaybarHeight = bottom - top;
            }
        });

        convertView.findViewById(R.id.img_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying == true) {
                    isPlaying = false;
                    ((ImageView) convertView.findViewById(R.id.img_play)).setImageResource(R.drawable.play_btn_play);

                    mVideoPlayer.pause();
                    mAudioPlayer.pause();
                }
                else
                {
                    isPlaying = true;
                    ((ImageView) convertView.findViewById(R.id.img_play)).setImageResource(R.drawable.play_btn_pause);

                    mVideoPlayer.start();
                    mAudioPlayer.start();
                }
            }
        });

        convertView.findViewById(R.id.img_fullscreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                convertView.findViewById(R.id.surface_video).setLayoutParams(new RelativeLayout.LayoutParams(size.x, size.y));

                //convertView.findViewById(R.id.surface_video).setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//                convertView.findViewById(R.id.surface_video).setTop(0);
//                convertView.findViewById(R.id.surface_video).setLeft(0);
//                convertView.findViewById(R.id.surface_video).setRight(size.x);
//                convertView.findViewById(R.id.surface_video).setBottom(size.y);
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

                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(nCircleWidth, nCircleHeight);
                        float f = nSeekbarWidth * mVideoPlayer.getCurrentPosition() / mVideoPlayer.getDuration();
                        layoutParams.setMargins(nSeekbarWidth * mVideoPlayer.getCurrentPosition() / mVideoPlayer.getDuration(), (nPlaybarHeight - nCircleHeight) / 2, -nCircleWidth, -nCircleHeight);
                        convertView.findViewById(R.id.img_circle).setLayoutParams(layoutParams);

                        int nPlayTime = mVideoPlayer.getCurrentPosition() / 1000;
                        int nSecond = nPlayTime / 60;
                        int nMinute = nPlayTime % 60;

                        String strPlayTime = String.format("%d%d:%d%d", nMinute / 10, nMinute % 10, nSecond / 10, nSecond % 10);
                        ((TextView) convertView.findViewById(R.id.txt_playtime)).setText(strPlayTime);
                    }
                });
            }
        }, 0, 40);

        return convertView;
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
        //mVideoPlayer = MediaPlayer.create(getActivity(), R.raw.intro);
        mVideoPlayer.setDisplay(holder);
        mVideoPlayer.setOnVideoSizeChangedListener(this);
        mVideoPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mVideoPlayer.start();
    }

    private void playAudio(int index) {
        mAudioPlayer = MediaPlayer.create(getActivity(), Constants.INTRO_AUDIOS[index]);
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
}
