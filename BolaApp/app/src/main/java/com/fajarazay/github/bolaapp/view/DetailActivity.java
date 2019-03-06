package com.fajarazay.github.bolaapp.view;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fajarazay.github.bolaapp.R;
import com.fajarazay.github.bolaapp.databinding.ActivityDetailBinding;
import com.fajarazay.github.bolaapp.model.TeamDetail;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    public static final String KEY_TEAM_DETAIL = "team_detail";
    public static final String KEY_TEAM_DETAIL_TRANSITION_NAME = "strTeamBadge";
    private ActivityDetailBinding binding;
    private TeamDetail teamDetail;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            teamDetail = (TeamDetail) bundle.getSerializable(KEY_TEAM_DETAIL);
        }
        //membuat tombol back toolbar
        setSupportActionBar(binding.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        setClub();
    }

    private void setClub() {
        String imageUrl = teamDetail.getTeamLogo();
        String imageTransitionName = bundle.getString(KEY_TEAM_DETAIL_TRANSITION_NAME);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.imageViewClub.setTransitionName(imageTransitionName);
        }
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        supportPostponeEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        supportPostponeEnterTransition();
                        return false;
                    }
                }).into(binding.imageViewClub);
        setTitle(teamDetail.getTeamName());
        binding.textViewDescription.setText(teamDetail.getTeamDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
