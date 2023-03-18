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

class Part2Test {

    // ==================== POSITION CLASS TEST =================== //
    @Test
    @Tag("score:1")
    @DisplayName("Position move() test1")
    void positionMoveDir1() {
        Position pos = new Position(7, 5);
        pos.moveWest();
        pos.moveNorth();

        assertEquals(6, pos.getX());
        assertEquals(4, pos.getY());

        pos = new Position(7, 5);
        pos.moveEast();
        pos.moveSouth();

        assertEquals(8, pos.getX());
        assertEquals(6, pos.getY());
    }

    @Test
    @Tag("score:1")
    @DisplayName("Position reset() test1")
    void positionReset1() {
        Position pos = new Position(7, 5);

        pos.reset(6, 9);

        assertEquals(6, pos.getX());
        assertEquals(9, pos.getY());

        Position pos2 = new Position(9, 6);

        pos.reset(pos2);
        assertEquals(9, pos.getX());
        assertEquals(6, pos.getY());
    }

    @Test
    @Tag("score:1")
    @DisplayName("Position getDistance() test")
    void positionGetDistance() {
        Position pos = new Position(7, 5);
        Position pos2 = new Position(0, 5);

        assertEquals(7, Position.getDistance(pos, pos2));
    }

    @Test
    @Tag("score:1")
    @DisplayName("Position equals() test1")
    void positionEqual1() {
        Position pos = new Position(7, 5);
        Position pos2 = new Position(9, 6);

        assertFalse(pos.equals(pos2));

        pos2.reset(pos);

        assertTrue(pos.equals(pos2));
    }

    // Testing Basic movement functionality
    @Test
    void posMove_1() {
        Position p = new Position(0, 0);
        p.moveNorth();
        assertEquals(new Position(0, -1), p);
        p.moveEast();
        assertEquals(new Position(1, -1), p);
        p.moveSouth();
        assertEquals(new Position(1, 0), p);
        p.moveWest();
        assertEquals(new Position(0, 0), p);
    }


    // ==================== TARGETQUEUE CLASS TEST =================== //
    @Test
    @Tag("score:1")
    @DisplayName("TargetQueue addTargets() test1")
    void tqAddTargets1() {
        TargetQueue test = new TargetQueue();
        assertTrue(test.isEmpty());

        test.addTargets("(7,5)");
        assertFalse(test.isEmpty());

        Position pos = new Position(7, 5);
        assertEquals(pos, test.dequeue());
    }

    @Test
    @Tag("score:2")
    @DisplayName("TargetQueue addTargets() test2")
    void tqAddTargets2() {
        TargetQueue test = new TargetQueue();

        test.addTargets("(7,5).(0,5).(2,3)");

        Position pos = new Position(7, 5);
        Position pos2 = new Position(0, 5);
        Position pos3 = new Position(2, 3);

        assertFalse(test.isEmpty());

        assertEquals(pos, test.dequeue());
        assertEquals(pos2, test.dequeue());
        assertEquals(pos3, test.dequeue());

    }

    @Test
    @Tag("score:1")
    @DisplayName("TargetQueue addTargets() test3")
    void tqAddTargets3() {
        TargetQueue test = new TargetQueue();

        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(7,5)(0,5)"));
    }

    @Test
    @Tag("score:1")
    @DisplayName("TargetQueue addTargets() test4")
    void tqAddTargets4() {
        TargetQueue test = new TargetQueue();

        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(7,1).(0,)"));
    }

    @Test
    @Tag("score:5")
    @DisplayName("TargetQueue addTargets() GitHub Test 1 - Killua")
    void tqAddTargets5() {
        TargetQueue test = new TargetQueue();

        test.addTargets("(1,2).(3,4).(5,6).");

        Position pos = new Position(1, 2);
        Position pos2 = new Position(3, 4);
        Position pos3 = new Position(5, 6);

        assertFalse(test.isEmpty());

        assertEquals(pos, test.dequeue());
        assertEquals(pos2, test.dequeue());
        assertEquals(pos3, test.dequeue());

        // Should not have any error even with "." at the end
        // https://edstem.org/us/courses/32649/discussion/2716504

    }

    @Test
    @Tag("score:5")
    @DisplayName("TargetQueue addTargets() GitHub Test 2 - Killua")
    void tqAddTargets6() {
        TargetQueue test = new TargetQueue();

        test.addTargets(".");

        assertTrue(test.isEmpty());

        // Should not have any error even with just "." as the queue should just be empty
        // https://edstem.org/us/courses/32649/discussion/2716504
    }

    @Test
    @Tag("score:5")
    @DisplayName("TargetQueue addTargets() GitHub Test 3 - Killua")
    void tqAddTargets7() {
        TargetQueue test = new TargetQueue();

        test.addTargets(".(1,2).(3,4)");

        assertFalse(test.isEmpty());

        Position pos = new Position(1, 2);
        Position pos2 = new Position(3, 4);

        assertEquals(pos, test.dequeue());
        assertEquals(pos2, test.dequeue());

        // Should not have any error even with "." in front
        // https://edstem.org/us/courses/32649/discussion/2716504
    }

