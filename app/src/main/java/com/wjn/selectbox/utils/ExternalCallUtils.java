package com.wjn.selectbox.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wjn.selectbox.EventBusBean;
import com.wjn.selectbox.R;
import com.wjn.selectbox.view.ChangeDatePopwindow;

import java.util.List;

import de.greenrobot.event.EventBus;

public class ExternalCallUtils {

    /**
     * 外部调用显示日期
     * */

    public static void initTime(final String Activityname, String year, String month, String day, View view, Context context){
        if(null!=view&&null!=context){
            ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(context);
            mChangeBirthDialog.setDate(year, month, day);
            mChangeBirthDialog.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

                @Override
                public void onClick(String year, String month, String day) {
                    String time = year + "-" + month + "-" + day;
                    //发送EventBus通知 通知相关页面刷新时间
                    EventBusBean eventBusBean=new EventBusBean();
                    eventBusBean.setUpdateActivity(Activityname);
                    eventBusBean.setUpdateVaule(time);
                    EventBus.getDefault().post(eventBusBean);
                }
            });
        }
    }

}
