package com.fajarazay.github.bolaapp.data.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fajarazay.github.bolaapp.R;
import com.fajarazay.github.bolaapp.databinding.ItemRowBinding;
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
    private final Context context;
    private List<TeamDetail> teamDetails;
    private LayoutInflater layoutInflater;
    public MainAdapter(Context context) {
        this.context = context;
        teamDetails = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.binding.setTeamDetailVM(teamDetails.get(position));
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