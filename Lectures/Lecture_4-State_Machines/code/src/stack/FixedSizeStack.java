package stack;

/**
 * A stack is a mutable object representing a last-in-first-out
 * stack of elements (of an arbitrary type E).
 * Elements can be pushed onto the stack, and then popped off
 * in the reverse order that they were pushed.
 * A FixedSizeStack can hold only specified amount of elements.
 */
public class FixedSizeStack<E> {
	// FixedSizeStack is a *generic* class, which means it takes a type parameter.
	// The type parameter E must be filled in with a object type
	// when using this class: e.g. FixedSizeStack<String>, FixedSizeStack<Double>,
	// or FixedSizeStack<List<Integer>>. From FixedSizeStack's point of view, however,
	// the element type is just E.
	
	private final E[] elements;
	private int n;
	// elements[] contains the elements in the stack.
	// elements[0] is the oldest item pushed;
	// elements[n-1] is the latest item pushed,
	//					and the next to be popped.
	// If n == 0, then the stack is empty.
	// If n == elements.length, then the stack is full.
	
	/**
	 * Make a FixedSizeStack, initially empty.
	 */
	public FixedSizeStack(int maxSize) {
		// can not use "new E[n]" because of a subtle Java problem
		elements = (E[]) new Object[maxSize];
		this.n = 0;
	}
	
	/**
	 * Modifies this stack by pushing an element onto it.
	 * Requires: stack is no full, i.e. size() < maxSize
	 * @param e element to push on top
	 */
	public void push(E e) {
		elements[n] = e;
		n = n + 1;
	}
	
	/**
	 * Modifies this stack by popping off the top element.
	 * Requires: stack is not empty, i.e. size() > 0
	 * @return element on top of stack
	 */
	public E pop() {
		final E e = elements[size()-1];
		n = n - 1;
		return e;
	}
	
	/**
	 * @return number of element in the stack
	 */
	public int size() {
		return n;
	}
}
