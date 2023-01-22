import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchingTest {
	char pattern[] = "iop".toCharArray();
	String web = "qwertyuiopasfghhjjkkzxcvbnm";
	char[] webText = web.toCharArray();
	
	@Test
	void testFindKMP() {
		int x = Matching.findKMP(webText, pattern);
		assertEquals(x, 7);
	}

	@Test
	void testFindBoyerMoore() {
		int x = Matching.findBoyerMoore(webText, pattern);
		assertEquals(x, 7);
	}

	@Test
	void testFindBrute() {
		int x = Matching.findBrute(webText, pattern);
		assertEquals(x, 7);
	}

	@Test
	void testFindRabinKarp() {
		int x = Matching.findRabinKarp(webText, pattern);
		assertEquals(x, 7);
	}
	
	@Test
	void testMax() {
		int x = Matching.max(5, 7);
		assertEquals(x, 7);
	}
	
}
