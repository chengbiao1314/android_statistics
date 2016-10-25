package statistics.cb.com.test.helper;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import statistics.cb.com.test.bean.EventBean;

/**
 * 
 * 上传统计数据
 * 
 * @author Chengbiao
 * 
 */
public class UploadService extends Service{
	private EventDao dao;
	private List<EventBean> list;

	@Override
	public void onCreate() {
		super.onCreate();
		if(isUpload()){
			upload(list);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service->onBind");
		return null;
	}

	@Override
	public ComponentName startService(Intent service) {
		System.out.println("Service->startService");
		return super.startService(service);
	}

	@Override
	public void onDestroy() {
		System.out.println("Service->onDestroy");
		if (dao != null){
			dao.closeDB();
			dao = null;
		}
		super.onDestroy();
	}

	private boolean isUpload(){
		if(dao == null){
			dao = new EventDao(this);
		}

		if(list == null){
			list = new ArrayList<>();
		}else{
			list.clear();
		}

		/*** start 查询数据库确定需要上传的数据 **/
		EventBean event = new EventBean();
		list.add(event);


		/*** end 查询数据库确定需要上传的数据 **/

		if(list.size() >0){
			return true;
		}else{
			return false;
		}
	}

	private void upload(List<EventBean> list){
		System.out.println("数据条数..." + dao.getSize() + "___" + list.size());

		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			System.out.println("线程异常...");
		}

		upload(list);
	}
}
