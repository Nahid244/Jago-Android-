package timepackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="alarmtimedb";
    private static final String TABLE_NAME="alarmtime_tabledb";
    private static final String COL_1="ID";
    private static final String COL_2="HOUR";
    private static final String COL_3="MIN";




    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,HOUR INTEGER,MIN INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addTimeTable(TimeTable timeTable)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO alarmtime_table.db(HOUR,MIN)" +
                "VALUES('"+timeTable.getHour()+"','"+timeTable.getMin()+"')";



        ContentValues value=new ContentValues();
       // value.put(COL_2,timeTable.getHour());
        value.put(COL_2,timeTable.getHour());
        value.put(COL_3,timeTable.getMin());

        db.insert(TABLE_NAME, null,value);

        db.close();

    }
    public List<TimeTable> getAllalarm()
    {
        List<TimeTable> mycontactList=new ArrayList<TimeTable>();

        String selectquery="SELECT * FROM "+ TABLE_NAME;// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                TimeTable timetable= new TimeTable(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)));
                mycontactList.add(timetable);
            }while(cursor.moveToNext());
        }

        return mycontactList;
    }
    public void deleteContact(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
      // String query = "DELETE From "+TABLE_NAME+" WHERE  ID="+id;
        String query = "DELETE From "+TABLE_NAME+" WHERE  ID="+id;
        db.execSQL(query);


        db.close();
    }
    public void deleteAlarm(String s1,String s2)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        // String query = "DELETE From "+TABLE_NAME+" WHERE  ID="+id;
       /// String query = "DELETE From "+TABLE_NAME+" WHERE  HOUR="+s1;
        String query= "DELETE FROM " + TABLE_NAME + " WHERE " + COL_2 + "='" + s1 + "'" + " AND " + COL_3 + " = '" + s2 + "'";
        db.execSQL(query);


        db.close();
    }
    public void updateAlarm(TimeTable timeTable,String s1,String s2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value=new ContentValues();

        value.put(COL_2,timeTable.getHour());
        value.put(COL_3,timeTable.getMin());

        db.update(TABLE_NAME,value, COL_2 + " = ? AND " + COL_3 + " = ? ", new String[]{s1,s2});
        db.close();

    }

}
