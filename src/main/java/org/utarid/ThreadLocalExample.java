package org.utarid;

public class ThreadLocalExample {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            threadLocal.set(10);
            System.out.println("thread1 - Value: " + threadLocal.get());
            threadLocal.remove(); //deger kullanildiktan sonra silinebilir
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set(20);
            System.out.println("thread2 - Value: " + threadLocal.get());
        });

        thread1.start();
        thread2.start();
    }
}