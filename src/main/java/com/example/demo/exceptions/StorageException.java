package com.example.demo.exceptions;

/**
 * This Class is for manage Storage Exceptions.
 *
 * @author Oscar Lopez
 * @version 0.1
 */
public class StorageException extends RuntimeException{

    /**
     *
     * @param message as String
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     *
     * @param message as String
     * @param exception as Throwable Exception
     */
    public StorageException(String message, Throwable exception) {
        super(message, exception);
    }
}
