package com.oshovskii.algorithms;

import java.util.NoSuchElementException;

public class CustomDeque<T> {
    private T[] list;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;
    private int begin;
    private int end;

    public CustomDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public CustomDeque() {
        this.capacity = DEFAULT_CAPACITY;
        list = (T[]) new Object[capacity];
    }

    /**
     * Метод добавления в очередь нового элемента
     *
     * @param item добавляемый элемент
     * @throws IllegalStateException если очередь полная
     */
    public void insertLast(T item) throws IllegalStateException {
        if (isFull()) {
                grow(list.length * 2);
        }
        size++;
        list[begin] = item;
        begin = nextIndex(begin);
    }

    public void insertFirst(T item) throws IllegalStateException {
        if (isFull()) {
            grow(list.length * 2);
        }
        size++;
        list[end] = item;
        end = nextIndex(end);
    }

    private T[] grow(int newSize) {
        this.capacity = newSize;
        T [] temp = (T[]) new Object[capacity];
        end = size;
        begin = 0;
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[begin];
    }

    public T remove() {
        T temp = peekFront();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
        return temp;
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

    private int nextIndex(int index) {
        return (index + 1) % list.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        int i = begin;
        while (i != end) {
            sb.append(list[i]).append(", ");
            i = nextIndex(i);
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }
}

