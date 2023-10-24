package com.example.singleton.multithreaded;

/**
 * @author Anna Ovcharenko
 */
public class ThreadSafeSingleton {

    // Private static instance of the class
    private static ThreadSafeSingleton instance;
    public String value;

    // Private constructor to prevent instantiation from other classes
    private ThreadSafeSingleton(String value) {
        // Initialization code, if any
        this.value = value;
    }

    // Public method to provide access to the instance
    public static synchronized ThreadSafeSingleton getInstance(String value) {
        if (instance == null) {
            instance = new ThreadSafeSingleton(value);
        }
        return instance;
    }
}

