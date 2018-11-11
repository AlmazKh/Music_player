package com.example.almaz.musicplayer;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Almaz on 11.11.2018.
 */

public class TrackAdapter extends ListAdapter<Track, TrackAdapter.TrackHolder> {

    SongCallback songCallback;

    protected TrackAdapter(@NonNull DiffUtil.ItemCallback<Track> diffCallback, SongCallback songCallback) {
        super(diffCallback);
        this.songCallback = songCallback;
    }


    @NonNull
    @Override
    public TrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track, parent, false);
        return new TrackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackHolder viewHolder, int position) {
        viewHolder.id = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songCallback.songClick(viewHolder.id);
            }
        });
        viewHolder.tvTrackName.setText(getItem(position).getTrackName());
        viewHolder.tvSingerName.setText(getItem(position).getSingerName());
    }

    public class TrackHolder extends RecyclerView.ViewHolder {
        TextView tvTrackName;
        TextView tvSingerName;
        int id;

        public TrackHolder(View itemView) {
            super(itemView);
            tvTrackName = itemView.findViewById(R.id.tv_name);
            tvSingerName = itemView.findViewById(R.id.tv_singer);
        }
    }
}
