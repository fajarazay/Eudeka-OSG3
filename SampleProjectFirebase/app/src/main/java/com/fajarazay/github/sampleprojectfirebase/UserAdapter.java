package com.fajarazay.github.sampleprojectfirebase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fajar Septian on 09/03/2019.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textViewEmail, textViewPhone;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
        }

        void bindView(int position) {
            textViewName.setText(userList.get(position).getName());
            textViewEmail.setText(userList.get(position).getEmail());
            textViewPhone.setText(userList.get(position).getPhone());
        }
    }
}

