package ru.drogunov.springbootdemo;

public class start {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 1; i < 3; i++) {
                System.out.println(i);
            }
        });
        thread.run();
   
        
    }
}
