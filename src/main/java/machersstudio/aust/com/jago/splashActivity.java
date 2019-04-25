package machersstudio.aust.com.jago;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class splashActivity extends AppCompatActivity {

    private static int splash_time_out=70;
    ImageView splashView;
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashView=(ImageView)findViewById(R.id.splashview);





        try{
            BitmapDrawable frame1=(BitmapDrawable)getResources().getDrawable(R.drawable.splash1);
            BitmapDrawable frame2=(BitmapDrawable)getResources().getDrawable(R.drawable.splash2);
            BitmapDrawable frame3=(BitmapDrawable)getResources().getDrawable(R.drawable.splash3);
            anim=new AnimationDrawable();
            anim.addFrame(frame1,30);
            anim.addFrame(frame2,30);
            anim.addFrame(frame3,30);
            anim.setOneShot(false);
            splashView.setBackgroundDrawable(anim);
            new Handler().postDelayed(new Runnable() {

                @Override

                public void run() {

                    anim.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent splashIntent=new Intent(splashActivity.this,MainActivity.class);
                            startActivity(splashIntent);
                            finish();
                        }
                    },2000);


                }
            },splash_time_out);


        }catch (Exception e){

        }
    }
}
