package statistics.cb.com.test.util;

import com.google.gson.Gson;

/**
 * Created by Ricky on 2016/10/26.
 */
public class JsonUtil {
    public static JsonUtil instances = null;

    public static JsonUtil getInstances(){
        if(instances == null){
            instances = new JsonUtil();
        }
        return instances;
    }

    public String beanToJson(Object bean){
        Gson gson =new Gson();
        return gson.toJson(bean);
    }
}
