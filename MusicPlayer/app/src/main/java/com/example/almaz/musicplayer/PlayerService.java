package com.example.almaz.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Almaz on 11.11.2018.
 */

public class PlayerService extends Service {
    ArrayList<Track> tracks;
    int currentSong;
    SongCallback callback;
    MediaPlayer mediaPlayer;
    MBinder binder = new MBinder();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void init(SongCallback callback, ArrayList<Track> tracks, int currentSong) {
        this.tracks = tracks;
        this.callback = callback;
        this.currentSong = currentSong;
        start();
    }

    public void start() {
        mediaPlayer = MediaPlayer.create(this, tracks.get(currentSong).getId());
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next();
            }
        });
    }

    public void next() {
        currentSong++;
        if (currentSong == tracks.size()) {
            currentSong = 0;
        } else {
            currentSong = currentSong;
        }
        callback.songClick(currentSong);
        mediaPlayer.release();
        start();
    }

    public void previous() {
        currentSong--;
        if (currentSong == -1) {
            currentSong = tracks.size() - 1;
        } else {
            currentSong = currentSong;
        }
        callback.songClick(currentSong);
        mediaPlayer.release();
        start();
    }

    public class MBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }
}
