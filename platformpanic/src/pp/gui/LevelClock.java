package pp.gui;

import pp.utilities.Timer;

public class LevelClock {
    private static final int MS_PER_MIN = 60000;
    private static final int MS_PER_SEC = 1000;
    private static final int MS_PER_CS = 10;
    private static Timer level_timer = new Timer();
    private static int minutes = 0;
    private static int seconds = 0;
    private static int hundredths = 0;
    private static boolean clock_paused = false;

    public static void initClock() {
        level_timer.initTimer();
        clock_paused = false;
    }

    public static void initClock(float start_time) {
        LevelClock.initClock();
        level_timer.setTt((long)(start_time * 1.0E9f));
    }

    public static void update() {
        long total_time = level_timer.getTtLong();
        level_timer.update();
        if (clock_paused) {
            level_timer.setTt(total_time);
        } else {
            LevelClock.calcUnits();
        }
    }

    public static void pauseClock(boolean value) {
        clock_paused = value;
    }

    public static void calcUnits() {
        long temptotal = level_timer.getTtLong() / 1000000;
        minutes = (int)(temptotal / MS_PER_MIN);
        seconds = (int)((temptotal -= (long)(minutes * MS_PER_MIN)) / MS_PER_SEC);
        hundredths = (int)((temptotal -= (long)(seconds * MS_PER_SEC)) / MS_PER_CS);
    }

    public static String getClockTime() {
        String formatclock = level_timer.getTtLong() < 0 ? String.format("-%02d:%02d:%02d", Math.abs(minutes), Math.abs(seconds), Math.abs(hundredths)) : String.format(" %02d:%02d:%02d", minutes, seconds, hundredths);
        return formatclock;
    }

    public static String getClockTime(long time) {
        if (time < 0) {
            return "TIMEERROR";
        }
        long temptotal = time / 1000000;
        int minutes = (int)(temptotal / MS_PER_MIN);
        int seconds = (int)((temptotal -= (long)(minutes * MS_PER_MIN)) / MS_PER_SEC);
        int hundredths = (int)((temptotal -= (long)(seconds * MS_PER_SEC)) / MS_PER_CS);
        String formatclock = String.format(" %02d:%02d:%02d", minutes, seconds, hundredths);
        return formatclock;
    }

    public static String getClockTimeMs(long milliseconds) {
        if (milliseconds < 0) {
            return "TIMEERROR";
        }
        int minutes = (int)(milliseconds / MS_PER_MIN);
        int seconds = (int)((milliseconds -= (long)(minutes * MS_PER_MIN)) / 1000);
        int hundredths = (int)((milliseconds -= (long)(seconds * 1000)) / 10);
        String formatclock = String.format(" %02d:%02d:%02d", minutes, seconds, hundredths);
        return formatclock;
    }

    public static long getClockTimeMs() {
        return level_timer.getTtLong() / 1000000;
    }

    public static long getBronzeTime(int level_num) {
        long return_value;
        try {
            return_value = Times.bronze_level[level_num];
        }
        catch (Exception e) {
            return_value = 0;
            //System.out.println("ERROR: A Bronze time for level " + level_num + " doesn't exist.");
        }
        return return_value;
    }

    public static long getSilverTime(int level_num) {
        long return_value;
        try {
            return_value = Times.silver_level[level_num];
        }
        catch (Exception e) {
            return_value = 0;
            //System.out.println("ERROR: A Silver time for level " + level_num + " doesn't exist.");
        }
        return return_value;
    }

    public static long getGoldTime(int level_num) {
        long return_value;
        try {
            return_value = Times.gold_level[level_num];
        }
        catch (Exception e) {
            return_value = 0;
            //System.out.println("ERROR: A Gold time for level " + level_num + " doesn't exist.");
        }
        return return_value;
    }

    public static class Times {
    	// NOTE: If a bronze_level time == 0, then its LevelToken's level_exist will be set false
        public static final long[] bronze_level = new long[]{15000, 30000, 32000, 20000, 75000,
        													 30000, 26000, 30000, 25000, 80000, 
        													 30000, 25000, 64000, 50000, 85000,    
        													 52000, 50000, 34000, 80000, 40000, 
        													 65000, 36000, 32000,144000, 22000, 
        													};
        public static final long[] silver_level = new long[]{11000, 22000, 20000, 15500, 45000,
        													 22000, 19000, 26000, 17000, 50000, 
        													 26000, 18000, 51000, 39000, 68000, 
        													 41000, 32000, 28000, 52000, 29000, 
        													 46000, 35000, 25000,120000, 21000, 
        													};
        public static final long[] gold_level   = new long[]{ 7750, 17000, 12000, 12200, 32000,
        													 17000, 13000, 21000, 13000, 32000,
        												 	 21000, 13000, 43000, 32000, 54000,
        												 	 30000, 24000, 21000, 34000, 23500,
        													 35000, 34500, 20000, 96000, 20000, 
        													};
    }

}

