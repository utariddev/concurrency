package org.utarid;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        testSupplyAsync();
        testRunAsync();
        testThenApply();
        testThenAccept();
        testThenCombine();
        testExceptionally();
    }

    public static void testExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("error occurred");
        });
        CompletableFuture<Integer> handledFuture = future.exceptionally(throwable -> {
            System.out.println("error occurred inside thread : " + throwable.getMessage());
            return -1;
        });
        Integer result = handledFuture.get();
        System.out.println(result);
    }

    public static void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new SupplierImplements(1));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new SupplierImplements(50));
        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + result2);
        Integer result2 = combinedFuture.get();
        System.out.println(result2);
    }

    public static void testThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new SupplierImplements(1));
        CompletableFuture<Void> newFuture = future.thenAccept(result -> System.out.println("result: " + result));
        newFuture.get();
    }

    public static void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new SupplierImplements(1));
        CompletableFuture<String> newFuture = future.thenApply(result -> "result is " + result);
        String result2 = newFuture.get();
        System.out.println(result2);
    }

    public static void testRunAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new RunnableImplements(1));
        future.get();
    }

    public static void testSupplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new SupplierImplements(1));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new SupplierImplements(50));

        //join ve get birbirinin yerine kullanilabilir
        Integer result1 = future1.join();
        Integer result2 = future2.get();

        System.out.println("task result 1: " + result1);
        System.out.println("task result 2: " + result2);
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

    public static class SupplierImplements implements Supplier<Integer> {
        private final int start;

        public SupplierImplements(int start) {
            this.start = start;
        }


        @Override
        public Integer get() {
            Printer.print(Thread.currentThread().getName(), start);
            return start;//future.get() metodundan okunacak deger
        }
    }
}
