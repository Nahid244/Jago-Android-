package timepackage;

/**
 * Created by user on 1/20/2018.
 */

public class TimeTable {
    private int Id;
    private int Hour;
    private int Min;

    public TimeTable(int id,int hour, int min) {
        Id=id;
        Hour = hour;
        Min = min;
    }
    public TimeTable(int hour, int min) {

        Hour = hour;
        Min = min;
    }

    public int getHour() {
        return Hour;
    }

    public int getMin() {
        return Min;
    }
}
