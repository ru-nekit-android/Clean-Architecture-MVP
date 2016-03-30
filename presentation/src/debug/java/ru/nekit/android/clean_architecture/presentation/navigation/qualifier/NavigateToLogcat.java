package ru.nekit.android.clean_architecture.presentation.navigation.qualifier;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface NavigateToLogcat {
}
