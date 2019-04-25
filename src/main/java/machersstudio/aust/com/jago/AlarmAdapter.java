package machersstudio.aust.com.jago;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import timepackage.TimeTable;

/**
 * Created by user on 1/22/2018.
 */



public class AlarmAdapter extends ArrayAdapter<String> {

    private ClickTouch clickTouch=new ClickTouch();
    public List<String> list=new ArrayList<String>() ;

    Context context;
    public AlarmAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
       this.context=context;
       this.list=objects;
    }
    public View getView(int position,View convertView,ViewGroup parent){
        final String item=getItem(position);
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.customlayout,parent,false);
        }




        ImageButton button1=(ImageButton) convertView.findViewById(R.id.customimageview);
        final TextView textView=(TextView)convertView.findViewById(R.id.customtxt);
        ImageButton button=(ImageButton)convertView.findViewById(R.id.customsetbut);

        clickTouch.setTouch(button1);

        textView.setText(item);
         button.setTag(position);
         clickTouch.setTouch(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=item;
                String s1=s.substring(0,2);
                s.replace(":","0");
                String s2=item.substring(s.length()-2);

                int index= (int) view.getTag();


                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();

                try {
                    Constants.db.deleteAlarm(s1, s2);

                    int a = Constants.alarmlist.get(index).getHour();
                    int b = Constants.alarmlist.get(index).getMin();

                    Constants.alarmtunelist.get(index).cancelAlarm(context, (a + b));
                }catch (Exception e){

                }
                   list.remove(index);
                   notifyDataSetChanged();

            }
        });













        return convertView;

    }
}
