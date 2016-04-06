package ru.nekit.android.clean_architecture.presentation.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}