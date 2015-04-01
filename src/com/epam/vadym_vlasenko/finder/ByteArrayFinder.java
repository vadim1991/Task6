package com.epam.vadym_vlasenko.finder;

import com.epam.vadym_vlasenko.util.ByteArrayUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Vadym_Vlasenko on 01.04.2015.
 */
public class ByteArrayFinder implements Runnable {
    private final Object MONITOR = new Object();

    /**
     * Path to file for searching bytes
     */
    private String filePath;

    /**
     * Length of longest repeatable sequence f bytes
     */
    private volatile int length;

    /**
     * Index of first sequence starts
     */
    private volatile int first;

    /**
     * Index of second sequence starts
     */
    private volatile int last;

    public Object getMONITOR() {
        return MONITOR;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLength() {
        return length;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (MONITOR) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
            Path path = Paths.get(filePath);
            byte[] array = null;
            try {
                array = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            findMax(array);
        }
    }

    private void findMax(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            int indexSameElement = ByteArrayUtilities.searchNearestSameByte(
                    ByteArrayUtilities.indexOf(bytes[i], bytes, i), i + 1, bytes);
            if (indexSameElement != -1) {
                for (int j = indexSameElement; j < bytes.length; ) {
                    int tmpLength = 1;
                    if (indexSameElement + 1 < bytes.length) {
                        int tmpI = i;
                        int tmpK = j;
                        while (tmpK + 1 < bytes.length && bytes[++tmpI] == bytes[++tmpK]) {
                            tmpLength++;
                        }
                        if (tmpLength > length) {
                            first = i;
                            last = j;
                            length = tmpLength;
                        }
                    }
                    int nextIndex = ByteArrayUtilities.searchNearestSameByte(i, j + 1, bytes);
                    if (nextIndex == -1) {
                        break;
                    } else {
                        j = nextIndex;
                    }
                }
            }
        }
    }
}
