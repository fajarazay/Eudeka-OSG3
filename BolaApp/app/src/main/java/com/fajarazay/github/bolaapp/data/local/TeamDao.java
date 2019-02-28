package com.fajarazay.github.bolaapp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.fajarazay.github.bolaapp.model.TeamDetail;

import java.util.List;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
@Dao
public interface TeamDao {
    @Query("Select * from team")
    List<TeamDetail> getTeams();

    @Insert
    void insertTeam(List<TeamDetail> team);
}
