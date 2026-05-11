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
import com.iptvstream.data.model.WatchProgress;
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
public final class WatchProgressDao_Impl implements WatchProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WatchProgress> __insertionAdapterOfWatchProgress;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProgress;

  public WatchProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWatchProgress = new EntityInsertionAdapter<WatchProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `watch_progress` (`streamId`,`streamName`,`streamIcon`,`streamUrl`,`streamType`,`positionMs`,`durationMs`,`watchedAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WatchProgress entity) {
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
        if (entity.getStreamUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStreamUrl());
        }
        if (entity.getStreamType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStreamType());
        }
        statement.bindLong(6, entity.getPositionMs());
        statement.bindLong(7, entity.getDurationMs());
        statement.bindLong(8, entity.getWatchedAt());
      }
    };
    this.__preparedStmtOfDeleteProgress = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM watch_progress WHERE streamId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object saveProgress(final WatchProgress progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWatchProgress.insert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProgress(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteProgress.acquire();
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
          __preparedStmtOfDeleteProgress.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WatchProgress>> getRecentlyWatched() {
    final String _sql = "SELECT * FROM watch_progress ORDER BY watchedAt DESC LIMIT 20";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"watch_progress"}, new Callable<List<WatchProgress>>() {
      @Override
      @NonNull
      public List<WatchProgress> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStreamId = CursorUtil.getColumnIndexOrThrow(_cursor, "streamId");
          final int _cursorIndexOfStreamName = CursorUtil.getColumnIndexOrThrow(_cursor, "streamName");
          final int _cursorIndexOfStreamIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "streamIcon");
          final int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
          final int _cursorIndexOfStreamType = CursorUtil.getColumnIndexOrThrow(_cursor, "streamType");
          final int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
          final int _cursorIndexOfWatchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "watchedAt");
          final List<WatchProgress> _result = new ArrayList<WatchProgress>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WatchProgress _item;
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
            final String _tmpStreamUrl;
            if (_cursor.isNull(_cursorIndexOfStreamUrl)) {
              _tmpStreamUrl = null;
            } else {
              _tmpStreamUrl = _cursor.getString(_cursorIndexOfStreamUrl);
            }
            final String _tmpStreamType;
            if (_cursor.isNull(_cursorIndexOfStreamType)) {
              _tmpStreamType = null;
            } else {
              _tmpStreamType = _cursor.getString(_cursorIndexOfStreamType);
            }
            final long _tmpPositionMs;
            _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final long _tmpWatchedAt;
            _tmpWatchedAt = _cursor.getLong(_cursorIndexOfWatchedAt);
            _item = new WatchProgress(_tmpStreamId,_tmpStreamName,_tmpStreamIcon,_tmpStreamUrl,_tmpStreamType,_tmpPositionMs,_tmpDurationMs,_tmpWatchedAt);
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
  public Object getProgress(final String id,
      final Continuation<? super WatchProgress> $completion) {
    final String _sql = "SELECT * FROM watch_progress WHERE streamId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WatchProgress>() {
      @Override
      @Nullable
      public WatchProgress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStreamId = CursorUtil.getColumnIndexOrThrow(_cursor, "streamId");
          final int _cursorIndexOfStreamName = CursorUtil.getColumnIndexOrThrow(_cursor, "streamName");
          final int _cursorIndexOfStreamIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "streamIcon");
          final int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
          final int _cursorIndexOfStreamType = CursorUtil.getColumnIndexOrThrow(_cursor, "streamType");
          final int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
          final int _cursorIndexOfWatchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "watchedAt");
          final WatchProgress _result;
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
            final String _tmpStreamUrl;
            if (_cursor.isNull(_cursorIndexOfStreamUrl)) {
              _tmpStreamUrl = null;
            } else {
              _tmpStreamUrl = _cursor.getString(_cursorIndexOfStreamUrl);
            }
            final String _tmpStreamType;
            if (_cursor.isNull(_cursorIndexOfStreamType)) {
              _tmpStreamType = null;
            } else {
              _tmpStreamType = _cursor.getString(_cursorIndexOfStreamType);
            }
            final long _tmpPositionMs;
            _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final long _tmpWatchedAt;
            _tmpWatchedAt = _cursor.getLong(_cursorIndexOfWatchedAt);
            _result = new WatchProgress(_tmpStreamId,_tmpStreamName,_tmpStreamIcon,_tmpStreamUrl,_tmpStreamType,_tmpPositionMs,_tmpDurationMs,_tmpWatchedAt);
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

  @Override
  public Flow<List<WatchProgress>> getRecentLive() {
    final String _sql = "SELECT * FROM watch_progress WHERE streamType = 'live' ORDER BY watchedAt DESC LIMIT 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"watch_progress"}, new Callable<List<WatchProgress>>() {
      @Override
      @NonNull
      public List<WatchProgress> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStreamId = CursorUtil.getColumnIndexOrThrow(_cursor, "streamId");
          final int _cursorIndexOfStreamName = CursorUtil.getColumnIndexOrThrow(_cursor, "streamName");
          final int _cursorIndexOfStreamIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "streamIcon");
          final int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
          final int _cursorIndexOfStreamType = CursorUtil.getColumnIndexOrThrow(_cursor, "streamType");
          final int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
          final int _cursorIndexOfWatchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "watchedAt");
          final List<WatchProgress> _result = new ArrayList<WatchProgress>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WatchProgress _item;
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
            final String _tmpStreamUrl;
            if (_cursor.isNull(_cursorIndexOfStreamUrl)) {
              _tmpStreamUrl = null;
            } else {
              _tmpStreamUrl = _cursor.getString(_cursorIndexOfStreamUrl);
            }
            final String _tmpStreamType;
            if (_cursor.isNull(_cursorIndexOfStreamType)) {
              _tmpStreamType = null;
            } else {
              _tmpStreamType = _cursor.getString(_cursorIndexOfStreamType);
            }
            final long _tmpPositionMs;
            _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final long _tmpWatchedAt;
            _tmpWatchedAt = _cursor.getLong(_cursorIndexOfWatchedAt);
            _item = new WatchProgress(_tmpStreamId,_tmpStreamName,_tmpStreamIcon,_tmpStreamUrl,_tmpStreamType,_tmpPositionMs,_tmpDurationMs,_tmpWatchedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
