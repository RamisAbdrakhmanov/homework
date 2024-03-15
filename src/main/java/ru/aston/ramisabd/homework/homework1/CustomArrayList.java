package ru.aston.ramisabd.homework.homework1;

import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {

    private Object[] arr;
    private int size = 0;

    public CustomArrayList() {
        arr = new Object[10];
    }

    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Выход за границу списка");
        }


        if (size == arr.length) {
            increaseArrayCapacity();
        }

        if (index != size) {
            System.arraycopy(arr, index, arr, index + 1, size - index);
        }

        arr[index] = element;
        size++;

    }

    public void addAll(Collection<? extends E> c) {
        Object[] newElements = c.toArray();

        while (arr.length < size + newElements.length) {
            increaseArrayCapacity();
        }

        System.arraycopy(newElements, 0, arr, size, newElements.length);
        size += newElements.length;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) arr[index];
    }

    public boolean isEmpty() {
        return arr[0] == null;
    }

    public void remove(int index) {
        System.arraycopy(arr, index + 1, arr, index, size - index);
        arr[size] = null;
        size--;
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == arr[i]) {
                remove(i);
            }
        }
    }

    public int size() {
        return size;
    }

    public void sort(Comparator<? super E> c) {

        if (c == null) {
            sort(arr, 0, size - 1, Comparator.naturalOrder());
        } else {
            sort(arr, 0, size - 1, c);
        }


    }

    private void sort(Object[] arr, int left, int right, Comparator c) {
        if (left < right) {
            int mid = quick(arr, left, right, c);
            sort(arr, left, mid - 1, c);
            sort(arr, mid, right, c);
        }
    }

    private int quick(Object[] arr, int left, int right, Comparator c) {
        Object mid = arr[left + (right - left) / 2];
        while (left <= right) {
            while (c.compare(arr[left], mid) < 0) {
                left++;
            }

            while (c.compare(arr[right], mid) > 0) {
                right--;
            }

            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private void swap(Object[] arr, int index1, int index2) {
        Object temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private void increaseArrayCapacity() {
        Object[] newArr = new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
}
