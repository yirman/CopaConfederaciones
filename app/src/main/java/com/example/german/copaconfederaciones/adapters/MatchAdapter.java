package com.example.german.copaconfederaciones.adapters;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.models.get.Item;
import com.example.german.copaconfederaciones.utils.Utilities;

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

        String ISO8601UTC = items.get(i).getStartDate();
        String ISO8601Local = Utilities.toLocalDateTime(ISO8601UTC);
        String regularDate = Utilities.getRegularDate(ISO8601Local);
        String regularTime = Utilities.getRegularTime(ISO8601Local);

        holder.date.setText(regularDate);
        holder.time.setText(regularTime);

        String teamA = items.get(i).getHomeTeam().getName();
        holder.nameTeamA.setText(teamA);
        holder.scoreTeamA.setText(String.valueOf(items.get(i).getHomeScore()));
        setTeamImage(teamA, holder.imageTeamA);

        String teamB = items.get(i).getAwayTeam().getName();
        holder.nameTeamB.setText(teamB);
        holder.scoreTeamB.setText(String.valueOf(items.get(i).getAwayScore()));
        setTeamImage(teamB, holder.imageTeamB);

        holder.status.setText(items.get(i).getEventStatus().getName().getOriginal());
    }

    private void setTeamImage(String team, ImageView imageView){

        switch(team){

            case "Nueva Zelanda":
                imageView.setImageResource(R.drawable.ic_new_zealand_team);
                break;

            case "Rusia":
                imageView.setImageResource(R.drawable.ic_russia_team);
                break;

            case "Portugal":
                imageView.setImageResource(R.drawable.ic_portugal_team);
                break;

            case "México":
                imageView.setImageResource(R.drawable.ic_mexico_team);
                break;

            case "Camerún":
                imageView.setImageResource(R.drawable.ic_cameroon_team);
                break;

            case "Chile":
                imageView.setImageResource(R.drawable.ic_chile_team);
                break;

            case "Australia":
                imageView.setImageResource(R.drawable.ic_australia_team);
                break;

            case "Alemania":
                imageView.setImageResource(R.drawable.ic_germany_team);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder{

        public TextView date;
        public TextView time;

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
            this.time = itemView.findViewById(R.id.tv_match_time);

            this.imageTeamA = itemView.findViewById(R.id.iv_image_team_a);
            this.nameTeamA = itemView.findViewById(R.id.tv_name_team_a);
            this.scoreTeamA = itemView.findViewById(R.id.tv_score_team_a);

            this.imageTeamB = itemView.findViewById(R.id.iv_image_team_b);
            this.nameTeamB = itemView.findViewById(R.id.tv_name_team_b);
            this.scoreTeamB = itemView.findViewById(R.id.tv_score_team_b);

            this.status = itemView.findViewById(R.id.tv_match_status);


        }
    }

    public static class Decoration extends RecyclerView.ItemDecoration{

        private int paddingTop;
        private int paddingBottom;
        private int paddingLeft;
        private int paddingRight;
        private int colorId1;
        private int colorId2;

        public Decoration(int padding, int colorid1, int colorid2) {
            this(padding, padding, padding, padding, colorid1, colorid2);
        }

        public Decoration(int paddingTopBottom, int paddingLeftRight, int colorid1, int colorid2) {
            this(paddingTopBottom, paddingTopBottom, paddingLeftRight, paddingLeftRight, colorid1, colorid2);
        }

        public Decoration(int paddingTop,
                          int paddingBottom,
                          int paddingLeft,
                          int paddingRight,
                          int colorid1,
                          int colorid2) {

            this.paddingTop = paddingTop;
            this.paddingBottom = paddingBottom;
            this.paddingLeft = paddingLeft;
            this.paddingRight = paddingRight;
            this.colorId1 = colorid1;
            this.colorId2 = colorid2;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.top = paddingTop;
            outRect.bottom = paddingBottom;
            outRect.left = paddingLeft;
            outRect.right = paddingRight;

            if( (state.getItemCount() - 1) == (parent.getChildAdapterPosition(view)) )
                outRect.bottom = 0;

            if(parent.getChildAdapterPosition(view) % 2 != 0)
                view.setBackgroundResource(this.colorId1);
            else
                view.setBackgroundResource(this.colorId2);
        }
    }

}
