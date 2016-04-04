package org.vaadin.petter.devday.bombs;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerBomb {

    public static final AtomicInteger timerCount = new AtomicInteger();

    public static void runTimer() {
        System.out.println(timerCount.incrementAndGet());
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runTimer();
            }
        }, 0, 500);
    }

    public static void main(String[] args) {
        runTimer();
    }
}
