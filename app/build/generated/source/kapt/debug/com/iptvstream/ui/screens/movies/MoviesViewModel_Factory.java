package com.iptvstream.ui.screens.movies;

import com.iptvstream.data.repository.IPTVRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class MoviesViewModel_Factory implements Factory<MoviesViewModel> {
  private final Provider<IPTVRepository> repositoryProvider;

  public MoviesViewModel_Factory(Provider<IPTVRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public MoviesViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static MoviesViewModel_Factory create(Provider<IPTVRepository> repositoryProvider) {
    return new MoviesViewModel_Factory(repositoryProvider);
  }

  public static MoviesViewModel newInstance(IPTVRepository repository) {
    return new MoviesViewModel(repository);
  }
}
