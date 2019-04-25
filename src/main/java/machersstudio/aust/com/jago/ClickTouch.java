package machersstudio.aust.com.jago;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by user on 1/18/2018.
 */

public class ClickTouch {


    public void setTouch(ImageButton but){

        but.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        view.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        view.getBackground().clearColorFilter();
                        break;
                    }
                }

                return false;
            }
        });
    }
    public void setClick(ImageButton but, final Context context, final Class<?extends Activity> activity){
        but.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                context.startActivity(new Intent(context,activity));
                ((Activity)context).finish();
            }
        });
    }
    public void setClick1(final Button but,final String s,final Context context, final Class<?extends Activity> activity){
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.link=s;
                but.setBackgroundColor(0xe0f47521);
                context.startActivity(new Intent(context,activity));

                ((Activity)context).finish();
            }
        });
    }

}
