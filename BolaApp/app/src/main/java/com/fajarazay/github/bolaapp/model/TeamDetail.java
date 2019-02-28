package com.fajarazay.github.bolaapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
@Entity(tableName = "team")
public class TeamDetail {
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "team_name")
    @SerializedName("strTeam")
    private String teamName;

    @ColumnInfo(name = "team_logo")
    @SerializedName("strTeamBadge")
    private String teamLogo;

    public TeamDetail(int mId, String teamName, String teamLogo) {
        this.mId = mId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
    }

    public int getmId() {
        return mId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }


}
