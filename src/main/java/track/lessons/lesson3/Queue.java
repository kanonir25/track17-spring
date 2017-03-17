package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Created by gunner on 17.03.17.
 */
public interface Queue {
    void enqueue(int value);

    int dequeue() throws NoSuchElementException;
}
