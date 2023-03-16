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
            if (this.getSize() != other.getSize()) {
                return false;
            }
            MyQueue<E> copy1 = this.copy();
            MyQueue<E> copy2 = other.copy();
            while (!copy1.isEmpty()) {
                E element1 = copy1.dequeue();
                E element2 = copy2.dequeue();
                if (!element1.equals(element2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private MyQueue<E> copy() {
        MyQueue<E> copy = new MyQueue<E>();
        MyQueue<E> original = new MyQueue<E>();
        while (!this.isEmpty()) {
            E element = this.dequeue();
            copy.enqueue(element);
            original.enqueue(element);
        }
        while (!original.isEmpty()) {
            E element = original.dequeue();
            this.enqueue(element);
        }
        return copy;
    }

}
