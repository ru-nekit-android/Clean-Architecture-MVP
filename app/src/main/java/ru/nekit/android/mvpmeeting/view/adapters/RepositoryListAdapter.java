package ru.nekit.android.mvpmeeting.view.adapters;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.nekit.android.mvpmeeting.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;

public class RepositoryListAdapter extends BaseAdapter<Repository> {

    private WeakReference<RepositoryListPresenter> presenterRef;

    public RepositoryListAdapter(RepositoryListPresenter presenter) {
        this.presenterRef = new WeakReference<>(presenter);
    }

    public void setRepositoryList(List<Repository> repositoryList) {
        this.list = repositoryList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        Repository repository = list.get(i);
        viewHolder.text.setText(repository.toString());
        if (presenterRef != null && presenterRef.get() != null) {
            viewHolder.text.setOnClickListener(view -> presenterRef.get().selectRepository(repository));
        }
    }

}