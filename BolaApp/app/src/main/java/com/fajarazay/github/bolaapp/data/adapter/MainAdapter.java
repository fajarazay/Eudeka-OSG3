package com.fajarazay.github.bolaapp.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fajarazay.github.bolaapp.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return teamDetails.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private ImageView imageViewBadge;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewBadge = itemView.findViewById(R.id.imageViewBadge);
        }

        void bindView(int position) {
            textViewName.setText(teamDetails.get(position).getTeamName());
            Glide.with(itemView.getContext()).load(teamDetails.get(position).getTeamLogo()).into(imageViewBadge);
        }
    }
}