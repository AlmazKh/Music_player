package com.example.almaz.musicplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lv_songs);

        ArrayList<File> songs = findSongs(Environment.getExternalStorageDirectory());
    }

    private ArrayList<File> findSongs(File root) {
        ArrayList<File> list = new ArrayList<>();
        File[] file = root.listFiles();

    }
}
