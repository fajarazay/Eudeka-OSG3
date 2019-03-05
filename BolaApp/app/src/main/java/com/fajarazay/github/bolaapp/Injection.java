package com.fajarazay.github.bolaapp;

import android.content.Context;

import com.fajarazay.github.bolaapp.data.TeamRepository;
import com.fajarazay.github.bolaapp.data.local.TeamLocalDataSource;
import com.fajarazay.github.bolaapp.data.remote.TeamRemoteDataSource;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class Injection {
    public static TeamRepository provideTeamRepository(Context context) {
        return new TeamRepository(new TeamRemoteDataSource(),
                new TeamLocalDataSource(context));
    }
}
