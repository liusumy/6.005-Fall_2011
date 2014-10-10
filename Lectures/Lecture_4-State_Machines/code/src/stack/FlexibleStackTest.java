package stack;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlexibleStackTest {

	@Test
	public void test() {
		
		// create a stack of strings, initial size should be zero.
		FlexibleStack<String> stringStack = new FlexibleStack<String>();
		assertEquals(stringStack.size(), 0);
		
		// push a string entity into stringStack,
		// the stringStack should expand to be of size 1.
		stringStack.push("Cats have nine lives.");
		assertEquals(stringStack.size(), 1);
		
		// push another string entity into stringStack,
		// the stringStack should expand to be of size 2.
		stringStack.push("One man's meat is another man's poison.");
		assertEquals(stringStack.size(), 2);
		
		// popping stringStack should return
		// "One man's meat is another man's poison.",
		// and the stack should shrink to be of size 1
		assertEquals(stringStack.pop(),
				"One man's meat is another man's poison.");
		assertEquals(stringStack.size(), 1);
		
		// popping again stringStack should return
		// "Cats have nine lives."
		// and the stack should become empty
		assertEquals(stringStack.pop(),
				"Cats have nine lives.");
		assertEquals(stringStack.size(), 0);
	}

}