    @Test
    @Tag("score:5")
    @DisplayName("TargetQueue addTargets() GitHub Test 4 - Killua")
    void tqAddTargets8() {
        TargetQueue test = new TargetQueue();

        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(1,2)..(3,4)"));

        // Should throw error since there is more than one period between each position
        // https://edstem.org/us/courses/32649/discussion/2716504
    }

    @Test
    @Tag("score:5")
    @DisplayName("TargetQueue addTargets() GitHub Test 5 - Killua")
    void tqAddTargets9() {
        TargetQueue test = new TargetQueue();

        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(1, 2).(3,4)"));

        // Should throw error when there is a space between characters
        // https://edstem.org/us/courses/32649/discussion/2754324
    }

    @Test
    @Tag("score:1")
    @DisplayName("TargetQueue clear() test")
    void tqClear() {
        TargetQueue test = new TargetQueue();

        test.addTargets("(7,5)");
        assertFalse(test.isEmpty());

        test.clear();
        assertTrue(test.isEmpty());
    }

    @Test
    void tqAddTargets_1() {
        TargetQueue test = new TargetQueue();
        test.addTargets(".");
        assertTrue(test.isEmpty());
    }

    // front and back period
    @Test
    void tqAddTargets_2() {
        TargetQueue test = new TargetQueue();
        test.addTargets(".(0,0).");
        assertEquals(test.dequeue(), new Position(0, 0));
        assertTrue(test.isEmpty());
    }

    @Test
    void tqAddTargets_3() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("..(0,0)."));
    }

    // Nothing as input
    @Test
    void tqAddTargets_4() {
        TargetQueue test = new TargetQueue();
        test.addTargets("");
        assertTrue(test.isEmpty());
    }

    // Just a space as input
    @Test
    void tqAddTargets_5() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets(" "));
    }

    // Null input
    @Test
    void tqAddTargets_6() {
        TargetQueue test = new TargetQueue();
        assertThrows(NullPointerException.class,
                () -> test.addTargets(null));
    }

    // Letter in coordinate
    @Test
    void tqAddTargets_7() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(0,0).(1,1).(0,a)"));
    }

    // Multi Digit Position Input
    @Test
    void tqAddTargets_8() {
        TargetQueue test = new TargetQueue();
        test.addTargets("(0,0).(100,9000)");
        assertEquals(test.dequeue(), new Position(0, 0));
        assertEquals(test.dequeue(), new Position(100, 9000));
        assertTrue(test.isEmpty());
    }

    // Mad periods
    @Test
    void tqAddTargets_9() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("....."));
    }

    // straight up no periods
    @Test
    void tqAddTargets_10() {
        TargetQueue test = new TargetQueue();
        test.addTargets("(0,0)");
        assertEquals(test.dequeue(), new Position(0, 0));
        assertTrue(test.isEmpty());
    }

    // missing second half
    @Test
    void tqAddTargets_11() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(1,"));
    }

    // missing second parenthesis
    @Test
    void tqAddTargets_12() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(1,2"));
    }

    // just one number
    @Test
    void tqAddTargets_13() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12)"));
    }

    // wierd minus sign placement
    @Test
    void tqAddTargets_15() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12,1-3)"));
    }

    // wierd minus sign placement pt2
    @Test
    void tqAddTargets_16() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12,13-)"));
    }

    // float coordinates
    @Test
    void tqAddTargets_17() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12,1.3)"));
    }

    // empty but properly formatted I suppose?
    @Test
    void tqAddTargets_18() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(,)"));
    }

    // properly formatted but spaces in between (in coordinate)
    @Test
    void tqAddTargets_19() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(1, 3)"));
    }

    // properly formatted but spaces in between (between coordinates)
    @Test
    void tqAddTargets_20() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(1,3). (1,3)"));
    }

    // properly formatted with leading 0
    @Test
    void tqAddTargets_21() {
        TargetQueue test = new TargetQueue();
        test.addTargets("(03,05)");
        assertEquals(test.dequeue(), new Position(3, 5));
        assertTrue(test.isEmpty());
    }

    // multiple .
    void tqAddTargets_22() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12,2)..(2,212133)."));
    }

    // extra (
    void tqAddTargets_23() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("((12,2).(2,212133)"));
    }

    // extra )
    void tqAddTargets_24() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12,2)).(2,212133)"));
    }

    // extra ()
    void tqAddTargets_25() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("((12,2)).(2,212133)"));
    }

    // extra ) at the end
    void tqAddTargets_26() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("(12,2).((2,212133))"));
    }

    //extra () wrapping string
    void tqAddTargets_27() {
        TargetQueue test = new TargetQueue();
        assertThrows(IllegalArgumentException.class,
                () -> test.addTargets("((12,2).(2,212133))"));
    }

    // Testing clear function
    @Test
    void tqClear_1() {
        TargetQueue test1 = new TargetQueue();
        TargetQueue test2 = new TargetQueue();
        test1.addTargets("(0,0).(1,1).(0,3)");
        test1.clear();
        assertTrue(test1.isEmpty());
        assertEquals(test1, test2);
    }
}
