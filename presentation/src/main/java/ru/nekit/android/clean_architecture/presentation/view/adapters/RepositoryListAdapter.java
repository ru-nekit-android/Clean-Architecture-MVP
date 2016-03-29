package ru.nekit.android.clean_architecture.presentation.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.clean_architecture.presentation.view.adapters.base.BaseAdapter;

public class RepositoryListAdapter extends BaseAdapter<RepositoryVO, RepositoryListAdapter.ViewHolder> {

    private WeakReference<RepositoryListPresenter> presenterRef;

    public RepositoryListAdapter(RepositoryListPresenter presenter) {
        this.presenterRef = new WeakReference<>(presenter);
    }

    public void setRepositoryList(List<RepositoryVO> repositoryList) {
        this.list = repositoryList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RepositoryVO repository = list.get(i);
        viewHolder.titleView.setText(repository.repoName);
        viewHolder.descriptionView.setText(repository.description);
        viewHolder.starsView.setText(repository.starsCount);
        viewHolder.forksView.setText(repository.forksCount);
        viewHolder.watchersView.setText(repository.watchersCount);
        if (presenterRef != null && presenterRef.get() != null) {
            viewHolder.rootView.setOnClickListener(view -> presenterRef.get().selectRepository(repository));
        }
    }

    public void destroy() {
        if (presenterRef != null) {
            presenterRef.clear();
        }
        presenterRef = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repository_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends BaseAdapter.ViewHolder {

        private TextView titleView;
        private TextView descriptionView;
        private TextView starsView;
        private TextView watchersView;
        private TextView forksView;
        private View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.text_title);
            descriptionView = (TextView) itemView.findViewById(R.id.text_description);
            starsView = (TextView) itemView.findViewById(R.id.text_stars);
            watchersView = (TextView) itemView.findViewById(R.id.text_watchers);
            forksView = (TextView) itemView.findViewById(R.id.text_forks);
            rootView = itemView.findViewById(R.id.card_view);
        }
    }
}