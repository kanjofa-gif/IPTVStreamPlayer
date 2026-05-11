package com.iptvstream.data.api;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.iptvstream.data.model.Favorite;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoriteDao_Impl implements FavoriteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Favorite> __insertionAdapterOfFavorite;

  private final SharedSQLiteStatement __preparedStmtOfRemoveFavorite;

  public FavoriteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavorite = new EntityInsertionAdapter<Favorite>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `favorites` (`streamId`,`streamName`,`streamIcon`,`streamType`,`categoryId`,`addedAt`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Favorite entity) {
        if (entity.getStreamId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getStreamId());
        }
        if (entity.getStreamName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getStreamName());
        }
        if (entity.getStreamIcon() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStreamIcon());
        }
        if (entity.getStreamType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStreamType());
        }
        if (entity.getCategoryId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategoryId());
        }
        statement.bindLong(6, entity.getAddedAt());
      }
    };
    this.__preparedStmtOfRemoveFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM favorites WHERE streamId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object addFavorite(final Favorite favorite, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFavorite.insert(favorite);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeFavorite(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveFavorite.acquire();
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveFavorite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Favorite>> getAllFavorites() {
    final String _sql = "SELECT * FROM favorites ORDER BY addedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"favorites"}, new Callable<List<Favorite>>() {
      @Override
      @NonNull
      public List<Favorite> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStreamId = CursorUtil.getColumnIndexOrThrow(_cursor, "streamId");
          final int _cursorIndexOfStreamName = CursorUtil.getColumnIndexOrThrow(_cursor, "streamName");
          final int _cursorIndexOfStreamIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "streamIcon");
          final int _cursorIndexOfStreamType = CursorUtil.getColumnIndexOrThrow(_cursor, "streamType");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final List<Favorite> _result = new ArrayList<Favorite>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Favorite _item;
            final String _tmpStreamId;
            if (_cursor.isNull(_cursorIndexOfStreamId)) {
              _tmpStreamId = null;
            } else {
              _tmpStreamId = _cursor.getString(_cursorIndexOfStreamId);
            }
            final String _tmpStreamName;
            if (_cursor.isNull(_cursorIndexOfStreamName)) {
              _tmpStreamName = null;
            } else {
              _tmpStreamName = _cursor.getString(_cursorIndexOfStreamName);
            }
            final String _tmpStreamIcon;
            if (_cursor.isNull(_cursorIndexOfStreamIcon)) {
              _tmpStreamIcon = null;
            } else {
              _tmpStreamIcon = _cursor.getString(_cursorIndexOfStreamIcon);
            }
            final String _tmpStreamType;
            if (_cursor.isNull(_cursorIndexOfStreamType)) {
              _tmpStreamType = null;
            } else {
              _tmpStreamType = _cursor.getString(_cursorIndexOfStreamType);
            }
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new Favorite(_tmpStreamId,_tmpStreamName,_tmpStreamIcon,_tmpStreamType,_tmpCategoryId,_tmpAddedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Favorite>> getFavoritesByType(final String type) {
    final String _sql = "SELECT * FROM favorites WHERE streamType = ? ORDER BY addedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"favorites"}, new Callable<List<Favorite>>() {
      @Override
      @NonNull
      public List<Favorite> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStreamId = CursorUtil.getColumnIndexOrThrow(_cursor, "streamId");
          final int _cursorIndexOfStreamName = CursorUtil.getColumnIndexOrThrow(_cursor, "streamName");
          final int _cursorIndexOfStreamIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "streamIcon");
          final int _cursorIndexOfStreamType = CursorUtil.getColumnIndexOrThrow(_cursor, "streamType");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final List<Favorite> _result = new ArrayList<Favorite>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Favorite _item;
            final String _tmpStreamId;
            if (_cursor.isNull(_cursorIndexOfStreamId)) {
              _tmpStreamId = null;
            } else {
              _tmpStreamId = _cursor.getString(_cursorIndexOfStreamId);
            }
            final String _tmpStreamName;
            if (_cursor.isNull(_cursorIndexOfStreamName)) {
              _tmpStreamName = null;
            } else {
              _tmpStreamName = _cursor.getString(_cursorIndexOfStreamName);
            }
            final String _tmpStreamIcon;
            if (_cursor.isNull(_cursorIndexOfStreamIcon)) {
              _tmpStreamIcon = null;
            } else {
              _tmpStreamIcon = _cursor.getString(_cursorIndexOfStreamIcon);
            }
            final String _tmpStreamType;
            if (_cursor.isNull(_cursorIndexOfStreamType)) {
              _tmpStreamType = null;
            } else {
              _tmpStreamType = _cursor.getString(_cursorIndexOfStreamType);
            }
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new Favorite(_tmpStreamId,_tmpStreamName,_tmpStreamIcon,_tmpStreamType,_tmpCategoryId,_tmpAddedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getFavorite(final String id, final Continuation<? super Favorite> $completion) {
    final String _sql = "SELECT * FROM favorites WHERE streamId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Favorite>() {
      @Override
      @Nullable
      public Favorite call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStreamId = CursorUtil.getColumnIndexOrThrow(_cursor, "streamId");
          final int _cursorIndexOfStreamName = CursorUtil.getColumnIndexOrThrow(_cursor, "streamName");
          final int _cursorIndexOfStreamIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "streamIcon");
          final int _cursorIndexOfStreamType = CursorUtil.getColumnIndexOrThrow(_cursor, "streamType");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final Favorite _result;
          if (_cursor.moveToFirst()) {
            final String _tmpStreamId;
            if (_cursor.isNull(_cursorIndexOfStreamId)) {
              _tmpStreamId = null;
            } else {
              _tmpStreamId = _cursor.getString(_cursorIndexOfStreamId);
            }
            final String _tmpStreamName;
            if (_cursor.isNull(_cursorIndexOfStreamName)) {
              _tmpStreamName = null;
            } else {
              _tmpStreamName = _cursor.getString(_cursorIndexOfStreamName);
            }
            final String _tmpStreamIcon;
            if (_cursor.isNull(_cursorIndexOfStreamIcon)) {
              _tmpStreamIcon = null;
            } else {
              _tmpStreamIcon = _cursor.getString(_cursorIndexOfStreamIcon);
            }
            final String _tmpStreamType;
            if (_cursor.isNull(_cursorIndexOfStreamType)) {
              _tmpStreamType = null;
            } else {
              _tmpStreamType = _cursor.getString(_cursorIndexOfStreamType);
            }
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _result = new Favorite(_tmpStreamId,_tmpStreamName,_tmpStreamIcon,_tmpStreamType,_tmpCategoryId,_tmpAddedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
