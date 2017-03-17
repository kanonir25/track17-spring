package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    private Node endList;

    public MyLinkedList() {
        super();
        this.endList = new Node(null, null, 0);
    }

    @Override
    void add(int item) {
        Node additionNode = new Node(this.endList, null, item);
        this.endList.next = additionNode;
        this.endList = additionNode;
        size++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        int removedValue;
        Node currentNode = this.endList;
        if (idx < size) {
            for (int i = 0; i < size - idx - 1; i++) {
                currentNode = currentNode.prev;
            }
            removedValue = currentNode.val;
            size--;
            while (currentNode.next != null) {
                currentNode.next.prev = currentNode.prev;
                currentNode.prev.next = currentNode.next;
                currentNode = currentNode.next;
            }
            this.endList = currentNode;
        } else {
            throw new NoSuchElementException();
        }
        return removedValue;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        int gotValue;
        Node currentNode = this.endList;
        if (idx < size) {
            for (int i = 0; i < size - idx - 1; i++) {
                currentNode = currentNode.prev;
            }
            gotValue = currentNode.val;
        } else {
            throw new NoSuchElementException();
        }
        return gotValue;
    }

    public void enqueue(int value) {
        Node currentNode = this.endList;
        while (currentNode.prev != null) {
            currentNode = currentNode.prev;
        }
        Node additionNode = new Node(null, currentNode, value);
        currentNode.prev = additionNode;
        size++;
    }

    public int dequeue() throws NoSuchElementException {
        int deletedValue;
        Node currentNode = this.endList;
        while (currentNode.prev != null) {
            currentNode = currentNode.prev;
        }
        deletedValue = currentNode.val;
        currentNode.next.prev = null;
        currentNode.next = null;
        size--;
        return deletedValue;
    }

    public void push(int value) {
        this.add(value);
        size++;
    }

    public int pop() throws NoSuchElementException {
        int deletedValue;
        if (size > 0) {
            deletedValue = this.endList.val;
            if (this.endList.prev != null) {
                this.endList.prev.next = null;
            }
            this.endList = this.endList.prev;
            size--;
        } else {
            throw new NoSuchElementException();
        }
        return deletedValue;
    }

}
