package iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class WebPage {
	/**
	 * Remove html tags from a list.
	 * Modifies l by removing elements of the form "<*>"
	 * @param l list of words and html tags
	 */
	public static void stripTags(ArrayList<String> l) {
		
		// using MyIterator
		/*
		MyIterator iter = new MyIterator(l);
		while (iter.hasNext()) {
			String s = iter.next();
			if (isTag(s)) {
				l.remove(s);
			}
		}
		*/
		
		// using for syntactic sugar
		/*
		for (String s : l) {
			if (isTag(s)) {
				l.remove(s);
			}
		}
		*/
		
		// using the ArrayList's builtin iterator
		Iterator<String> iter = l.iterator();
		while (iter.hasNext()) {
			String s = iter.next();
			if (isTag(s)) {
				iter.remove();
			}
		}
	}
	
	// returns true iff s is an html tag of the form "<*>" for any *
	private static boolean isTag(String s) {
		if ((s.startsWith("<")) && (s.endsWith(">"))) {
			return true;
		} else {
			return false;
		}
	}
}
