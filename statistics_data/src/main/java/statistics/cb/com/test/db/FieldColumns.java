package statistics.cb.com.test.db;

/**
 * author ：ChengBiao
 *
 * desc ：创建表格的固定参数
 */
public abstract class FieldColumns {
	//主要为了注意每个字段之间的空格
	protected final static String SQL_CREATE = "create table if not exists ";
	protected final static String INTEGER_INCREMENT = " integer auto_increment,";
	protected final static String TEXT = " TEXT,";
//	protected final static String INTEGER = " integer,";
	protected final static String TIMESTAMP = " timestamp,";
	protected final static String TEXT_END= " TEXT";

	protected final static String ID = "id";

	public final static String TableName_Event = "tb_event";  //事件的id......必填
	public final static String Event_id = "event_id";  //事件的id......必填
	public final static String Event_name = "event_name";  //事件的名称
	public final static String Event_type = "event_type";  //事件的类型
	public final static String Event_desc = "event_desc";  //事件的描述
	public final static String Event_param = "event_param";  //事件的参数......选填
	public final static String Time_Stamp = "time_stamp";  //时间戳...必填
	public final static String Devices_model = "devices_model";  //设备的型号...必填
	public final static String System_Version = "system_version";  //系统的版本...必填
	public final static String Devices_code = "devices_code";  //设备的信息
	public final static String Reserved_Field = "reserved_field";  //预留字段

	/**
	 * table tb_event
	 * @return
     */
	public static String CreateEventTable(){
		return SQL_CREATE + TableName_Event + "(" +ID +INTEGER_INCREMENT
				+ Event_id + TEXT
				+ Event_name + TEXT
				+ Event_type + TEXT
				+ Event_desc + TEXT
				+ Event_param + TEXT
				+ Time_Stamp + TIMESTAMP
				+ Devices_model + TEXT
				+ System_Version + TEXT
				+ Devices_code + TEXT
				+ Reserved_Field + TEXT_END
				+ ")";
	}
}
