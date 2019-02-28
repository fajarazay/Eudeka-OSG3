package com.fajarazay.github.bolaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TeamResponse {

    @SerializedName("teams")
    private List<TeamDetail> teams;

    public TeamResponse(List<TeamDetail> teams) {
        this.teams = teams;
    }

    public List<TeamDetail> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDetail> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return
                "TeamResponse{" +
                        "teams = '" + teams + '\'' +
                        "}";
    }
}