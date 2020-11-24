package entity;

import java.util.LinkedList;

/**
 * Class Buffer uses a LinkedList of generic objects T to create a synchronized buffer to ensure that separate threads
 * handle the information sequentially. The objects stored are only used in communication with the server.
 * @author Niklas Hultin
 * @version 1.0
 * @param <T> generic object
 */

public class Buffer<T> {
    private LinkedList<T> buffer = new LinkedList<T>();

    public synchronized void put(T obj) {
        buffer.addLast(obj);
        notifyAll();

    }

    public synchronized T get() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        return buffer.removeFirst();
    }
}
