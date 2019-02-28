package com.fajarazay.github.bolaapp.data.remote;

import com.fajarazay.github.bolaapp.model.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
interface ApiInterface {
    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamResponse> getAllTeams(@Query("l") String search);
}
