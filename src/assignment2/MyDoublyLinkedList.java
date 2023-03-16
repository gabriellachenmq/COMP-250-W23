package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E>{
    private DNode head;
    private DNode tail;

    public void add(E element) {
        addLast(element);
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public E remove() {
        return removeLast();
    }

    public void addFirst(E element){
        DNode nNode = new DNode();
        if (head == null){
            head = nNode;
            tail = nNode;
        }
        else{
            nNode.next = head;
            head.prev = nNode;
            head = nNode;
        }
        size++;
    }

    public void addLast(E element){
        DNode nNode = new DNode();
        if (tail == null){
            head = nNode;
            tail = nNode;
        }
        else{
            nNode.next = tail;
            tail.prev = nNode;
            tail = nNode;
        }
        size++;
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        E element = head.element;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return element;
    }

    public E removeLast(){
        if (tail == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        E element = tail.element;
        if(head == tail){
            head = null;
            tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return element;
    }

    public E peekFirst() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        return head.element;
    }
    public E peekLast() {
        if (tail == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        return tail.element;
    }

    public boolean equals(Object obj){
        if (obj instanceof MyDoublyLinkedList){
            MyDoublyLinkedList otherOne = (MyDoublyLinkedList) obj;
            if (size != otherOne.size){
                return false;
            }
            Iterator<E> iterator = iterator();
            Iterator otherIterator = otherOne.iterator();
            while (iterator.hasNext() && otherIterator.hasNext()) {
                E element = iterator.next();
                Object otherEle = otherIterator.next();
                if (!element.equals(otherEle)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private class DNode {
        private E element;
        private DNode next;
        private DNode prev;
    }

    private class DLLIterator implements Iterator<E> {
        DNode curr;

        public DLLIterator() {
            this.curr = head;
        }

        public boolean hasNext() {
            return this.curr != null;
        }

        public E next() {
            if (!this.hasNext())
                throw new NoSuchElementException();

            E element = this.curr.element;
            this.curr = this.curr.next;
            return element;
        }
    }
}
