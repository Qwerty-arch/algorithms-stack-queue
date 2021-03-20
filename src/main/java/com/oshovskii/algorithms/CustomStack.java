package com.oshovskii.algorithms;

import java.util.EmptyStackException;

public class CustomStack<T> {
    private T[] list;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;

    public CustomStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public CustomStack() {
        this.capacity = DEFAULT_CAPACITY;
        list = (T[]) new Object[capacity];
    }

    public void push(T item) {
        if (isFull()) {
            grow(list.length * 2);
        }
        list[size]= item;
        size++;
    }

    private T[] grow(int newSize) {
        this.capacity = newSize;
        T [] temp = (T[]) new Object[capacity];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public T pop(){
        T temp = peek();
        size--;
        list[size]= null;
        return temp;
    }

    public T peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return list[size-1];
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

    private void reCapacity(int newCapacity){
        T[] tempArr = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, tempArr, 0, size);
        list = tempArr;
    }
}

