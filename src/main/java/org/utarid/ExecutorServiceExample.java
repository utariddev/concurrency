package org.utarid;

import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //thread havuzunda kac thread olusturulacagi ExecutorService ilklendirilirken belirlenir
//        ExecutorService executor = Executors.newSingleThreadExecutor(); // tek bir thread olusturur
//        ExecutorService executor = Executors.newFixedThreadPool(2); // belirli sayida thread olusturur
        ExecutorService executor = Executors.newCachedThreadPool(); // gerektigi kadar thread olusturur

        //threadden veri okunmak isteniyorsa submit() ve Callable arayuzu kullanilmali
        Future<Integer> future1 = executor.submit(new CallableImplements(1));
        Future<Integer> future2 = executor.submit(new CallableImplements(50));
        Future<Integer> future3 = executor.submit(new CallableImplements(100));

        System.out.println("result1: " + future1.get());
        System.out.println("result2: " + future2.get());
        System.out.println("result3: " + future3.get());


        //threadden veri okunmaycaksa submit() veya execute() metodlari Runnable arayuzu ile kullanilir
//        executor.execute(new RunnableImplements(1));
//        executor.execute(new RunnableImplements(50));
//        executor.execute(new RunnableImplements(100));


        //zamanlanmis gorevler icin ScheduledExecutorService sinifi kullanilir
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(); // tek bir thread olusturur
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3); // belirli sayida thread olusturur

//        executor.schedule(new RunnableImplements(1), 1, TimeUnit.SECONDS); //Runnable kullanilirsa geriye veri alinamaz
//        ScheduledFuture<Integer> future2 = executor.schedule(new CallableImplements(50), 1, TimeUnit.SECONDS); //Callable kullanilirsa geriye veri alinabilir
//        ScheduledFuture<Integer> future3 = executor.schedule(new CallableImplements(100), 1, TimeUnit.SECONDS);
//        System.out.println("result2: " + future2.get());
//        System.out.println("result3: " + future3.get());

//        ScheduledFuture<?> future4 = executor.scheduleAtFixedRate(new RunnableImplements(100), 1,3, TimeUnit.SECONDS);
//        executor.schedule(() -> {
//            future4.cancel(true);
//            executor.shutdown();
//        }, 10, TimeUnit.SECONDS); //10 saniye sonra sonlandırılıyor

        executor.shutdown();
    }

    public static class CallableImplements implements Callable<Integer> {
        private final int start;

        public CallableImplements(int start) {
            this.start = start;
        }

        @Override
        public Integer call() throws Exception {
            Printer.print(Thread.currentThread().getName(), start);
            return start;
        }
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
