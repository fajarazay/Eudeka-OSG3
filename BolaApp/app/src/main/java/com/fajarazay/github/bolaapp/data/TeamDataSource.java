package com.fajarazay.github.bolaapp.data;

import com.fajarazay.github.bolaapp.model.TeamResponse;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public interface TeamDataSource {
    void getListTeams(GetTeamsCallback callback);

    interface GetTeamsCallback {
        void onTeamLoaded(TeamResponse data);

        void onDataNotAvailable(String errorMessage);
    }
}
