package com.fajarazay.github.bolaapp.viewmodel;

import com.fajarazay.github.bolaapp.data.TeamDataSource;
import com.fajarazay.github.bolaapp.data.TeamRepository;
import com.fajarazay.github.bolaapp.model.TeamResponse;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class TeamViewModel {
    private TeamRepository teamRepository;
    private TeamNavigator teamNavigator;

    public TeamViewModel(TeamRepository teamRepository, TeamNavigator teamNavigator) {
        this.teamRepository = teamRepository;
        this.teamNavigator = teamNavigator;
    }

    public void setTeamNavigator(TeamNavigator teamNavigator) {
        this.teamNavigator = teamNavigator;
    }

    public void getListTeam() {
        teamRepository.getListTeams(new TeamDataSource.GetTeamsCallback() {
            @Override
            public void onTeamLoaded(TeamResponse data) {
                teamNavigator.loadListTeam(data.getTeams());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                teamNavigator.errorLoadListTeam(errorMessage);
            }
        });
    }
}
