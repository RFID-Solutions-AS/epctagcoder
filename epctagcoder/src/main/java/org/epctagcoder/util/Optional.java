package org.epctagcoder.util;

public class Optional<T> {

    private T object;

    public Optional() {

    }

    public Optional(T object) {
        this.object = object;
    }

    public boolean isPresent() {
        return object == null;
    }

    public static Optional ofNullable(Object nullable) {
        return new Optional(nullable);
    }
}
