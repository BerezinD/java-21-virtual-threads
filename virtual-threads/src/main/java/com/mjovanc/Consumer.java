package com.mjovanc;

public record Consumer(Buffer buffer, String name, int numTasksToConsume) implements Runnable {

    @Override
    public void run() {
        int tasksConsumed = 0;
        while (tasksConsumed < numTasksToConsume) {
            buffer.consume(name);
            tasksConsumed++;
        }
    }
}
