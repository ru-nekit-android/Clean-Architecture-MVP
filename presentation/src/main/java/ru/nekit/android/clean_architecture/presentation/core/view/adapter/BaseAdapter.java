package ru.nekit.android.clean_architecture.presentation.core.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by ru.nekit.android on 05.03.16.
 */

public abstract class BaseAdapter<T, VH extends BaseAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}