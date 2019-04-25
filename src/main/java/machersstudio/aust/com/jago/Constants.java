package machersstudio.aust.com.jago;

import android.app.Dialog;
import android.media.Ringtone;
import android.os.PowerManager;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.List;

import timepackage.DatabaseHelper;
import timepackage.TimeTable;

/**
 * Created by user on 1/19/2018.
 */

public class Constants {
    public static String link="https:";
    public static int hour=00;
    public static int min=00;
    public static List<TimeTable> alarmlist=new ArrayList<TimeTable>();
    public static  int constantno=0;
    public static DatabaseHelper db;
    public static ArrayList<Alarm> alarmtunelist=new ArrayList<Alarm>();
    public static Dialog d;
    public static Ringtone r=null;
    public static Vibrator vr=null;

    public static PowerManager.WakeLock wll=null;
    public static boolean iswklknotrel=true;
}
