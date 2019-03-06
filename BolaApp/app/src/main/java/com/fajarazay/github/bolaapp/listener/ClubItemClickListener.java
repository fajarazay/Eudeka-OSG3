package com.fajarazay.github.bolaapp.listener;

import android.widget.ImageView;

import com.fajarazay.github.bolaapp.model.TeamDetail;

/**
 * Created by Fajar Septian on 2019-03-06.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public interface ClubItemClickListener {
    void onClubItemClick(TeamDetail clubItem, ImageView shareImageView);
}
