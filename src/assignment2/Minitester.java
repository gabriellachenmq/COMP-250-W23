package assignment2;

import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class Minitester {}

class SyntaxTest {

    @Test
    @Tag("score:0")
    @DisplayName("Syntax Test for MyList")
    void syntaxTestMyList() throws NoSuchMethodException {

        Class<?> mList = MyList.class;
        //Check if MyList is an Interface
        assertTrue(mList.isInterface());

        // Check the methods by signature
        // Return NoSuchMethodException if the input type or method name is not matched
        assertEquals("getSize", mList.getMethod("getSize").getName());
        assertEquals("isEmpty", mList.getMethod("isEmpty").getName());
        assertEquals("add", mList.getMethod("add", Object.class).getName());
        assertEquals("clear", mList.getMethod("clear").getName());
        assertEquals("remove", mList.getMethod("remove").getName());

        // Check the return type of each method
        assertEquals("int", mList.getMethod("getSize").getReturnType().getName());
        assertEquals("boolean", mList.getMethod("isEmpty").getReturnType().getName());
        assertEquals("void", mList.getMethod("add", Object.class).getReturnType().getName());
        assertEquals("void", mList.getMethod("clear").getReturnType().getName());
        assertEquals(Object.class.getName(), mList.getMethod("remove").getReturnType().getName());

    }

    @Test
    @Tag("score:0")
    @DisplayName("Syntax Test for MyLinkedList")
    void syntaxTestMyLinkedList() throws NoSuchMethodException, NoSuchFieldException {
        Class<?> mLinkedList = MyLinkedList.class;

        // Check if we have a field named size and its type is int
        assertEquals("int", mLinkedList.getDeclaredField("size").getType().getName());

        // Check isEmpty()
        assertEquals("isEmpty", mLinkedList.getMethod("isEmpty").getName());
        assertEquals("boolean", mLinkedList.getMethod("isEmpty").getReturnType().getName());

        // Check getSize()
        assertEquals("getSize", mLinkedList.getMethod("getSize").getName());
        assertEquals("int", mLinkedList.getMethod("getSize").getReturnType().getName());

    }

    @Test
    @Tag("score:0")
    @DisplayName("Syntax Test for MyDoublyLinkedList")
    void syntaxTestMyDoublyLinkedList() throws NoSuchMethodException, NoSuchFieldException {

        Class<?> dList = MyDoublyLinkedList.class;

        // Check if MyDoublyLinkedList extends MyLinkedList
        assertEquals(MyLinkedList.class, dList.getSuperclass());

        // Check add()
        assertEquals("add", dList.getMethod("add", Object.class).getName());
        assertEquals("void", dList.getMethod("add", Object.class).getReturnType().getName());

        // Check remove()
        assertEquals("remove", dList.getMethod("remove").getName());
        assertEquals(Object.class.getName(), dList.getMethod("remove").getReturnType().getName());

        // Check addFirst and addLast
        assertEquals("addFirst", dList.getMethod("addFirst", Object.class).getName());
        assertEquals("void", dList.getMethod("addFirst", Object.class).getReturnType().getName());
        assertEquals("addLast", dList.getMethod("addLast", Object.class).getName());
        assertEquals("void", dList.getMethod("addLast", Object.class).getReturnType().getName());

        // Check removeFirst and removeLast
        assertEquals("removeFirst", dList.getMethod("removeFirst").getName());
        assertEquals(Object.class.getName(), dList.getMethod("removeFirst").getReturnType().getName());
        assertEquals("removeLast", dList.getMethod("removeLast").getName());
        assertEquals(Object.class.getName(), dList.getMethod("removeLast").getReturnType().getName());

        // Check peekFirst and peekLast
        assertEquals("peekFirst", dList.getMethod("peekFirst").getName());
        assertEquals(Object.class.getName(), dList.getMethod("peekFirst").getReturnType().getName());
        assertEquals("peekLast", dList.getMethod("peekLast").getName());
        assertEquals(Object.class.getName(), dList.getMethod("peekLast").getReturnType().getName());

        // Check clear
        assertEquals("clear", dList.getMethod("clear").getName());
        assertEquals("void", dList.getMethod("clear").getReturnType().getName());

        // Check equals
        assertEquals("equals", dList.getMethod("equals", Object.class).getName());
        assertEquals("boolean", dList.getMethod("equals", Object.class).getReturnType().getName());

    }

    @Test
    @Tag("score:0")
    @DisplayName("Syntax Test for MyStack")
    void syntaxTestMyStack() throws NoSuchMethodException, NoSuchFieldException {
        Class<?> stack = MyStack.class;

        // the name of the private field is not given
        // a constructor with no inputs shall be created by default

        // check push
        assertEquals("push", stack.getMethod("push", Object.class).getName());
        assertEquals("void", stack.getMethod("push", Object.class).getReturnType().getName());

        // check pop
        assertEquals("pop", stack.getMethod("pop").getName());
        assertEquals(Object.class.getName(), stack.getMethod("pop").getReturnType().getName());

        // check peek
        assertEquals("peek", stack.getMethod("peek").getName());
        assertEquals(Object.class.getName(), stack.getMethod("peek").getReturnType().getName());

        // check isEmpty
        assertEquals("isEmpty", stack.getMethod("isEmpty").getName());
        assertEquals("boolean", stack.getMethod("isEmpty").getReturnType().getName());

        // Check clear
        assertEquals("clear", stack.getMethod("clear").getName());
        assertEquals("void", stack.getMethod("clear").getReturnType().getName());

        // Check getSize
        assertEquals("getSize", stack.getMethod("getSize").getName());
        assertEquals("int", stack.getMethod("getSize").getReturnType().getName());

    }

