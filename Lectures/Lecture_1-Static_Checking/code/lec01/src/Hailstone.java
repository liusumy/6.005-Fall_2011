import java.util.List;
import java.util.ArrayList;

public class Hailstone {
	private static final int MAX_VAL = 1000;
	
	/**
	 * Compute a hailstone sequence.
	 * See http://en.wikipedia.org/wiki/Collatz_conjecture#Statement_of_the_problem
	 * @param n	Starting number for sequence. Assume n > 0.
	 * @return hailstone sequence starting with n and ending with 1.
	 */
	public static List<Integer> hailstoneSequence(int n) {
		final List<Integer> hs = new ArrayList<Integer>();
		while (n != 1) {
			hs.add(Integer.valueOf(n));
			if (isEven(n)) {
				n = n / 2;
			} else {
				n = (n * 3) + 1;
			}
		}
		hs.add(n);	
		return hs;
	}
	
	/**
	 * Check if an positive integer is an even number.
	 * @param n The integer to be check for parity. Assume n > 0.
	 * @return true if the positive integer is an even number,
	 * or false if the positive integer is an odd number.
	 */
	private static boolean isEven(final int n){
		if ((n % 2) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Find the largest element in a list.
	 * @param l list of elements. Require l to be nonempty
	 * and all elements to be nonnegtive.
	 * @return the largest element in l
	 */
	private static int max(final List<Integer> intSeq) {
		int max = 0;
		for (int x : intSeq) {
			max = Math.max(max, x);
		}
		return max;
	}
	
	/**
	 * Main program. Print the peak of the hailstone
	 * sequence for a range of starting n's.
	 * */
	public static void main(String[] args) {
		int n = 1;
		for (n = 1; n <= MAX_VAL; n = n + 1) {
			List<Integer> l = hailstoneSequence(n);
			System.out.println(n + ">>" + max(l));
		}
	}
}
