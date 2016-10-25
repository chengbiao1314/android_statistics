package statistics.cb.com.test;

import android.content.Context;
import android.content.Intent;

import java.util.Map;

import statistics.cb.com.test.bean.EventBean;
import statistics.cb.com.test.helper.EventDao;
import statistics.cb.com.test.helper.UploadService;

/**
 * Created by Ricky on 2016/10/25.
 */
public class StatisticsHelper {
    public static StatisticsHelper instance = null;
    private EventDao dao;

    public static StatisticsHelper getInstance(){
        if(instance == null){
            instance = new StatisticsHelper();
        }
        return instance;
    }

    Intent intent;
    public void initStatistics(Context context){
        System.out.println("Helper 要开启上传服务了......");
        intent = new Intent(context, UploadService.class);
        context.startService(intent);
    }
    public void stopStatistics(Context context){
        System.out.println("Helper 要关闭上传服务了......");
        context.stopService(intent);
    }

    public void onResume(Context context){
        if(dao == null){
            dao = new EventDao(context);
        }
    }

    public void onPause(Context context){
        if (dao != null){
            dao.closeDB();
            dao = null;
        }
    }
    public void StatisticsEvent(Context context ,String eventId , Map<String,Object> map){
        if(dao == null){
            System.out.println("统计没有实行初始化......");
        }

        EventBean event = new EventBean();
        event.setEvent_id(eventId);
        dao.add(event);

        System.out.println("数据条数......" + dao.getSize());
    }


}
