package machersstudio.aust.com.jago;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class emergencycallActivity extends AppCompatActivity {
    private ImageButton homebut;
    private ImageView slidee;
    private Button call999but;

    private ClickTouch clickTouch=new ClickTouch();
    private SliderAnim slideranim=new SliderAnim();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycall);

        homebut=(ImageButton) findViewById(R.id.home4);
        clickTouch.setTouch(homebut);
        clickTouch.setClick(homebut,emergencycallActivity.this,MainActivity.class);

        slidee = (ImageView) findViewById(R.id.slide3);
       slideranim.Anim(slidee);

       call999but=(Button)findViewById(R.id.call999);
       call999but.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String s="999";
               Intent callintent=new Intent(Intent.ACTION_CALL);
               callintent.setData(Uri.parse("tel:"+s));


               startActivity(callintent);
           }
       });



    }
    public void onBackPressed(){
        this.startActivity(new Intent(emergencycallActivity.this,MainActivity.class));
        finish();
    }
}
