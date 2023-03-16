package assignment2;

public class MyStack<E> {

    private MyDoublyLinkedList<E> stack;

    public MyStack() {
        stack = new MyDoublyLinkedList<>();
    }

    public void push(E element){
        stack.add(element);
    }

    public E pop(){
        return stack.remove();
    }
    public E peek() {
        return stack.peekLast();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public int getSize() {
        return stack.getSize();
    }

}
