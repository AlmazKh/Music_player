package com.example.almaz.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongCallback {

    TrackAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.rv_tracks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        appAdapter = new TrackAdapter(new AppListDiffCallback(), this);
        mRecyclerView.setAdapter(appAdapter);
        appAdapter.submitList(getPlaylist());
    }

    public static ArrayList<Track> getPlaylist() {
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(R.raw.track_1, "Track 1", "Singer"));
        tracks.add(new Track(R.raw.track_2, "Track 2", "Singer"));
        tracks.add(new Track(R.raw.track_3, "Track 3", "Singer"));
        tracks.add(new Track(R.raw.track_4, "Track 4", "Singer"));
        tracks.add(new Track(R.raw.track_5, "Track 5", "Singer"));
        return tracks;
    }

    @Override
    public void songClick(int id) {
        Intent intent = new Intent(this, Player.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
