package com.example.almaz.musicplayer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Player extends AppCompatActivity {

    Button btnPrevious;
    Button btnPlay;
    Button btnNext;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView tvCurrentTime;
    TextView tvRemainingTime;
    TextView tvTrackName;
    MediaPlayer mediaPlayer;
    int totalTime;
//    final String DATA_SD = Environment
//            .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
//            + "/music.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        btnPrevious = (Button)findViewById(R.id.btn_previous);
        btnPlay = (Button)findViewById(R.id.btn_play);
        btnNext = (Button)findViewById(R.id.btn_next);
        tvCurrentTime = (TextView)findViewById(R.id.tv_current_time);
        tvRemainingTime = (TextView)findViewById(R.id.tv_remaining_time);
        tvTrackName = (TextView)findViewById(R.id.tv_track_name);
        mediaPlayer = MediaPlayer.create(this, R.raw.track_5);
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);
        totalTime = mediaPlayer.getDuration();
        positionBar = (SeekBar)findViewById(R.id.seekBar_position);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser) {
                        if(formUser) {
                            mediaPlayer.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        volumeBar = (SeekBar)findViewById(R.id.seekBar_volume);
        volumeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser) {
                        float volumePower = progress / 100f;
                        mediaPlayer.setVolume(volumePower, volumePower);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer !=null) {
                    try {
                        Message message = new Message();
                        message.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            positionBar.setProgress(currentPosition);
            String currentTime = createTimeLable(currentPosition);
            tvCurrentTime.setText(currentTime);
            String remainingTime = createTimeLable(totalTime - currentPosition);
            tvRemainingTime.setText(remainingTime);
        }
    };

    private String createTimeLable(int time) {
        String timeLable = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLable = min + ";";
        if(sec < 10) {
            timeLable += "0";
        } else {
            timeLable += sec;
        }
        return timeLable;
    }

    public void OnClick(View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.btn_play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    btnPlay.setBackgroundResource(R.drawable.pause);
                } else {
                    mediaPlayer.pause();
                    btnPlay.setBackgroundResource(R.drawable.play);
                }
                break;
            case R.id.btn_previous:

            case R.id.btn_next:

        }
    }
}
