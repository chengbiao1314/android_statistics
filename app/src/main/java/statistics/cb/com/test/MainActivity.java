package statistics.cb.com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private TextView tv_click;
    private TextView tv_click2;
    private TextView tv_click3;

    private  int id = 0;
    private  int idb = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_click = (TextView) findViewById(R.id.tv_click);
        tv_click2 = (TextView) findViewById(R.id.tv_click2);
        tv_click3 = (TextView) findViewById(R.id.tv_click3);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                tv_click.setText("事件A点击了" + id);

                //事件统计
                StatisticsHelper.getInstance().AddEvent(MainActivity.this ,"eventA"+id ,null);
            }
        });
        tv_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idb++;
                tv_click2.setText("事件B点击了" + idb);

                //事件统计
                StatisticsHelper.getInstance().AddEvent(MainActivity.this ,"eventB"+idb ,null);
            }
        });
        tv_click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件统计
                Map<String, Object> map = new HashMap();
                map.put("param1","11111");
                map.put("param2","22222");
                map.put("param3","33333");
                StatisticsHelper.getInstance().AddEvent(MainActivity.this ,"event_to_b"+idb ,map);

                Gson gson = new Gson();
                System.out.println(gson.toJson(map));

                Intent intent = new Intent(MainActivity.this,MainActivityB.class);
                startActivity(intent);
            }
        });
    }
}
