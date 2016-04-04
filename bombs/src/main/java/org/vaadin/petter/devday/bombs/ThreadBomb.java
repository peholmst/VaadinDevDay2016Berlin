package org.vaadin.petter.devday.bombs;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadBomb {

    public static final AtomicInteger threadCount = new AtomicInteger();

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(threadCount.incrementAndGet());
            new MyThread().start();
            try {
                Thread.sleep(100000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
