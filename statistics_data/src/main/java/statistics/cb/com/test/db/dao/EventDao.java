package statistics.cb.com.test.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import statistics.cb.com.test.StatisticsHelper;
import statistics.cb.com.test.bean.EventBean;
import statistics.cb.com.test.db.DBManager;
import statistics.cb.com.test.db.FieldColumns;
import statistics.cb.com.test.util.JsonUtil;

/**
 * Created by Ricky on 2016/10/26.
 */
public class EventDao {

    private Context context;
    private static EventDao instances;

    private EventDao(Context context) {
        this.context = context;
    }

    public static EventDao getInstance(Context context) {
        if (null == instances) {
            synchronized (EventDao.class) {
                if (null == instances) {
                    instances = new EventDao(context);
                }
            }
        }
        return instances;
    }

    /**
     * 查询统计数据表
     * @param num 需要查询的条数
     * @return
     */
    public List<EventBean> queryEventList(int num) {
        String sql = "select * from "+ FieldColumns.TableName_Event
                +" where "+ FieldColumns.Time_Stamp + "<? limit "+ num +" offset 0";
        SQLiteDatabase database = DBManager.getInstance(context).getDatabase();
        Cursor cursor = null;
        EventBean sData = null;
        List<EventBean> list = null;
        try {
            cursor = database.rawQuery(sql ,new String[]{StatisticsHelper.indextime +""});
            if (cursor != null && cursor.getCount() > 0) {
                list = new ArrayList<EventBean>();
                while (cursor.moveToNext()) {
                    sData = new EventBean();
                    sData.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    sData.setEvent_id(cursor.getString(cursor.getColumnIndex(FieldColumns.Event_id)));
                    sData.setEvent_name(cursor.getString(cursor.getColumnIndex(FieldColumns.Event_name)));
                    sData.setEvent_desc(cursor.getString(cursor.getColumnIndex(FieldColumns.Event_desc)));
                    sData.setEvent_type(cursor.getString(cursor.getColumnIndex(FieldColumns.Event_type)));
                    sData.setEvent_param(cursor.getString(cursor.getColumnIndex(FieldColumns.Event_param)));
                    sData.setTime_stamp(cursor.getLong(cursor.getColumnIndex(FieldColumns.Time_Stamp)));
                    sData.setDevices_model(cursor.getString(cursor.getColumnIndex(FieldColumns.Devices_model)));
                    sData.setSystem_version(cursor.getString(cursor.getColumnIndex(FieldColumns.System_Version)));
                    sData.setDevices_code(cursor.getString(cursor.getColumnIndex(FieldColumns.Devices_code)));
                    sData.setReserved_field(cursor.getString(cursor.getColumnIndex(FieldColumns.Reserved_Field)));
                    list.add(sData);
                }
            }
        } catch (Exception e) {
            if (StatisticsHelper.isDebug){
                Log.v("statistics","EventDao..." + "查询数据异常..." + e.toString());
            }
            sData = null;
            list = null;
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public void deleteData(int num){
        String sql = "delete from " + FieldColumns.TableName_Event + " where "+ FieldColumns.Time_Stamp +" in"
                +  "(select "+ FieldColumns.Time_Stamp +" from "+ FieldColumns.TableName_Event
                +" where "+ FieldColumns.Time_Stamp + "<"+ StatisticsHelper.indextime +" limit "+ num +" offset 0)";
        SQLiteDatabase database = DBManager.getInstance(context).getDatabase();
        database.execSQL(sql);
    }

    /**
     * 插入一行数据
     *
     * @param eventBean
     *
     */
	public void insertData(EventBean eventBean) {
        if(eventBean == null){
            return;
        }

        SQLiteDatabase database = DBManager.getInstance(context).getDatabase();

        ContentValues valus = new ContentValues();
        valus.put(FieldColumns.Event_id,eventBean.getEvent_id());
        valus.put(FieldColumns.Event_name,eventBean.getEvent_name());
        valus.put(FieldColumns.Event_desc,eventBean.getEvent_desc());
        valus.put(FieldColumns.Event_type,eventBean.getEvent_type());
        valus.put(FieldColumns.Event_param,eventBean.getEvent_param());
        valus.put(FieldColumns.Time_Stamp,eventBean.getTime_stamp());
        valus.put(FieldColumns.Devices_model,eventBean.getDevices_model());
        valus.put(FieldColumns.System_Version,eventBean.getSystem_version());
        valus.put(FieldColumns.Devices_code,eventBean.getDevices_code());
        valus.put(FieldColumns.Reserved_Field,eventBean.getReserved_field());

        database.insert(FieldColumns.TableName_Event,null,valus);

        if (StatisticsHelper.isDebug){
            Log.v("statistics","EventDao-->  查询结果：" + JsonUtil.getInstances().beanToJson(EventDao.getInstance(context).queryEventList(5)));
//            deleteData(5);
        }
    }
}
