package com.fajarazay.github.samplevideoexoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    private PlayerView playerView;
    private BandwidthMeter bandwidthMeter;
    private DefaultTrackSelector trackSelector=null;
    private DataSource.Factory mediaDataSourceFactory;
    private SimpleExoPlayer player = null;
    private Boolean shouldAutoPlay = true;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView = findViewById(R.id.playerView);
        progressBar = findViewById(R.id.loading);
        initPlayer();


    }

    private void initPlayer() {
        playerView.requestFocus();
        bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        playerView.setPlayer(player);
        player.setPlayWhenReady(shouldAutoPlay);
        mediaDataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "exoPlayerSample"),
                (TransferListener<? super DataSource>) bandwidthMeter);

        Uri uri= Uri.parse("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        MediaSource mediaSource = new HlsMediaSource.Factory(mediaDataSourceFactory)
                .setAllowChunklessPreparation(true)
                .createMediaSource(uri);
        player.prepare(mediaSource);
        player.addListener(new Player.DefaultEventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
                super.onTimelineChanged(timeline, manifest, reason);
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {
                    case Player.STATE_READY:
                        progressBar.setVisibility(View.GONE);
                        break;

                    case Player.STATE_BUFFERING:
                        progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
