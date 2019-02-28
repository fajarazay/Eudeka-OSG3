package com.fajarazay.github.bolaapp.viewmodel;

import com.fajarazay.github.bolaapp.model.TeamDetail;

import java.util.List;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public interface TeamNavigator {
    void errorLoadListTeam(String message);

    void loadListTeam(List<TeamDetail> teamDetails);
}
