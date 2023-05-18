package org.utarid;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorExample {

    public static void main(String[] args) {
        //thread havuzunda kac thread olusturulacagi ExecutorService ilklendirilirken belirlenir
//        Executor executor = Executors.newSingleThreadExecutor(); // tek bir thread olusturur
//        Executor executor = Executors.newFixedThreadPool(2); // belirli sayida thread olusturur
        Executor executor = Executors.newCachedThreadPool(); // gerektigi kadar thread olusturur

        executor.execute(new RunnableImplements(1));
        executor.execute(new RunnableImplements(50));
        executor.execute(new RunnableImplements(100));
    }

    public static class RunnableImplements implements Runnable {
        private final int start;

        public RunnableImplements(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            Printer.print(Thread.currentThread().getName(), start);
        }
    }
}
