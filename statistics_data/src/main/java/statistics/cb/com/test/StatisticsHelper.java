package statistics.cb.com.test;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Map;

import statistics.cb.com.test.db.DBManager;

/**
 * Created by Ricky on 2016/10/25.
 */
public class StatisticsHelper {
    public static boolean isDebug = true;
    public static long indextime;
    public static StatisticsHelper instance = null;

    public static StatisticsHelper getInstance(){
        if(instance == null){
            instance = new StatisticsHelper();
        }
        return instance;
    }

    /**
     * 初始化 统计
     * @param context
     */
    public void initStatistics(Context context){
        indextime = System.currentTimeMillis();
        new Thread(new UploadEventThread(context)).start();

//        Intent intent = new Intent(context, UploadService.class);
//        context.startService(intent);
    }

    /**
     * 关闭统计，目的在于关闭数据库
     * @param context
     */
    public void closeStatistics(Context context){
        DBManager.getInstance(context).closeDatabase();
    }

    /**
     * 添加统计事件
     * @param context
     * @param eventId
     * @param map
     */
    public void AddEvent(Context context , String eventId , Map<String,Object> map){
        new Thread(new AddEventThread(context,eventId,map)).start();
    }


}
