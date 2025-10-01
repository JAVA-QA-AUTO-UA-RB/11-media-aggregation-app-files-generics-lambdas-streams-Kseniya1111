package com.example.media.classes;

@FunctionalInterface
public interface MediaFilter<T extends Media> {
    boolean test(T item);
}
