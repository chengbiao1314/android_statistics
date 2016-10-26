package statistics.cb.com.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private static DBManager instance;
	private static SQLiteDatabase database;
	private Context context;

	private DBManager(Context context) {
		this.context = context;
	}

	public synchronized static DBManager getInstance(Context context) {
		if (instance == null) {
			instance = new DBManager(context);
		}
		return instance;
	}

	public synchronized SQLiteDatabase getDatabase() {
		if (null == database) {
			database = new SqliteDBHelper(context).getReadableDatabase();
		}
		return database;
	}

	public void closeDatabase() {
		if (null != database) {
			database.close();
			database = null;
			instance = null;
		}
	}

}
