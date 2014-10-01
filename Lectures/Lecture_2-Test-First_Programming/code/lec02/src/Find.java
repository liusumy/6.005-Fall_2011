
public class Find {
	/**
	 * Find the first occurrence of x in sorted array a.
	 * @param x value to find
	 * @param a array sorted in increasing order (a[0] <= a[1] <= ... <= a[n-1])
	 * @return lowest i such that a[i] == x, or -1 if x not found in a.
	 */
	public static int find(int x, int[] a) {
		// return linearSearch(x, a);
		return binarySearchInRange(x, a, 0, a.length);
	}
	
	/*
	 * Same spec as find(). Uses linear search.
	 */
	/*
	private static int linearSearch(int x, int[] a) {
		for (int i = 0; i < a.length; i = i + 1) {
			if (x == a[i]) {
				return i;
			}
		}
		return -1;
	}
	*/

	/**
	 * Find the first occurrence of x in sorted array a[first..max-1].
	 * @param x value to find
	 * @param a array sorted in increasing order
	 * 			(a[0] <= a[1] <= ... <= a[n-1])
	 * @param first first index of range.
	 * 				Requires 0 <= first <= a.length.
	 * @param max one beyond the last index of range.
	 * 			  Requires 0<= max <= a.length, and first <= max.
	 * @return lowest i such that first <= i <= last and a[i] == x,
	 * 		   or -1 if there's no such i.
	 */
	private static int binarySearchInRange(int x, int[] a, int first, int max) {
		if (first >= max) {
			return -1;	// range has dwindled to nothingness
		}
		
		int mid = (first + max) / 2;
		if (x < a[mid]) {
			return binarySearchInRange(x, a, first, mid);
		} else if (x > a[mid]) {
			return binarySearchInRange(x, a, mid+1, max);
		} else {
			// x == a[mid] ... we found it, but check if it's the first
			if (mid > 0 && (x == a[mid-1])) {
				return binarySearchInRange(x, a, first, mid);
			} else {
				return mid;
			}
		}
	}
	
}
