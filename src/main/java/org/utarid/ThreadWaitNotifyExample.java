package org.utarid;

public class ThreadWaitNotifyExample {
    public static void main(String[] args) {
        final Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 başladı.");
                    System.out.println("Thread 1 lock üzerinde bekliyor.");
                    lock.wait(); // diger thread tarafından notify edilene kadar bekler
                    System.out.println("Thread 1 uyandı ve devam ediyor.");
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 InterruptedException : " + e.getMessage());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2000); // thread2 2 sn bekleyip notify() metodu ile diger threadi uyandirir
                synchronized (lock) {
                    System.out.println("Thread 2 lock üzerinde işlemler yapıyor.");
                    lock.notify(); // diger threadi uyandirir
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 2 InterruptedException : " + e.getMessage());
            }
        });

        thread1.start();
        thread2.start();
    }
}
