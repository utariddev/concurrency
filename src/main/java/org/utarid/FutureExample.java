package org.utarid;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //ayri bir threadde gorev baslatiliyor
        Future<Integer> future = executorService.submit(new CallableImplements(1));

        //gorevin bitip bitmedigi kontrolu
        if (future.isDone()) System.out.println("task is completed");
        else System.out.println("task is not completed yet");

        //thread isini bitirdiginde donus degeri buradan okunur
        int result = future.get();

        //gorevin bitip bitmedigi kontrolu
        if (future.isDone()) System.out.println("task is completed");
        else System.out.println("task is not completed yet");

        System.out.println("task result: " + result);

        executorService.shutdown();
    }

    public static class CallableImplements implements Callable<Integer> {
        private final int start;

        public CallableImplements(int start) {
            this.start = start;
        }

        @Override
        public Integer call() throws Exception {
            Printer.print(Thread.currentThread().getName(), start);
            return start;//future.get() metodundan okunacak deger
        }
    }
}
