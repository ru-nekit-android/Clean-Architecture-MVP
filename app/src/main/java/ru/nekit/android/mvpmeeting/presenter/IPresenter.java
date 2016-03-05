package ru.nekit.android.mvpmeeting.presenter;

import android.os.Bundle;

/**
 * Created by MacOS on 02.03.16.
 */
public interface IPresenter {

    void onLoadState(Bundle savedState);

    void onSaveState(Bundle outSate);

    void onStop();
}
