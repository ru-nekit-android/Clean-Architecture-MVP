package ru.nekit.android.mvpmeeting.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.nekit.android.mvpmeeting.R;

/**
 * Created by ru.nekit.android on 05.03.16.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

}