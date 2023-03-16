package assignment2;

public class MyQueue<E> {

    private MyDoublyLinkedList<E> queue;

    public MyQueue() {
        queue = new MyDoublyLinkedList<>();
    }

    public void enqueue(E element) {
        queue.add(element);
    }

    public E dequeue() {
        return queue.removeFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void clear() {
        queue.clear();
    }

    public boolean equals(Object obj){
        return queue.equals(obj);
    }
}
