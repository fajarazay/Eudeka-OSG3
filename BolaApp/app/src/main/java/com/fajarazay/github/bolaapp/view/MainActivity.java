package com.fajarazay.github.bolaapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fajarazay.github.bolaapp.Injection;
import com.fajarazay.github.bolaapp.R;
import com.fajarazay.github.bolaapp.adapter.MainAdapter;
import com.fajarazay.github.bolaapp.databinding.ActivityMainBinding;
import com.fajarazay.github.bolaapp.listener.ClubItemClickListener;
import com.fajarazay.github.bolaapp.model.TeamDetail;
import com.fajarazay.github.bolaapp.viewmodel.TeamNavigator;
import com.fajarazay.github.bolaapp.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.fajarazay.github.bolaapp.view.DetailActivity.KEY_TEAM_DETAIL;
import static com.fajarazay.github.bolaapp.view.DetailActivity.KEY_TEAM_DETAIL_TRANSITION_NAME;

public class MainActivity extends AppCompatActivity implements TeamNavigator, ClubItemClickListener {
    private MainAdapter adapter;
    private List<TeamDetail> teamDetails;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        final TeamViewModel teamViewModel = new TeamViewModel(Injection.provideTeamRepository(this), this);
        teamDetails = new ArrayList<>();
        teamViewModel.setTeamNavigator(this);
        teamViewModel.getListTeam();
        binding.setVm(teamViewModel);
        initAdapter();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                teamViewModel.getListTeam();
            }
        });
    }

    @Override
    public void errorLoadListTeam(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadListTeam(List<TeamDetail> teamDetails) {
        this.teamDetails.addAll(teamDetails);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initAdapter() {
        adapter = new MainAdapter(this);
        adapter.setData(teamDetails);
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClubItemClick(TeamDetail clubItem, ImageView shareImageView) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(KEY_TEAM_DETAIL, clubItem);
        intent.putExtra(KEY_TEAM_DETAIL_TRANSITION_NAME, ViewCompat.getTransitionName(shareImageView));
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        shareImageView,
                        ViewCompat.getTransitionName(shareImageView)
                );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, optionsCompat.toBundle());
        }
    }
}

