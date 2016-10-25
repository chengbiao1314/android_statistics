package statistics.cb.com.test.helper;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import statistics.cb.com.test.bean.EventBean;

/**
 * Created by dabing on 16/3/29.
 */
public class EventDao {
    private Dao dao;
    private StatisticsDaoHelper helper;
    private Context context;

    public EventDao(Context context) {
        this.context = context;
        try {
            helper = StatisticsDaoHelper.getStatisticsDaoHelperInstance(context);
            System.out.println("statistics database path :" + helper.getReadableDatabase().getPath());
            dao = helper.getDao(EventBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<EventBean> querySQL(String sql) {
        List<EventBean> results = new ArrayList<EventBean>();
        try {
            GenericRawResults<EventBean> rawResults = dao.queryRaw(sql, new RawRowMapper<EventBean>() {
                @Override
                public EventBean mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
                    EventBean eventBean = new EventBean();
//                    eventBean.setAvatar(resultColumns[0]);
//                    eventBean.setBirthday(Strings.parseDate(resultColumns[1]));
//                    eventBean.setCode(resultColumns[2]);
                    return eventBean;
                }
            });
            Iterator<EventBean> iterator = rawResults.iterator();
            while (iterator.hasNext()) {
                EventBean eventBean = iterator.next();
                results.add(eventBean);
            }
            rawResults.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return results;
    }

    public List<EventBean> queryByLevelAndCompanyCode(int level, String companyCode) {
        String sql;
        if (level == 0) {
            sql = String.format("select * from tb_event where companyCode = '%s' and status = '%s' and demonstration = '%s'",
                    companyCode);
        } else {
            sql = String.format("select * from tb_event where level = %d and companyCode = '%s' and status = '%s' and demonstration = '%s'",
                    level, companyCode);
        }
        Log.e("SQL:", sql);
        return querySQL(sql);
    }

    public List<EventBean> queryByLevelAndShopCode(int level, String shopCode) {
        String sql;
        if (level == 0) {
            sql = String.format("select * from tb_event where shopCode = '%s' and status = '%s' and demonstration = '%s'",
                    shopCode);
        } else {
            sql = String.format("select * from tb_event where level = %d and shopCode = '%s' and status = '%s' and demonstration = '%s'",
                    level, shopCode);
        }
        Log.e("SQL:", sql);
        return querySQL(sql);
    }

    public int getSize() {
        List<EventBean> lst = querySQL("select * from tb_event");
        return lst.size();
    }

    public void add(EventBean eventBean) {
        try {
            dao.createOrUpdate(eventBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        try {
            List<EventBean> lst = querySQL("select * from tb_event");
            if (null != lst && !lst.isEmpty()) {
                dao.delete(lst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        helper.close();
    }

}
