package com.fajarazay.github.bolaapp.data.local;

import android.content.Context;

import com.fajarazay.github.bolaapp.data.TeamDataSource;
import com.fajarazay.github.bolaapp.model.TeamDetail;
import com.fajarazay.github.bolaapp.model.TeamResponse;

import java.util.List;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class TeamLocalDataSource implements TeamDataSource {
    private Context context;
    private TeamDao teamDao;

    public TeamLocalDataSource(Context context) {
        this.context = context;
        teamDao = TeamDataBase.getInstance(this.context).teamDao();
    }

    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TeamDetail> team = teamDao.getTeams();
                if (team.isEmpty()) {
                    callback.onDataNotAvailable("Data di Database SQLITE kosong");
                } else {
                    TeamResponse teamResponse = new TeamResponse(team);
                    callback.onTeamLoaded(teamResponse);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void saveDataTeam(final List<TeamDetail> teamDetails) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                teamDao.insertTeam(teamDetails);
            }
        };
        new Thread(runnable).start();
    }
}
