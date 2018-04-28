package newwater.com.newwater.utils;

import android.content.Context;
import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class TimeRun {
    public TimeRun(Context context, String timestring, final Handler handler,final long loopjiange,final int what){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(what);
            }
        };

        Timer timer = new Timer(true);
        Date time = TimeUtils.strToDateLong(timestring);
        timer.schedule(task,time,loopjiange);
    }
}
