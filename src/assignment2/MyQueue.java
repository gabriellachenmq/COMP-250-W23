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

    public int getSize() {
        return queue.getSize();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MyQueue) {
            MyQueue<E> other = (MyQueue<E>) obj;
            if (getSize() != other.getSize()) {
                return false;
            }
            if (getSize() == 0){
                return true;
            }
            while (!isEmpty()) {
                if (!dequeue().equals(other.dequeue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
