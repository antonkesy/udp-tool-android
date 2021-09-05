package com.antonkesy.udptool.util;

public final class Utils {

    public static Byte[] byteToByte(byte[] input) {
        Byte[] output = new Byte[input.length];
        for (int i = 0; i < input.length; ++i) {
            output[i] = input[i];
        }
        return output;
    }
}
