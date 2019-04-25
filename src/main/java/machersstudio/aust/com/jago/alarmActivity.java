package machersstudio.aust.com.jago;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import timepackage.DatabaseHelper;
import timepackage.TimeTable;

public class alarmActivity extends AppCompatActivity {
    private ImageButton homebut;
    private ImageView slidee;
    private ImageButton addalarmbut;

    Button but;






    private ClickTouch clickTouch=new ClickTouch();
    private SliderAnim slideanim=new SliderAnim();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        homebut = (ImageButton) findViewById(R.id.home1);
       clickTouch.setTouch(homebut);
        clickTouch.setClick(homebut,alarmActivity.this,MainActivity.class);
        slidee = (ImageView) findViewById(R.id.slide);
        slideanim.Anim(slidee);

        addalarmbut=(ImageButton)findViewById(R.id.plus);
        clickTouch.setTouch(addalarmbut);




         final DatabaseHelper db=new DatabaseHelper(this);
        Constants.db=db;


        Constants.alarmlist=db.getAllalarm();




          ListView listView=(ListView)findViewById(R.id.list);
          List<String> timestring=new ArrayList<String>();

        for(int i=0;i<Constants.alarmlist.size();i++){

            String s1= String.valueOf(Constants.alarmlist.get(i).getHour());
            String s2=String.valueOf(Constants.alarmlist.get(i).getMin());
            if(Constants.alarmlist.get(i).getHour()<10){
                s1="0"+Constants.alarmlist.get(i).getHour();
            }

            if(Constants.alarmlist.get(i).getMin()<10){
                s2="0"+Constants.alarmlist.get(i).getMin();
            }

            String s=s1+" : "+s2;
            timestring.add(s);




        }

        final AlarmAdapter adapter=new AlarmAdapter(alarmActivity.this,timestring);
         listView.setAdapter(adapter);




         addalarmbut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final Dialog dialog = new Dialog(alarmActivity.this);
                 dialog.setTitle("Set Alarm");
                 dialog.setContentView(R.layout.dialog_add);
                 dialog.show();


                 Button addhourbut = (Button) dialog.findViewById(R.id.plushour);
                 Button addminbut = (Button) dialog.findViewById(R.id.plusmin);
                 final TextView alarmhrtxt = (TextView) dialog.findViewById(R.id.hr);
                 final TextView alarmmintxt = (TextView) dialog.findViewById(R.id.min);
                 Button minushourbut = (Button) dialog.findViewById(R.id.minushour);
                 Button minusminbut = (Button) dialog.findViewById(R.id.minusmin);
                 Button savealarmbut = (Button) dialog.findViewById(R.id.savealarm);

                 addhourbut.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Constants.hour = (Constants.hour + 1) % 24;
                         String s = Integer.toString(Constants.hour);
                         alarmhrtxt.setText(s);
                     }
                 });
                 minushourbut.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if (Constants.hour > 0) {
                             Constants.hour--;
                             String s = Integer.toString(Constants.hour);
                             alarmhrtxt.setText(s);

                         }
                     }
                 });
                 addminbut.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Constants.min = (Constants.min + 1) % 60;
                         String s = Integer.toString(Constants.min);
                         alarmmintxt.setText(s);
                     }
                 });
                 minusminbut.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if (Constants.min > 0) {
                             Constants.min--;
                             String s = Integer.toString(Constants.min);
                             alarmmintxt.setText(s);
                         }
                     }
                 });


                 savealarmbut.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dialog.cancel();
//

                         try {

                             db.addTimeTable(new TimeTable(Constants.hour, Constants.min));
                             Toast.makeText(alarmActivity.this,"Saved",Toast.LENGTH_LONG).show();


                             Alarm a=new Alarm();
                             a.setAlarm(alarmActivity.this,(Constants.hour+Constants.min),Constants.hour,Constants.min);

                             Constants.alarmtunelist.add(a);

                             Constants.hour=0;
                             Constants.min=0;

                             refreshUpdateAlarm();



                            adapter.notifyDataSetChanged();



//

                         } catch (Exception e) {

                         }
                     }
                 });

             }

         });
         ImageButton alarmringstop=(ImageButton)findViewById(R.id.stopp);
         clickTouch.setTouch(alarmringstop);
         alarmringstop.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(Constants.r!=null && Constants.vr!=null) {
                     Constants.r.stop();
                     Constants.vr.cancel();

                     if(Constants.iswklknotrel) {
                         Constants.wll.release();
                         Constants.iswklknotrel=false;
                     }
                 }
             }
         });







}

    public void onBackPressed(){
        this.startActivity(new Intent(alarmActivity.this,MainActivity.class));
        finish();
    }
    public void refreshUpdateAlarm(){
        Constants.alarmlist=Constants.db.getAllalarm();




        ListView listView=(ListView)findViewById(R.id.list);
        List<String> timestring=new ArrayList<String>();

        for(int i=0;i<Constants.alarmlist.size();i++){

            String s1= String.valueOf(Constants.alarmlist.get(i).getHour());
            String s2=String.valueOf(Constants.alarmlist.get(i).getMin());
            if(Constants.alarmlist.get(i).getHour()<10){
                s1="0"+Constants.alarmlist.get(i).getHour();
            }

            if(Constants.alarmlist.get(i).getMin()<10){
                s2="0"+Constants.alarmlist.get(i).getMin();
            }

            String s=s1+" : "+s2;
            timestring.add(s);
        }

        final AlarmAdapter adapter=new AlarmAdapter(alarmActivity.this,timestring);
        listView.setAdapter(adapter);
    }


}


