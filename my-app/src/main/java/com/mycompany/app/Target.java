package com.mycompany.app;

import java.util.Scanner;

public class Target {
    public static String getSecret() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static void leak(String data) {
        System.out.println(data);
    }

    public static void run() {
        String secret = getSecret();
        leak(secret);
    }
}