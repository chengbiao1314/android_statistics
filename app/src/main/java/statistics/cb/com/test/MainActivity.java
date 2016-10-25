package statistics.cb.com.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView tv_click;

    private  int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //启动数据统计
        StatisticsHelper.getInstance().initStatistics(this);

        tv_click = (TextView) findViewById(R.id.tv_click);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                tv_click.setText("点击了" + id);

                //事件统计
                StatisticsHelper.getInstance().StatisticsEvent(MainActivity.this ,"event"+id ,null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        StatisticsHelper.getInstance().stopStatistics(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticsHelper.getInstance().onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatisticsHelper.getInstance().onResume(this);
    }
}
