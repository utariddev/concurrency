package org.utarid;

public class ThreadJoinExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 çalışıyor. ilk mesaj");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Thread 1 InterruptedException : " + e.getMessage());
            }
            System.out.println("Thread 1 çalışıyor. ikinci mesaj");
        });


        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 çalışıyor. ilk mesaj");
            try {
                thread1.join(); // Thread 1'in tamamlanmasını bekleyip daha sonra devam eder
            } catch (InterruptedException e) {
                System.out.println("Thread 2 InterruptedException : " + e.getMessage());
            }
            System.out.println("Thread 2 çalışıyor. ikinci mesaj");
        });


        thread1.start();
        thread2.start();
    }
}
