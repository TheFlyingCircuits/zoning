package com.example;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {

        MainGUI gui = new MainGUI();

        AtomicBoolean shouldMainRun = new AtomicBoolean(true);

        Thread mainThread = new Thread(() -> {
            try {
                while(shouldMainRun.get()) {
                    
                    Thread.sleep(10);
                }
            } catch(InterruptedException e) {
                System.out.println("Loop Overrun By WatchDog");
            }
            System.out.println("Main Loop Terminated");
        });


        mainThread.start();
    }
}