package com.example.german.copaconfederaciones.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.models.get.Item;

import java.util.List;

/**
 * Created by German on 7/6/2018.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder>{

    private List<Item> items;

    public MatchAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int i) {

        holder.date.setText(items.get(i).getStartDate());

        holder.nameTeamA.setText(items.get(i).getHomeTeam().getName());
        holder.scoreTeamA.setText(String.valueOf(items.get(i).getHomeScore()));

        holder.nameTeamB.setText(items.get(i).getAwayTeam().getName());
        holder.scoreTeamB.setText(String.valueOf(items.get(i).getAwayScore()));

        holder.status.setText(items.get(i).getEventStatus().getName().getOriginal());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder{

        public TextView date;

        public ImageView imageTeamA;
        public TextView nameTeamA;
        public TextView scoreTeamA;

        public ImageView imageTeamB;
        public TextView nameTeamB;
        public TextView scoreTeamB;

        public TextView status;

        public MatchViewHolder(View itemView) {
            super(itemView);

            this.date = itemView.findViewById(R.id.tv_match_date);
            this.imageTeamA = itemView.findViewById(R.id.iv_image_team_a);
            this.nameTeamA = itemView.findViewById(R.id.tv_name_team_a);
            this.scoreTeamA = itemView.findViewById(R.id.tv_score_team_a);

            this.imageTeamB = itemView.findViewById(R.id.iv_image_team_b);
            this.nameTeamB = itemView.findViewById(R.id.tv_name_team_b);
            this.scoreTeamB = itemView.findViewById(R.id.tv_score_team_b);

            this.status = itemView.findViewById(R.id.tv_match_status);

        }
    }

}
