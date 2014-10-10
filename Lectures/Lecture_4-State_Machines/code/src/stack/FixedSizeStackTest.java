package stack;

import static org.junit.Assert.*;

import org.junit.Test;

public class FixedSizeStackTest {

	@Test
	public void test() {
		
		// create a string stack of size 3, initial size should be zero.
		final int STACK_SIZE = 3;
		FixedSizeStack<String> stringStack = new FixedSizeStack<String>(STACK_SIZE);
		assertEquals(stringStack.size(), 0);
		
		// push a string entity into stringStack,
		// the stringStack should expand to be of size 1.
		stringStack.push("USA");
		assertEquals(stringStack.size(), 1);
		
		// push another string entity into stringStack,
		// the stringStack should expand to be of size 2.
		stringStack.push("SINO");
		assertEquals(stringStack.size(), 2);
		
		// push one more string entity into stringStack,
		// the stringStack should expand to be of size 3.
		stringStack.push("ROME");
		assertEquals(stringStack.size(), 3);
		
		// popping stringStack should return "ROME",
		// and the stack should shrink to be of size 2
		assertEquals(stringStack.pop(), "ROME");
		assertEquals(stringStack.size(), 2);
		
		// popping stringStack should return "SINO",
		// and the stack should shrink to be of size 1
		assertEquals(stringStack.pop(), "SINO");
		assertEquals(stringStack.size(), 1);
		
		// popping stringStack should return "USA",
		// and the stack should shrink to become empty
		assertEquals(stringStack.pop(), "USA");
		assertEquals(stringStack.size(), 0);
	}

}
