package pp.utilities;

public class Timer {
    private long start_time = 0;
    private long current_time = 0;
    private long last_update_time = 0;
    private long delta_time = 0;
    private long total_time = 0;
    private boolean paused = false;

    public Timer() {
        this.initTimer();
    }

    public Timer(boolean start) {
        if (start) {
            this.initTimer();
        }
    }

    public void initTimer() {
        this.total_time = 0;
        this.delta_time = 0;
        this.current_time = this.start_time = System.nanoTime();
        this.last_update_time = this.start_time;
    }

    public void update() {
        this.last_update_time = this.current_time;
        this.current_time = System.nanoTime();
        if (this.paused) {
            return;
        }
        this.delta_time = this.current_time - this.last_update_time;
        this.total_time += this.delta_time;
    }

    public void pause(boolean value) {
        this.paused = value;
    }

    public float getDt() {
        return (float)this.delta_time / 1.0E9f;
    }

    public float getDtScaled() {
        return (float)(this.delta_time * 82) / 1.0E9f;
    }

    public double getDtDouble() {
        return (double)this.delta_time / 1.0E9;
    }

    public long getCtInNanos() {
        return this.current_time;
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public float getTt() {
        return (float)this.total_time / 1.0E9f;
    }

    public long getTtLong() {
        return this.total_time;
    }

    public void setTt(long nanoseconds) {
        this.total_time = nanoseconds;
    }

    public void setTtFloat(float seconds) {
        this.total_time = (long)(1.0E9f * seconds);
    }

    public void setCt(long nanoseconds) {
        this.current_time = nanoseconds;
    }
}

