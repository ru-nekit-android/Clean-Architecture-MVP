package ru.nekit.android.mvpmeeting.view;

import java.util.List;

import ru.nekit.android.mvpmeeting.presenter.vo.Repository;

/**
 * Created by MacOS on 02.03.16.
 */
public interface IView {

    String getUserName();

    void showData(List<Repository> data);

    void showEmptyList();

    void showError(String error);
}
