package assignment2;

public abstract class MyLinkedList<E> implements MyList<E>{

    protected int size;
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
}
