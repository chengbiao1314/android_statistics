package statistics.cb.com.test.bean;

/**
 * Created by Ricky on 2016/10/24.
 */
public class EventBean {

    private Integer id; //  自生成id
    private String event_id; //  事件id...必填
    private String event_name; //  事件名称...
    private String event_type; //  事件类型...
    private String event_desc; //  事件描述...
    private String event_param; //  事件参数...选填
    private long time_stamp; //  时间戳...必填
    private String devices_model; //  设备型号...必填
    private String system_version; //  系统版本...必填
    private String devices_code; //  设备信息...
    private String Reserved_field;//  预留字段...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEvent_param() {
        return event_param;
    }

    public void setEvent_param(String event_param) {
        this.event_param = event_param;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getDevices_code() {
        return devices_code;
    }

    public void setDevices_code(String devices_code) {
        this.devices_code = devices_code;
    }

    public String getReserved_field() {
        return Reserved_field;
    }

    public void setReserved_field(String reserved_field) {
        Reserved_field = reserved_field;
    }

    public String getDevices_model() {
        return devices_model;
    }

    public void setDevices_model(String devices_model) {
        this.devices_model = devices_model;
    }

    public String getSystem_version() {
        return system_version;
    }

    public void setSystem_version(String system_version) {
        this.system_version = system_version;
    }
}
