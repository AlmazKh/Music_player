package com.example.almaz.musicplayer;

import android.support.v7.util.DiffUtil;

/**
 * Created by Almaz on 11.11.2018.
 */

public class AppListDiffCallback extends DiffUtil.ItemCallback<Track> {

    @Override
    public boolean areItemsTheSame(Track oldItem, Track newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(Track oldItem, Track newItem) {
        return oldItem.getTrackName().equals(newItem.getTrackName());
    }

}
