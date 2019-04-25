package machersstudio.aust.com.jago;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 1/20/2018.
 */

public class Alarm extends BroadcastReceiver {

    private static PowerManager.WakeLock wl;

    @Override
    public void onReceive(final Context context, Intent intent) {

        if(wl!=null){
            wl.release();
        }

        PowerManager pm=(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        wl=pm.newWakeLock(PowerManager.FULL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP|PowerManager.ON_AFTER_RELEASE,"");
        Constants.wll=wl;
        wl.acquire();

        long ringDelay=60000;

        Ringtone defaultring= RingtoneManager.getRingtone(context,Settings.System.DEFAULT_RINGTONE_URI);
        Uri currentRingtoneuri=RingtoneManager.getActualDefaultRingtoneUri(context.getApplicationContext(),RingtoneManager.TYPE_ALARM);
        final Ringtone currentRingtone=RingtoneManager.getRingtone(context,currentRingtoneuri);
        Constants.r=currentRingtone;

        currentRingtone.play();
        try {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    currentRingtone.stop();
                   if(Constants.iswklknotrel){
                       wl.release();
                       Constants.iswklknotrel=false;
                   }
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, ringDelay);
        }
        catch (Exception e){

        }

        Toast.makeText(context,"Alert",Toast.LENGTH_LONG).show();
        Vibrator v= (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        Constants.vr=v;
        v.vibrate(60000);
    }
    public void setAlarm(Context context,int id,int hour,int min){
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        Intent i=new Intent(context,Alarm.class);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,min);
        calendar.set(Calendar.SECOND,0);

        if(calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,id,i,0);
        // alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        // alarmManager.setRepeating();
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
    public void cancelAlarm(Context context ,int a){
        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(context.getApplicationContext(),Alarm.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context.getApplicationContext(),a,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }
}
