package statistics.cb.com.test.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import statistics.cb.com.test.bean.EventBean;

/**
 * author :ChengBiao
 * description : 统计的操作类
 */
public class StatisticsDaoHelper extends OrmLiteSqliteOpenHelper {
    private final static String DATABASE_NAME = "statistics.db";
    private Map<String, Dao> daos = new HashMap();
    private Context mContext;
    private static StatisticsDaoHelper instance;

    public StatisticsDaoHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);// 1是数据库的版本号
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, EventBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, EventBean.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            if(daos.get(key) != null)
                daos.put(key,null);
        }
    }

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized StatisticsDaoHelper getStatisticsDaoHelperInstance(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (StatisticsDaoHelper.class) {
                if (instance == null)
                    instance = new StatisticsDaoHelper(context);
            }
        }
        return instance;
    }

    /**
     * 获取dao
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 删除数据库
     */
    public void deleteDB() {
        if (mContext != null) {
            File f = mContext.getDatabasePath(DATABASE_NAME);
            if (f.exists()) {
                // mContext.deleteDatabase(DATABASE_NAME);
                f.delete();
            } else {
                mContext.deleteDatabase(DATABASE_NAME);
            }

            File file = mContext.getDatabasePath(this.getReadableDatabase().getPath());
            if (file.exists()) {
                file.delete();
            }

//            File file2 = mContext.getDatabasePath(DATABASE_PATH_JOURN);
//            if (file2.exists()) {
//                LogTool.e("DB", "---delete SDCard DB 333---");
//                file2.delete();
//            }
        }
    }
}
