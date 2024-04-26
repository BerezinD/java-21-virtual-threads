package com.mjovanc;

public record Producer(Buffer buffer, String name, int numTasksToProduce) implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= numTasksToProduce; i++) {
            buffer.produce("task " + i, name);
        }
    }
}
