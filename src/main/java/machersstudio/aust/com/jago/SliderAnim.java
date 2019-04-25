package machersstudio.aust.com.jago;

import android.os.Handler;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 1/18/2018.
 */

public class SliderAnim {

    private  Runnable mR;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private float initpos;


    public void Anim(final ImageView slidee){
        initpos=slidee.getY();

        try {
            mR = new Runnable() {
                @Override
                public void run() {
                    changePos(slidee);
                }
            };

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(mR);

                }
            }, 0, 8);
        }catch(Exception e){

        }

    }
    public void changePos(ImageView slidee) {

        if(slidee.getY()>initpos-700) {
            slidee.setY(slidee.getY() - 4);
        }
        else{
            handler.removeCallbacks(mR);
        }

    }
}
