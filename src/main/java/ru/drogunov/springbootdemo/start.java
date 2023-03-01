package ru.drogunov.springbootdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class start {
    public static void main(String[] args) {
        try {
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            String b = br.readLine();
            
            
            Integer number = Integer.parseInt(s);
            Integer number1 = Integer.parseInt(b);
            System.out.println(number + number1);
        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
}
