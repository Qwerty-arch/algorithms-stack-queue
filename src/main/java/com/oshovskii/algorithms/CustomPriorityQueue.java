package com.oshovskii.algorithms;

import java.util.Arrays;
import java.util.EmptyStackException;

public class CustomPriorityQueue<T extends Comparable<T>> {
    private T[] list;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;

    public CustomPriorityQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Comparable[capacity];
    }

    public CustomPriorityQueue() {
        this.capacity = DEFAULT_CAPACITY;
        list = (T[]) new Comparable[capacity];
    }

    public void insert(T item) {
        if (isFull()) {
            grow(list.length * 2);
        }
        list[size] = item;
        size++;

        int i = size - 1;
        while (i > 0 && list[i-1].compareTo(list[i]) < 0) {
            swap(i, i-1);
            i--;
        }
    }

    private T[] grow(int newSize) {
        this.capacity = newSize;
        T [] temp = (T[]) new Comparable[capacity];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public T remove() {
        T temp = peek();
        size--;
        list[size] = null;
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[size - 1];
    }


    public boolean isFull() {
        return size == list.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void reCapacity(int newCapacity) {
        T[] tempArr = (T[]) new Comparable[newCapacity];
        System.arraycopy(list, 0, tempArr, 0, size);
        list = tempArr;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
}

