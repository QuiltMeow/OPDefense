package ew.quilt.OPDefense.util;

public class TimerUtil {

    public static double tickToSecond(int tick) {
        return (double) tick / 20;
    }

    public static int secondToTick(int second) {
        return second * 20;
    }

    public static int minuteToTick(int minute) {
        return secondToTick(minute * 60);
    }

    public static int hourToTick(int hour) {
        return secondToTick(hour * 60 * 60);
    }
}
