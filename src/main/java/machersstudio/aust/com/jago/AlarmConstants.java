package machersstudio.aust.com.jago;

import java.util.ArrayList;

/**
 * Created by user on 2/9/2018.
 */

public class AlarmConstants {
    public ArrayList<PrayerTime> prayerTimelist=new ArrayList<PrayerTime>();



    public void init(){
        prayerTimelist.add(new PrayerTime("0523","1212","1609","1740","1902"));
        prayerTimelist.add(new PrayerTime("0521","1213","1623","1800","1916"));

        //prayerTimelist.add(new PrayerTime("1043","1547","0423","0600","0716"));




        prayerTimelist.add(new PrayerTime("0505","1204","1630","1813","1930"));
        prayerTimelist.add(new PrayerTime("0434","1203","1632","1825","1947"));
        prayerTimelist.add(new PrayerTime("0404","1156","1636","1840","2007"));
        prayerTimelist.add(new PrayerTime("0347","1202","1642","1850","2017"));
        prayerTimelist.add(new PrayerTime("0404","1205","1643","1852","2017"));
        prayerTimelist.add(new PrayerTime("0421","1205","1643","1840","2004"));
        prayerTimelist.add(new PrayerTime("0434","1159","1629","1815","1934"));
        prayerTimelist.add(new PrayerTime("0447","1149","1607","1735","1902"));
        prayerTimelist.add(new PrayerTime("0502","1147","1543","1723","1957"));
        prayerTimelist.add(new PrayerTime("0519","1202","1546","1725","1943"));


    }


}
