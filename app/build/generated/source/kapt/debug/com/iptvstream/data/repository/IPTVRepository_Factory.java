package com.iptvstream.data.repository;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.iptvstream.data.api.AppDatabase;
import com.iptvstream.data.api.XtreamApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class IPTVRepository_Factory implements Factory<IPTVRepository> {
  private final Provider<XtreamApiService> apiProvider;

  private final Provider<AppDatabase> dbProvider;

  private final Provider<DataStore<Preferences>> dataStoreProvider;

  public IPTVRepository_Factory(Provider<XtreamApiService> apiProvider,
      Provider<AppDatabase> dbProvider, Provider<DataStore<Preferences>> dataStoreProvider) {
    this.apiProvider = apiProvider;
    this.dbProvider = dbProvider;
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public IPTVRepository get() {
    return newInstance(apiProvider.get(), dbProvider.get(), dataStoreProvider.get());
  }

  public static IPTVRepository_Factory create(Provider<XtreamApiService> apiProvider,
      Provider<AppDatabase> dbProvider, Provider<DataStore<Preferences>> dataStoreProvider) {
    return new IPTVRepository_Factory(apiProvider, dbProvider, dataStoreProvider);
  }

  public static IPTVRepository newInstance(XtreamApiService api, AppDatabase db,
      DataStore<Preferences> dataStore) {
    return new IPTVRepository(api, db, dataStore);
  }
}
