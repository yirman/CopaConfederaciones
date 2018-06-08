package com.example.german.copaconfederaciones.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        MatchViewHolder mvh = new MatchViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder{

        public MatchViewHolder(View itemView) {
            super(itemView);
        }
    }

}
