package statistics.cb.com.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import statistics.cb.com.test.bean.EventBean;
import statistics.cb.com.test.db.dao.EventDao;
import statistics.cb.com.test.util.JsonUtil;

/**
 * 
 * 上传统计数据
 * 
 * @author Chengbiao
 * 
 */
public class UploadService extends Service{
	private List<EventBean> list;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v("statistics","Service->onCreate");
		if(isUpload()){
			upload(list);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v("statistics","Service->onBind");
		return null;
	}

	@Override
	public ComponentName startService(Intent service) {
		Log.v("statistics","Service->startService");
		return super.startService(service);
	}

	@Override
	public void onDestroy() {
		Log.v("statistics","Service->onDestroy");
		super.onDestroy();
	}

	private boolean isUpload(){
		if(list == null){
			list = new ArrayList<>();
		}else{
			list.clear();
		}

		/*** start 查询数据库确定需要上传的数据 **/
		list = EventDao.getInstance(this).queryEventList(5);
		/*** end 查询数据库确定需要上传的数据 **/

		if(list != null && list.size() >0){
			return true;
		}else{
			if(StatisticsHelper.isDebug){
				Log.v("statistics","service-->  服务的查询结果为空");
			}
			return false;
		}
	}

	private void upload(List<EventBean> list){
		if(StatisticsHelper.isDebug){
			Log.v("statistics","service-->  查询的数据长度" + list.size());
			Log.v("statistics","service-->  查询结果：" + JsonUtil.getInstances().beanToJson(list));
		}
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			System.out.println("线程异常...");
		}

		EventDao.getInstance(this).deleteData(5);
		upload(list);
	}
}
