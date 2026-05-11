package com.iptvstream.ui.screens.player;

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
public final class PlayerViewModel_Factory implements Factory<PlayerViewModel> {
  private final Provider<IPTVRepository> repositoryProvider;

  public PlayerViewModel_Factory(Provider<IPTVRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PlayerViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static PlayerViewModel_Factory create(Provider<IPTVRepository> repositoryProvider) {
    return new PlayerViewModel_Factory(repositoryProvider);
  }

  public static PlayerViewModel newInstance(IPTVRepository repository) {
    return new PlayerViewModel(repository);
  }
}
