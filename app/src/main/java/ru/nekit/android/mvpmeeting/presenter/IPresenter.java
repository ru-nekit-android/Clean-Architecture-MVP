package ru.nekit.android.mvpmeeting.presenter;

import android.os.Bundle;

/**
 * Created by MacOS on 02.03.16.
 */
public interface IPresenter {

    void onRestoreInstanceState(Bundle savedState);

    void onSaveInstanceState(Bundle outSate);

    void onStop();
}
