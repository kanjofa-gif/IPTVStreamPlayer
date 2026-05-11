package com.iptvstream.ui.screens.loading;

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
public final class LoadingViewModel_Factory implements Factory<LoadingViewModel> {
  private final Provider<IPTVRepository> repositoryProvider;

  public LoadingViewModel_Factory(Provider<IPTVRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LoadingViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static LoadingViewModel_Factory create(Provider<IPTVRepository> repositoryProvider) {
    return new LoadingViewModel_Factory(repositoryProvider);
  }

  public static LoadingViewModel newInstance(IPTVRepository repository) {
    return new LoadingViewModel(repository);
  }
}
