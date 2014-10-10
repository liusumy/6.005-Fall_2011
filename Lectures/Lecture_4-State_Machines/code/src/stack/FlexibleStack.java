package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * A stack is a mutable object representing a last-in-first-out
 * stack of elements (of an arbitrary type E).
 * Elements can be pushed onto the stack, and then popped off
 * in the reverse order that they were pushed.
 * A FlexibleStack can hold an arbitrary number of elements.
 */
public class FlexibleStack<E> {
	// FlexibleStack is a *generic* class, which means it takes a type parameter.
	// The type parameter E must be filled in with a object type
	// when using this class: e.g. FlexibleStack<String>, FlexibleStack<Double>,
	// or FlexibleStack<List<Integer>>. From FlexibleStack's point of view, however,
	// the element type is just E.
	
	private final List<E> elements = new ArrayList<E>();
	// 'elements' contains the elements in the stack,
	// in order from oldest pushed (elements[0]) to
	// 			to the latest item pushed, and the
	//			next to be popped (elements[size-1])
	// If elements.size == 0, then the stack is empty.
	
	
	/**
	 * Make a FlexibleStack, initially empty.
	 */
	public FlexibleStack() {
		;
	}
	
	/**
	 * Modifies this stack by pushing an element onto it.
	 * @param e element to push on top
	 */
	public void push(E e) {
		elements.add(e);
	}
	
	/**
	 * Modifies this stack by popping off the top element.
	 * Requires: stack is not empty, i.e. size() > 0.
	 * @return element on top of stack
	 */
	public E pop() {
		final E e = elements.get(elements.size() - 1);
		elements.remove(elements.size() - 1);
		return e;
	}
	
	/**
	 * @return number of elements in the stack
	 */
	public int size() {
		return elements.size();
	}
}
