package com.fajarazay.github.bolaapp.data;

import android.support.annotation.Nullable;

import com.fajarazay.github.bolaapp.data.local.TeamLocalDataSource;
import com.fajarazay.github.bolaapp.data.remote.TeamRemoteDataSource;
import com.fajarazay.github.bolaapp.model.TeamResponse;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class TeamRepository implements TeamDataSource {
    private TeamRemoteDataSource teamRemoteDataSource;
    private TeamLocalDataSource teamLocalDataSource;

    public TeamRepository(TeamRemoteDataSource teamRemoteDataSource, TeamLocalDataSource teamLocalDataSource) {
        this.teamRemoteDataSource = teamRemoteDataSource;
        this.teamLocalDataSource = teamLocalDataSource;
    }

    private void getTeamsFromRemote(@Nullable final GetTeamsCallback callback) {
        teamRemoteDataSource.getListTeams(new GetTeamsCallback() {
            @Override
            public void onTeamLoaded(TeamResponse data) {
                //insert to Database
                teamLocalDataSource.saveDataTeam(data.getTeams());
                callback.onTeamLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        teamLocalDataSource.getListTeams(new GetTeamsCallback() {
            @Override
            public void onTeamLoaded(TeamResponse data) {
                callback.onTeamLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                getTeamsFromRemote(callback);
            }
        });
    }
}
