package ru.nekit.android.clean_architecture.data.di.repository.qualifier;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface LongOperationThread {
}
