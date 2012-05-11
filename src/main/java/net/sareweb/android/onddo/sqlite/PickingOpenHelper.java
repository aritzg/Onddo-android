package net.sareweb.android.onddo.sqlite;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.lifedroid.sqlite.generic.LDSQLiteHelper;

public class PickingOpenHelper extends LDSQLiteHelper<Picking> {
	
	public PickingOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		Log.d(TAG, "Created open helper");
	}

	public PickingOpenHelper(Context context) {
		super(context, OnddoConstants.DB_NAME, null, OnddoConstants.DB_VERSION);
		Log.d(TAG, "Created open helper");
	}
	
	public List<Picking> getByUserId(long userId){
		String[] selectionArgs = new String[] {String.valueOf(userId)};
		return query("userId=? and objectStatus!='" + OBJECT_STATUS_DELETED_LOCAL + "' and objectStatus!='" + OBJECT_STATUS_DELETED_REMOTE + "'", selectionArgs);
	}
	
	public List<Picking> getByUserIdDeletedToo(long userId){
		String[] selectionArgs = new String[] {String.valueOf(userId)};
		return query("userId=?", selectionArgs);
	}
	
}
