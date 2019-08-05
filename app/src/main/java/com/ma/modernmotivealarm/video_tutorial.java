package com.ma.modernmotivealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class video_tutorial extends YouTubeBaseActivity  implements
        YouTubePlayer.OnInitializedListener{


YouTubePlayerView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorial);
        vv=findViewById(R.id.vvvv);




                vv.initialize("AIzaSyCsQzA3g_9EvVOuTfnCP9v0NH6oXYXVLmA",video_tutorial.this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.loadVideo("df-6eROdL_4");
        youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        startActivity(new Intent(video_tutorial.this, MainActivity.class));
    }
}
