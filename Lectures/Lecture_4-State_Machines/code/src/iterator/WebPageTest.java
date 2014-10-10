package iterator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class WebPageTest {
	// Testing strategy
	//	l.size: 0, 1, n
	//	contents: no tags, one tag, all tags
	//	position: tag at start, tag in middle, tag at end
	//	kind of element: <foo>, </foo>, word, empty string
	
	@Test
	public void stripTagsTestEmptyList() {
		ArrayList<String> l = new ArrayList<String>();
		WebPage.stripTags(l);
		assertEquals(new ArrayList<Integer>(), l);
	}
	
	@Test
	public void stripTagsTestNoTagsOneWord() {
		ArrayList<String> l = new ArrayList<String>(
				Arrays.asList(new String[] {"hello"}));
		WebPage.stripTags(l);
		assertEquals(new ArrayList<String>(
				Arrays.asList(new String[] {"hello"})),
				l);
	}
	
	@Test
	public void stripTagsTestNoTagsSeveralWords() {
		ArrayList<String> l = new ArrayList<String>(
				Arrays.asList(new String[] {"now", "is", "", "time"}));
		WebPage.stripTags(l);
		assertEquals(new ArrayList<String>(
				Arrays.asList(new String[] {"now", "is", "", "time"})),
				l);
	}
	
	@Test
	public void stripTagsTestOneStartTagsSeveralWords() {
		ArrayList<String> l = new ArrayList<String>(
				Arrays.asList(new String[] {"<img>", "now", "time"}));
		WebPage.stripTags(l);
		assertEquals(new ArrayList<String>(
				Arrays.asList(new String[] {"now", "time"})),
				l);
	}
	
	@Test
	public void stripTagsTestOneMiddleTagsSeveralWords() {
		ArrayList<String> l = new ArrayList<String>(
				Arrays.asList(new String[] {"now", "<img>", "time"}));
		WebPage.stripTags(l);
		assertEquals(new ArrayList<String>(
				Arrays.asList(new String[] {"now", "time"})),
				l);
	}
	
	@Test
	public void stripTagsTestOneEndTagsSeveralWords() {
		ArrayList<String> l = new ArrayList<String>(
				Arrays.asList(new String[] {"now", "time", "<img>"}));
		WebPage.stripTags(l);
		assertEquals(new ArrayList<String>(
				Arrays.asList(new String[] {"now", "time"})),
				l);
	}
	
	@Test
	public void stripTagsTestAllTags() {
		ArrayList<String> l = new ArrayList<String>(
				Arrays.asList(new String[] {"<b>", "<a>", "</a>"}));
		WebPage.stripTags(l);
		System.out.println(l);
		assertEquals(new ArrayList<Integer>(), l);
	}

}
