package com.example;

import java.util.LinkedList;

/**
 * @author Anna Ovcharenko
 *
 * In this task, the use of notify() is essential for proper synchronization and to wake up waiting threads.
 * In the producer-consumer scenario, both producer and consumer threads
 * may need to wait when certain conditions are not met:
 *
 * Producer Waiting: If the buffer is full (reached its capacity),
 * the producer needs to wait until there is space in the buffer to add more items.
 *
 * Consumer Waiting: If the buffer is empty (no items to consume),
 * the consumer needs to wait until there are items to consume.
 *
 * The wait() and notify() methods are used to achieve this synchronization.
 * Here's how they work in the context of the code:
 *
 * In the produce() method:
 * When the buffer is full, the producer thread calls wait(), effectively pausing itself
 * and allowing other threads(e.g., the consumer) to run.
 * When an item is added to the buffer, it calls notify(),
 * signaling that it has produced an item, and potentially waking up any waiting threads
 * (e.g., the consumer) that were blocked due to the buffer being full.
 *
 * In the consume() method:
 * When the buffer is empty, the consumer thread calls wait(),
 * pausing itself to allow other threads(e.g., the producer) to run.
 * When an item is consumed (removed from the buffer), it calls notify(),
 * signaling that it has consumed an item, and potentially waking up any waiting threads
 * (e.g., the producer) that were blocked due to the buffer being empty.
 * The use of wait() and notify() ensures that the producer and consumer threads work in a coordinated manner,
 * avoiding issues like buffer overflow or underflow and efficiently utilizing system resources.
 * It allows threads to wait when necessary and resume execution when the required conditions are met.
 *
 * These mechanisms are fundamental to implementing safe and efficient multithreaded producer-consumer scenarios.
 */
class ProducerConsumer {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacity = 10;

    public void produce() {
        synchronized (this) {
            while (buffer.size() == capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int item = (int) (Math.random() * 100);
            buffer.add(item);
            System.out.println("Produced: " + item);
            notify();
        }
    }

    public void consume() {
        synchronized (this) {
            while (buffer.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int item = buffer.remove();
            System.out.println("Consumed: " + item);
            notify();
        }
    }

    int getBufferSize(){
        return buffer.size();
    }
}

public class ProducerConsumerTask {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                pc.produce();
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                pc.consume();
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("List size: " + pc.getBufferSize());
    }
}
