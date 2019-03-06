package com.fajarazay.github.bolaapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
@Entity(tableName = "team")
public class TeamDetail extends BaseObservable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "team_name")
    @SerializedName("strTeam")
    private String teamName;

    @ColumnInfo(name = "team_logo")
    @SerializedName("strTeamBadge")
    private String teamLogo;

    @ColumnInfo(name = "team_description")
    @SerializedName("strDescriptionEN")
    private String teamDescription;

    public TeamDetail(int mId, String teamName, String teamLogo, String teamDescription) {
        this.mId = mId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.teamDescription = teamDescription;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public int getmId() {
        return mId;
    }

    @Bindable
    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    @BindingAdapter({"teamLogo"})
    public static void loadImage( ImageView view, String imageUrl){
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
