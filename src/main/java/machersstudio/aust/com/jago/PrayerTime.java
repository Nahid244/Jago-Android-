package machersstudio.aust.com.jago;

/**
 * Created by user on 2/9/2018.
 */

public class PrayerTime {
    public String Fajr;
    public String Zuhr;
    public String Asr;
    public String Maghrib;
    public String Isha;



    public PrayerTime(String fajr, String zuhr, String asr, String maghrib, String isha) {
        Fajr = fajr;
        Zuhr = zuhr;
        Asr = asr;
        Maghrib = maghrib;

        Isha = isha;
    }
    public String getFajr() {
        return Fajr;
    }

    public String getZuhr() {
        return Zuhr;
    }

    public String getAsr() {
        return Asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public String getIsha() {
        return Isha;
    }
}
