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
    private List<EventBean> list;

    public UploadEventThread(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        Log.v("statistics","uploadEventThread -> 上传数据的线程执行了...");
        if(isUpload()){
            upload(list);
        }
    }

    private boolean isUpload(){
        if(list == null){
            list = new ArrayList<>();
        }else{
            list.clear();
        }

        /*** start 查询数据库确定需要上传的数据 **/
        list = EventDao.getInstance(context).queryEventList(5);
        /*** end 查询数据库确定需要上传的数据 **/

        if(list != null && list.size() >0){
            return true;
        }else{
            if(StatisticsHelper.isDebug){
                Log.v("statistics","uploadEventThread-->  服务的查询结果为空");
            }
            return false;
        }
    }

    private void upload(List<EventBean> list){
        if(StatisticsHelper.isDebug){
            Log.v("statistics","uploadEventThread-->  查询的数据长度" + list.size());
            Log.v("statistics","uploadEventThread-->  查询结果：" + JsonUtil.getInstances().beanToJson(list));
        }
        try{
            Thread.sleep(10000);
        }catch(InterruptedException e){
            System.out.println("线程异常...");
        }

        EventDao.getInstance(context).deleteData(5);
        upload(list);
    }
}
