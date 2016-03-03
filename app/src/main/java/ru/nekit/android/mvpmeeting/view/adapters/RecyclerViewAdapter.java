package ru.nekit.android.mvpmeeting.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.model.data.Repo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Repo> repoList = new ArrayList<>();

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_layout, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Repo Repo = repoList.get(i);
        viewHolder.name.setText(Repo.getName());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}