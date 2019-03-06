package com.fajarazay.github.bolaapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajarazay.github.bolaapp.R;
import com.fajarazay.github.bolaapp.databinding.ItemRowBinding;
import com.fajarazay.github.bolaapp.listener.ClubItemClickListener;
import com.fajarazay.github.bolaapp.model.TeamDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //private final Context context;
    private List<TeamDetail> teamDetails;
    private LayoutInflater layoutInflater;
    private ClubItemClickListener clubItemClickListener;

    public MainAdapter(ClubItemClickListener clubItemClickListener) {
        //this.context = context;
        this.clubItemClickListener = clubItemClickListener;
        this.teamDetails = new ArrayList<>();
    }

    public void setData(List<TeamDetail> teamDetails) {
        this.teamDetails = teamDetails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater== null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ItemRowBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_row, viewGroup, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        holder.binding.setTeamDetailVM(teamDetails.get(position));
        ViewCompat.setTransitionName(holder.binding.imageViewBadge, teamDetails.get(position).getTeamLogo());
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clubItemClickListener.onClubItemClick(teamDetails.get(position), holder.binding.imageViewBadge);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRowBinding binding;
        ViewHolder(ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
        }
    }
}