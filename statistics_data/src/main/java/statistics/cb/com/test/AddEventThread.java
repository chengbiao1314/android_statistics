package statistics.cb.com.test;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Map;

import statistics.cb.com.test.bean.EventBean;
import statistics.cb.com.test.db.dao.EventDao;

/**
 * Created by Ricky on 2016/10/26.
 */
public class AddEventThread implements Runnable {
    private Context context;
    private String eventId;
    private Map<String,Object> map;

    public AddEventThread(Context context , String eventId , Map<String,Object> map){
        this.context = context;
        this.eventId = eventId;
        this.map = map;
    }

    @Override
    public void run() {
        EventBean event = new EventBean();
        event.setTime_stamp(System.currentTimeMillis());//时间戳
        event.setDevices_model("型号：" + android.os.Build.MODEL);//手机型号
        event.setSystem_version("系统版本："+ android.os.Build.VERSION.RELEASE);//系统版本
        event.setEvent_id(eventId);
        if(map != null){
            Gson gson = new Gson();

            event.setEvent_param(gson.toJson(map));
//            event.setEvent_param(map.toString());
        }

        EventDao.getInstance(context).insertData(event);
    }
}
