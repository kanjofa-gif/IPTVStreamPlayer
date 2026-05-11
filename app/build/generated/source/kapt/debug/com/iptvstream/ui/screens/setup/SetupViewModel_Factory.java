package com.iptvstream.ui.screens.setup;

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
public final class SetupViewModel_Factory implements Factory<SetupViewModel> {
  private final Provider<IPTVRepository> repositoryProvider;

  public SetupViewModel_Factory(Provider<IPTVRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SetupViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static SetupViewModel_Factory create(Provider<IPTVRepository> repositoryProvider) {
    return new SetupViewModel_Factory(repositoryProvider);
  }

  public static SetupViewModel newInstance(IPTVRepository repository) {
    return new SetupViewModel(repository);
  }
}
