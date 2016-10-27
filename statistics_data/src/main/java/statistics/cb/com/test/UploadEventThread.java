package statistics.cb.com.test;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import statistics.cb.com.test.bean.EventBean;
import statistics.cb.com.test.db.dao.EventDao;
import statistics.cb.com.test.util.JsonUtil;

/**
 * Created by Ricky on 2016/10/26.
 */
public class UploadEventThread implements Runnable {
    private Context context;

    public UploadEventThread(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        Log.v("statistics","uploadEventThread -> 上传数据的线程执行了...");
        isUpload();
    }

    private boolean isUpload(){
        /*** start 查询数据库确定需要上传的数据 **/
        List<EventBean> list = EventDao.getInstance(context).queryEventList(5);
        /*** end 查询数据库确定需要上传的数据 **/

        if(list != null && list.size() >0){
            upload(list);
            return true;
        }else{
            if(StatisticsHelper.isDebug){
                Log.v("statistics","uploadEventThread-->  上传线程的查询结果为空");
            }
            return false;
        }
    }

    private void upload(List<EventBean> list){
        if(StatisticsHelper.isDebug){
            Log.v("statistics","uploadEventThread-->  查询的数据长度" + list.size());
            Log.v("statistics","uploadEventThread-->  查询结果：" + JsonUtil.getInstances().beanToJson(list));
            Log.v("statistics","uploadEventThread-->  Map参数结果：" + JsonUtil.getInstances().beanToJson(list.get(0).getEvent_param()));
        }
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println("线程异常...");
        }

        EventDao.getInstance(context).deleteData(5);
        isUpload();
    }
}