    @Test
    @Tag("score:0")
    @DisplayName("Syntax Test for MyQueue")
    void syntaxTestMyQueue() throws NoSuchMethodException, NoSuchFieldException {
        Class<?> queue = MyQueue.class;

        // check field
        boolean found = false;
        for (Field f : queue.getDeclaredFields()) {
            if (f.getType().equals(MyDoublyLinkedList.class)) {
                found = true;
                break;
            }
        }
        assertTrue(found);

        // check enqueue
        assertEquals("enqueue", queue.getMethod("enqueue", Object.class).getName());
        assertEquals("void", queue.getMethod("enqueue", Object.class).getReturnType().getName());

        // check dequeue
        assertEquals("dequeue", queue.getMethod("dequeue").getName());
        assertEquals(Object.class.getName(), queue.getMethod("dequeue").getReturnType().getName());

        // check isEmpty
        assertEquals("isEmpty", queue.getMethod("isEmpty").getName());
        assertEquals("boolean", queue.getMethod("isEmpty").getReturnType().getName());

        // Check clear
        assertEquals("clear", queue.getMethod("clear").getName());
        assertEquals("void", queue.getMethod("clear").getReturnType().getName());

        // Check equals
        assertEquals("equals", queue.getMethod("equals", Object.class).getName());
        assertEquals("boolean", queue.getMethod("equals", Object.class).getReturnType().getName());

    }
}

class Part1Test {

    // ==================== MY DOUBLY LINKED LIST TEST =================== //
    @Test
    @Tag("score:1")
    @DisplayName("MyDLL add() test1")
    public void shouldAdd() {
        MyDoublyLinkedList<Number> list = new MyDoublyLinkedList<>();

        list.add(2);
        list.addFirst(5);
        list.addFirst(9);
        list.addFirst(0);  // {0, 9, 5, 2}

        assertEquals(4, list.getSize());
        assertEquals(0, list.peekFirst());
        assertEquals(2, list.peekLast());

        list = new MyDoublyLinkedList<>();
        list.add(2);
        list.add(5);
        list.addLast(9);
        list.addFirst(0);  // {0, 2, 5, 9}

        assertEquals(4, list.getSize());
        assertEquals(0, list.peekFirst());
        assertEquals(9, list.peekLast());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyDLL remove() test1")
    public void shouldRemove() {
        MyDoublyLinkedList<Number> list = new MyDoublyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Number removedItem = list.removeFirst();  // {2, 3}

        assertEquals(1, removedItem);
        assertEquals(2, list.peekFirst());
        assertEquals(2, list.getSize());

        list = new MyDoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.addFirst(4);
        list.addFirst(5);  // {5, 4, 1, 2, 3}

        removedItem = list.removeLast();

        assertEquals(3, removedItem);
        assertEquals(2, list.peekLast());
        assertEquals(4, list.getSize());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyDLL exception handling test1")
    public void shouldThrowExceptionOnEmptyList() {
        MyDoublyLinkedList<Number> list = new MyDoublyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.peekLast());
        assertThrows(NoSuchElementException.class, () -> list.peekFirst());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyDLL peek() test1")
    public void shouldPeek() {
        MyDoublyLinkedList<Number> list = new MyDoublyLinkedList<>();

        list.addLast(1);
        list.add(2);
        list.addFirst(3);

        assertEquals(3, list.peekFirst());
        assertEquals(2, list.peekLast());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyDLL clear() test1")
    public void shouldClear() {
        MyDoublyLinkedList<Number> list = new MyDoublyLinkedList<>();

        list.addLast(1);
        list.add(2);
        list.addFirst(3);

        list.clear();

        assertEquals(0, list.getSize());
        assertFalse(list.iterator().hasNext());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyDLL equals() test1")
    public void shouldCheckEquals() {
        MyDoublyLinkedList<Number> list1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<Number> list2 = new MyDoublyLinkedList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(1);
        list2.add(2);
        list2.add(3);


        assertTrue(list1.equals(list2));
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyDLL equals() test2")
    public void shouldCheckNotEquals() {
        MyDoublyLinkedList<Number> list1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<Number> list2 = new MyDoublyLinkedList<>();

        list1.add(1);
        list1.add(2);

        list2.add(2);
        list2.add(1);

        assertFalse(list1.equals(list2));
    }


    // ==================== MYQUEUE TEST =================== //

    @Test
    @Tag("score:2")
    @DisplayName("MyQueue enqueue(), dequeue() and clear() test")
    public void shouldEnqueueAndDequeue() {
        MyQueue<Number> queue = new MyQueue<Number>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertFalse(queue.isEmpty());

        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 2);
        assertEquals(queue.dequeue(), 3);

        assertTrue(queue.isEmpty());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.clear();

        assertTrue(queue.isEmpty());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyQueue equals() test")
    public void shouldCheckEqual() {
        MyQueue<Number> queue1 = new MyQueue<Number>();
        MyQueue<Number> queue2 = new MyQueue<Number>();

        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);

        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);

        assertTrue(queue1.equals(queue2));
    }

    // ==================== MYSTACK TEST =================== //
    @Test
    @Tag("score:1")
    @DisplayName("MyStack push() and pop() test1")
    public void shouldPush() {
        MyStack<Number> stack = new MyStack<Number>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(stack.getSize(), 3);
        assertEquals(stack.peek(), 3);

        Number popped = stack.pop();

        assertEquals(3, popped);
        assertEquals(2, stack.getSize());
        assertEquals(2, stack.peek());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyStack isEmpty() test1")
    public void shouldReturnIsEmpty() {
        MyStack<Number> stack = new MyStack<Number>();
        assertTrue(stack.isEmpty());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyStack clear() test1")
    public void shouldReturnClear() {
        MyStack<Number> stack = new MyStack<Number>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }
}
