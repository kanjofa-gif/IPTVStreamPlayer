package com.iptvstream.ui.screens.playlists;

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
public final class ManagePlaylistsViewModel_Factory implements Factory<ManagePlaylistsViewModel> {
  private final Provider<IPTVRepository> repositoryProvider;

  public ManagePlaylistsViewModel_Factory(Provider<IPTVRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ManagePlaylistsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ManagePlaylistsViewModel_Factory create(
      Provider<IPTVRepository> repositoryProvider) {
    return new ManagePlaylistsViewModel_Factory(repositoryProvider);
  }

  public static ManagePlaylistsViewModel newInstance(IPTVRepository repository) {
    return new ManagePlaylistsViewModel(repository);
  }
}
