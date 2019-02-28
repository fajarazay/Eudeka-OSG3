package com.fajarazay.github.bolaapp.data.remote;

import com.fajarazay.github.bolaapp.data.TeamDataSource;
import com.fajarazay.github.bolaapp.model.TeamResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class TeamRemoteDataSource implements TeamDataSource {
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        Call<TeamResponse> call = apiInterface.getAllTeams("English Premier League");
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                callback.onTeamLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });

    }
}
