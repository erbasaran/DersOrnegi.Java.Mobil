package com.hr200012.bekir_berk_dundar_final.util;

import java.net.InetAddress;

public class ConnectionHelper {
    public static boolean InternetIsAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}