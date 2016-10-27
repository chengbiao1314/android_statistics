package statistics.cb.com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivityB extends Activity {
    private TextView tv_click;
    private TextView tv_click2;
    private TextView tv_click3;

    private  int id = 0;
    private  int idb = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainb);

        tv_click = (TextView) findViewById(R.id.tv_click);
        tv_click2 = (TextView) findViewById(R.id.tv_click2);
        tv_click3 = (TextView) findViewById(R.id.tv_click3);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                tv_click.setText("事件C点击了" + id);

                //事件统计
                StatisticsHelper.getInstance().AddEvent(MainActivityB.this ,"eventA"+id ,null);
            }
        });
        tv_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idb++;
                tv_click2.setText("事件D点击了" + idb);

                //事件统计
                StatisticsHelper.getInstance().AddEvent(MainActivityB.this ,"eventB"+idb ,null);
            }
        });
        tv_click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件统计
                Map<String, Object> map = new HashMap();
                map.put("param1b","11111b");
                map.put("param2b","22222b");
                map.put("param3b","33333b");
                StatisticsHelper.getInstance().AddEvent(MainActivityB.this ,"event_back_a"+idb ,map);

                finish();
            }
        });
    }
}
