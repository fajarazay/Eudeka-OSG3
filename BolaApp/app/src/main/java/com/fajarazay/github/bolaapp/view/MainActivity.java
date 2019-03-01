package com.fajarazay.github.bolaapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fajarazay.github.bolaapp.R;
import com.fajarazay.github.bolaapp.data.adapter.MainAdapter;
import com.fajarazay.github.bolaapp.databinding.ActivityMainBinding;
import com.fajarazay.github.bolaapp.model.TeamDetail;
import com.fajarazay.github.bolaapp.viewmodel.Injection;
import com.fajarazay.github.bolaapp.viewmodel.TeamNavigator;
import com.fajarazay.github.bolaapp.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TeamNavigator {
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
}

