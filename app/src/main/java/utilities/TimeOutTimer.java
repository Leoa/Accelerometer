package utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Leondria on 1/14/2015.
 */
public class TimeOutTimer {
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
  public String strDate;

    public void TimeOutTimer(){}




    public void initializeTimerTask() {
 Log.v("Timer tast running  "," timerOutTimer");

       timerTask = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp

                handler.post(new Runnable() {
                     public void run() {
                        //get the current timeStamp
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        strDate = simpleDateFormat.format(calendar.getTime());

                    }
                });
            }
        };
    }


    public void startTimer(){
        Log.v("***************************************", "timer tast started ");
       timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask,5000,1000);
    }

    public void stopTimerTask(Context ctx){

       if(timer !=null){
            timer.cancel();
           timer=null;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ctx, strDate, duration);
           toast.show();
        }

    }

}

