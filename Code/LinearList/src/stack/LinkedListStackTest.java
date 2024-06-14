package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListStackTest {
    @Test
    public void test(){
        LinkedListStack<Integer> stack = new LinkedListStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertFalse(stack.push(4));
        assertEquals(stack.peek(),3);
        stack.pop();
        assertEquals(stack.peek(),2);
        stack.pop();
        assertEquals(stack.peek(),1);
        stack.pop();
        assertNull(stack.pop());
    }
}
