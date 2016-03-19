package ru.nekit.android.mvpmeeting.presentation.view.adapters;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presentation.view.adapters.base.BaseAdapter;

public class RepositoryListAdapter extends BaseAdapter<RepositoryVO> {

    private WeakReference<RepositoryListPresenter> presenterRef;

    public RepositoryListAdapter(RepositoryListPresenter presenter) {
        this.presenterRef = new WeakReference<>(presenter);
    }

    public void setRepositoryList(List<RepositoryVO> repositoryList) {
        this.list = repositoryList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        RepositoryVO repository = list.get(i);
        viewHolder.text.setText(repository.toString());
        if (presenterRef != null && presenterRef.get() != null) {
            viewHolder.text.setOnClickListener(view -> presenterRef.get().selectRepository(repository));
        }
    }

}