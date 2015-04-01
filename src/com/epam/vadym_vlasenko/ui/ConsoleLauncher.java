package com.epam.vadym_vlasenko.ui;

import com.epam.vadym_vlasenko.finder.ByteArrayFinder;

import java.util.Scanner;

/**
 * Created by Vadym_Vlasenko on 01.04.2015.
 */
public class ConsoleLauncher {

    public void run() {
        ByteArrayFinder finder = new ByteArrayFinder();
        final Object MONITOR = finder.getMONITOR();
        while (true) {
            Thread t = new Thread(finder);
            t.start();
            System.out.println("Please, enter file path");
            Scanner in = new Scanner(System.in);
            if (in.hasNextLine()) {
                finder.setFilePath(in.nextLine());
            }
            long start = System.currentTimeMillis();
            synchronized (MONITOR) {
                MONITOR.notifyAll();
            }
            while (t.getState() != Thread.State.WAITING) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Current length: " + finder.getLength());
                System.out.println("Current first: " + finder.getFirst());
                System.out.println("Current last: " + finder.getLast());
                System.out.println();
            }
            System.out.println("Result time: " + (System.currentTimeMillis() - start));
            System.out.println("Current length: " + finder.getLength());
            System.out.println("Current first: " + finder.getFirst());
            System.out.println("Current last: " + finder.getLast());
        }
    }

}
