package com.kelasbalik.kelasbalikmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoviewActivity extends AppCompatActivity {

    String videoUrl;
    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    private boolean leaveCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_videoview);

        videoUrl = getIntent().getStringExtra("url_pdf");

        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();

        playerView = findViewById(R.id.vvVideo);
        playerView.setPlayer(simpleExoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        simpleExoPlayer.stop();
        simpleExoPlayer.seekTo(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Home button pressed
        if(!leaveCalled) {
            simpleExoPlayer.pause();
        }

        leaveCalled = false;
    }

}