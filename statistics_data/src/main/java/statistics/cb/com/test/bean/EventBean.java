package statistics.cb.com.test.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ricky on 2016/10/24.
 */
@DatabaseTable(tableName = "tb_event")
public class EventBean {

    @DatabaseField(generatedId = true)
    private Integer id; //  自生成id
//    @DatabaseField(id = true)
    @DatabaseField
    private String event_id; //  事件id
    @DatabaseField
    private String event_name; //  事件名称
    @DatabaseField
    private String event_type; //  事件类型
    @DatabaseField
    private String event_desc; //  事件描述
    @DatabaseField
    private String devices_code; //  设备信息
    @DatabaseField
    private int count; //  计数
    @DatabaseField
    private String Reserved_field;//  预留字段

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getDevices_code() {
        return devices_code;
    }

    public void setDevices_code(String devices_code) {
        this.devices_code = devices_code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReserved_field() {
        return Reserved_field;
    }

    public void setReserved_field(String reserved_field) {
        Reserved_field = reserved_field;
    }
}
