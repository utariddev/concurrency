package org.utarid;

import org.utarid.ThreadExample.ThreadImplements;

public class ThreadExample {

    public static void main(String[] args) {
//        Thread thread1 = new ThreadExtends(1);
//        Thread thread2 = new ThreadExtends(50);
//        Thread thread3 = new ThreadExtends(100);

        Thread thread1 = new Thread(new ThreadImplements(1));
        Thread thread2 = new Thread(new ThreadImplements(50));
        Thread thread3 = new Thread(new ThreadImplements(100));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static class ThreadExtends extends Thread {

        private final int start;

        public ThreadExtends(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            Printer.print(Thread.currentThread().getName(), start);
        }
    }

    public static class ThreadImplements implements Runnable {
        private final int start;

        public ThreadImplements(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            Printer.print(Thread.currentThread().getName(), start);
        }
    }
}
