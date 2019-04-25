package machersstudio.aust.com.jago;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

public class newsActivity extends AppCompatActivity {
    private ImageButton homebut;
    private ImageView slidee;
    private Button prothomalobut;
    private Button jugantorbut;
    private Button bbcbut;


    private ClickTouch clickTouch=new ClickTouch();
    private SliderAnim slideranim=new SliderAnim();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        homebut=(ImageButton) findViewById(R.id.home3);
        clickTouch.setTouch(homebut);
        clickTouch.setClick(homebut,newsActivity.this,MainActivity.class);

        slidee = (ImageView) findViewById(R.id.slide2);
       slideranim.Anim(slidee);


        prothomalobut=(Button)findViewById(R.id.palo);
        clickTouch.setClick1(prothomalobut,"http://www.prothomalo.com/",newsActivity.this,webviewActivity.class);
        jugantorbut=(Button)findViewById(R.id.jugantor);
        clickTouch.setClick1(jugantorbut,"http://www.ejjdin.com/",newsActivity.this,webviewActivity.class);
        bbcbut=(Button)findViewById(R.id.bbc);
         clickTouch.setClick1(bbcbut,"http://www.bbc.com/bengali",newsActivity.this,webviewActivity.class);


    }

    public void onBackPressed(){
        this.startActivity(new Intent(newsActivity.this,MainActivity.class));
        finish();
    }
}
