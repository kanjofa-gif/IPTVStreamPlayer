package com.iptvstream.ui.screens.live;

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
public final class LiveViewModel_Factory implements Factory<LiveViewModel> {
  private final Provider<IPTVRepository> repositoryProvider;

  public LiveViewModel_Factory(Provider<IPTVRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LiveViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static LiveViewModel_Factory create(Provider<IPTVRepository> repositoryProvider) {
    return new LiveViewModel_Factory(repositoryProvider);
  }

  public static LiveViewModel newInstance(IPTVRepository repository) {
    return new LiveViewModel(repository);
  }
}
