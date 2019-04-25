package machersstudio.aust.com.jago;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageButton setAlarmBut;
    private ImageButton prayerTimeBut;
    private ImageButton newsPaperBut;
    private ImageButton emergencyCallBut;
    private ClickTouch clicktouch=new ClickTouch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlarmBut=(ImageButton) findViewById(R.id.setalarm);
       clicktouch.setTouch(setAlarmBut);
       clicktouch.setClick(setAlarmBut,MainActivity.this,alarmActivity.class);

        prayerTimeBut=(ImageButton) findViewById(R.id.prayer);
       clicktouch.setTouch(prayerTimeBut);
       clicktouch.setClick(prayerTimeBut,MainActivity.this,prayerActivity.class);

        newsPaperBut=(ImageButton) findViewById(R.id.papers);
        clicktouch.setTouch(newsPaperBut);
        clicktouch.setClick(newsPaperBut,MainActivity.this,newsActivity.class);


        emergencyCallBut=(ImageButton) findViewById(R.id.emergncy);
       clicktouch.setTouch(emergencyCallBut);
        clicktouch.setClick(emergencyCallBut,MainActivity.this,emergencycallActivity.class);





    }
    public void onBackPressed(){
       final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
       builder.setMessage("Are u sure want to exit?");
       builder.setCancelable(true);
       builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.cancel();
           }
       });
       builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {

                 finish();
           }
       });
       AlertDialog alertdialog=builder.create();
       alertdialog.show();
    }
}
