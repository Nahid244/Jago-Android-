package machersstudio.aust.com.jago;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class prayerActivity extends AppCompatActivity {
     private ImageButton homebut;
    private ImageView slidee;


    private ClickTouch clickTouch=new ClickTouch();
    private SliderAnim slideranim=new SliderAnim();

    private AlarmConstants alarmConstants=new AlarmConstants();
    private Button activatebut;
    private Button deactivatebut;
    private ImageButton alarmstopbut;

    private TextView activationtxt;
    private TextView fajrtxt;
    private TextView zuhrtxt;
    private TextView asrtxt;
    private TextView maghribtxt;
    private TextView ishatxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        homebut=(ImageButton) findViewById(R.id.home2);
        clickTouch.setTouch(homebut);
        clickTouch.setClick(homebut,prayerActivity.this,MainActivity.class);

        slidee = (ImageView) findViewById(R.id.slide1);
        slideranim.Anim(slidee);
         alarmConstants.init();

        activatebut=(Button)findViewById(R.id.activate);
        deactivatebut=(Button)findViewById(R.id.deactivate);


        alarmstopbut=(ImageButton)findViewById(R.id.stop2);
        clickTouch.setTouch(alarmstopbut);


        activationtxt=(TextView)findViewById(R.id.activation);
        activationtxt.setText("Schedule");

        fajrtxt=(TextView)findViewById(R.id.fajr);
        zuhrtxt=(TextView)findViewById(R.id.zuhr);
        asrtxt=(TextView)findViewById(R.id.asr);
        maghribtxt=(TextView)findViewById(R.id.maghrib);
        ishatxt=(TextView)findViewById(R.id.isha);

        Calendar c=Calendar.getInstance();
       final int month=c.get(Calendar.MONTH);
        String sf=alarmConstants.prayerTimelist.get(month).getFajr();
        final String sf1=sf.substring(0,2);
        final String sf2=sf.substring(sf.length()-2);
        fajrtxt.setText("Fajr-"+sf1+":"+sf2);


        String sz=alarmConstants.prayerTimelist.get(month).getZuhr();
        final String sz1=sz.substring(0,2);
        final String sz2=sz.substring(sz.length()-2);
        zuhrtxt.setText("Zuhr-"+sz1+":"+sz2);

        String sa=alarmConstants.prayerTimelist.get(month).getAsr();
       final  String sa1=sa.substring(0,2);
       final String sa2=sa.substring(sa.length()-2);
        asrtxt.setText("Asr-"+sa1+":"+sa2);

        String sm=alarmConstants.prayerTimelist.get(month).getMaghrib();
        final String sm1=sm.substring(0,2);
        final String sm2=sm.substring(sm.length()-2);
        maghribtxt.setText("Maghrib-"+sm1+":"+sm2);


        String si=alarmConstants.prayerTimelist.get(month).getIsha();
        final String si1=si.substring(0,2);
        final String si2=si.substring(si.length()-2);
        ishatxt.setText("Isha-"+si1+":"+si2);

        final Alarm a1=new Alarm();
        final Alarm a2=new Alarm();
        final Alarm a3=new Alarm();
        final Alarm a4=new Alarm();
        final Alarm a5=new Alarm();

        activatebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(prayerActivity.this, "Activated",Toast.LENGTH_LONG).show();


                    a1.setAlarm(prayerActivity.this,month+13,Integer.parseInt(sf1),Integer.parseInt(sf2));
                    a2.setAlarm(prayerActivity.this,month+14,Integer.parseInt(sz1),Integer.parseInt(sz2));
                    a3.setAlarm(prayerActivity.this,month+15,Integer.parseInt(sa1),Integer.parseInt(sa2));
                    a4.setAlarm(prayerActivity.this,month+16,Integer.parseInt(sm1),Integer.parseInt(sm2));
                    a5.setAlarm(prayerActivity.this,month+17,Integer.parseInt(si1),Integer.parseInt(si2));
            }
        });
        deactivatebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(prayerActivity.this, "Deactivated",Toast.LENGTH_LONG).show();
                a1.cancelAlarm(prayerActivity.this,month+13);
                a2.cancelAlarm(prayerActivity.this,month+14);
                a3.cancelAlarm(prayerActivity.this,month+15);
                a4.cancelAlarm(prayerActivity.this,month+16);
                a5.cancelAlarm(prayerActivity.this,month+17);
            }
        });
        alarmstopbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constants.r!=null && Constants.vr!=null) {
                    Constants.r.stop();
                    Constants.vr.cancel();
                    if(Constants.iswklknotrel){
                        Constants.wll.release();
                        Constants.iswklknotrel=false;
                    }

                }
            }
        });


    }

    public void onBackPressed(){
        this.startActivity(new Intent(prayerActivity.this,MainActivity.class));
        finish();
    }
}
