package com.wjn.selectbox.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjn.selectbox.EventBusBean;
import com.wjn.selectbox.R;
import com.wjn.selectbox.utils.BooleanUtils;
import com.wjn.selectbox.utils.ExternalCallUtils;
import com.wjn.selectbox.utils.StringUtils;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView textView;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.activity_main_fatherlayout);
        textView=findViewById(R.id.activity_main_textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExternalCallUtils.initTime("MainActivity","2018","12","12",linearLayout,MainActivity.this);
            }
        });

        //注册EventBus
        EventBus.getDefault().register(this);

    }

    /**
     * onEventMainThread EventBus
     * */

    public void onEventMainThread(EventBusBean eventBusBean) {
        if (null != eventBusBean) {
            String updateActivity = eventBusBean.getUpdateActivity();
            if ("MainActivity".equals(updateActivity)) {//性别
                String updateVaule = eventBusBean.getUpdateVaule();
                if (!BooleanUtils.isEmpty(updateVaule)) {
                    time=StringUtils.getDoubleYearMonthDayString(updateVaule);
                    textView.setText(time);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//注销EventBus
    }
}
