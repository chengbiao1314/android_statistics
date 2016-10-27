package statistics.cb.com.test;

import android.app.Application;

/**
 * Created by Ricky on 2016/10/27.
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //启动数据统计
        StatisticsHelper.getInstance().initStatistics(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        //关闭数据库
        StatisticsHelper.getInstance().closeStatistics(this);
    }
}
