package com.epam.vadym_vlasenko.util;

/**
 * Created by Vadym_Vlasenko on 01.04.2015.
 */
public class ByteArrayUtilities {
    public static int searchNearestSameByte(int indexForSearch, int from, byte[] arr) {
        for (int i = from; i < arr.length; i++) {
            if (arr[indexForSearch] == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(byte element, byte[] arr, int from) {
        for (int i = from; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }
}
