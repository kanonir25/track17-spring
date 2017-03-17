package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    protected int capacity;
    private int[] data;
    public MyArrayList() {
        this.size = 0;
        this.capacity = 2;
        this.data = new int[capacity];
    }

    public MyArrayList(int capacity) {
        this();
        this.capacity = capacity;
    }

    @Override
    void add(int item) {
        if (size == capacity)
            expand_capacity();
        this.data[size] = item;
        size++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        int removedElement;
        if (idx < size)
            removedElement = data[idx];
        else
            throw new NoSuchElementException();
        System.arraycopy(data, idx + 1, data, idx, size - idx - 1);
        size--;
        return removedElement;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        int gotElement;
        if (idx < size)
            gotElement = data[idx];
        else
            throw new NoSuchElementException();
        return gotElement;
    }

    private void expand_capacity(){
        int oldCapacity = this.capacity;
        if (this.capacity == 0) {
            this.capacity = 2;
        } else {
            this.capacity *= 2;
        }
        int[] newData = new int[this.capacity];
        System.arraycopy(data, 0, newData, 0, oldCapacity);
        this.data = newData;
    }
}
