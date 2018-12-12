package com.wjn.selectbox.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wjn on 2017/11/22.
 * 字符串工具类
 */

public class StringUtils {

    /**
     * 去除EditText中的空格 任意位置
     * */

    public static String removeSpaceEditText(String oldstr){
        String newsstr="";
        newsstr=oldstr.replaceAll(" ", "");
        return newsstr;
    }

    /**
     * 判断两个时间字符串的大小
     * */

    public  static boolean isTimeIntervalIsRight(String beforetime, String aftertime){
        boolean result=false;
        if(!BooleanUtils.isEmpty(beforetime)&&!BooleanUtils.isEmpty(aftertime)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long beforetimemillionSeconds=0;
            long aftertimemillionSeconds=0;
            try {
                beforetimemillionSeconds = sdf.parse(beforetime).getTime();//毫秒
                aftertimemillionSeconds = sdf.parse(aftertime).getTime();//毫秒
                if(aftertimemillionSeconds>=beforetimemillionSeconds){
                    result=true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 去除字符串中的空格 回车键 制表符
     * */

    public static String replaceBlank(String str) {
        String dest = "";
        if (!BooleanUtils.isEmpty(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 根据图片原图路径获取缩略图路径
     * */

    public static String getThumbPath(String avatar){
        if(!BooleanUtils.isEmpty(avatar)){
            if(avatar.endsWith(".jpg")){
                avatar=avatar+".thumb.jpg";
            }else if(avatar.endsWith(".png")){
                avatar=avatar+".thumb.png";
            }
        }
        return avatar;
    }

    /**
     * 将一个长字符串每隔3个字符隔开
     * */

    public static String interceptionString(String context){
        String result="";
        if(!BooleanUtils.isEmpty(context)){
            result=context;
            StringBuffer stringBuffer=new StringBuffer(context);
            int index;
            for(index=3;index<stringBuffer.length();index+=4){
                stringBuffer.insert(index, '\r');
            }
            if(!BooleanUtils.isEmpty(stringBuffer.toString())){
                result=stringBuffer.toString();
            }
        }

        return result;
    }

    /**
     * 时间转换成毫秒数
     * */

    public static long getTimeToNum(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long millionSeconds = 0;
        try {
            if ("".equals(time) || "null".equals(time) || null == time) {

            } else {
                millionSeconds = sdf.parse(time).getTime();//毫秒
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millionSeconds;
    }

    /**
     * 截取时间后六位 2018-3-8 —>180308
     * */

    public static String getAfterSixStringByTime(String time) {
        if(BooleanUtils.isEmpty(time)){
            return "";
        }else{
            String str[]=time.split("-");
            if(null!=str&&str.length==3){
                String result="";
                String year=str[0];//年份 2018
                String month=str[1];//月份 3
                String day=str[2];//日 8
                if(!BooleanUtils.isEmpty(year)&&!BooleanUtils.isEmpty(month)&&!BooleanUtils.isEmpty(day)&&year.length()==4){
                    year=year.substring(2);
                    if(month.length()==1){
                        month="0"+month;
                    }
                    if(day.length()==1){
                        day="0"+day;
                    }
                    result=year+month+day;
                }
                return result;
            }else{
                return "";
            }
        }

    }

    /**
     * 将 2018 —>18
     * */

    public static String getYearByString(String str){
        String result="2018";
        if(!BooleanUtils.isEmpty(str)&&str.length()==4){
            result=str.substring(2);
        }
        return result;
    }

    /**
     * 将 2018-04-04 截取 2018 04 04
     * */

    public static String[] getYearMonthAndDay(String str){
       String[] result=new String[]{"","",""};
       if(!BooleanUtils.isEmpty(str)){
           String s[]=str.split("-");
           if(null!=s&&s.length==3){
               result[0]=s[0];
               result[1]=s[1];
               result[2]=s[2];
           }
       }
       return result;
    }

    /**
     * 将 2018-04-04 截取 2018 04 04
     * */

    public static String getDoubleYearMonthDayString(String str){
        String result="";
        if(!BooleanUtils.isEmpty(str)){
            String string[]=str.split("-");
            if(null!=string&&string.length==3){
                String year=string[0];
                String month=string[1];
                if(!BooleanUtils.isEmpty(month)){
                    if(month.length()==1){
                        month="0"+month;
                    }
                }
                String day=string[2];
                if(!BooleanUtils.isEmpty(day)){
                    if(day.length()==1){
                        day="0"+day;
                    }
                }
               result=year+"-"+month+"-"+day;
            }
        }
        return result;
    }

    public static String getTimeByLongTime(String time){
        String result="";
        if(!BooleanUtils.isEmpty(time)){
            if(BooleanUtils.isNum(time)){
                long l= Long.parseLong(time);
                if(l>0){
                    Date date = new Date(l);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    result=format.format(date);
                }else{
                    result=time;
                }
            }else{
                result=time;
            }
        }
        return result;
    }

}
