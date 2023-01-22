public class Matching {

	static void computeLPSArray(char[] pat, int M, int lps[]) { // Works Cited : https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
	int len = 0, i = 1;
	lps[0] = 0;
	while (i < M) {
	    if (pat[i] == pat[len]) {
		len++; lps[i] = len; i++;
	    }  else {
		if (len != 0) {
		    len = lps[len - 1];
		} else {
		    lps[i] = len; i++;
		}
            }
        }
    }
	
    static void badCharHeuristic( char []str, int size,int badchar[]) { // Works Cited: https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
    	for (int i = 0; i < NO_OF_CHARS; i++)
    		badchar[i] = -1;
    	for (int i = 0; i < size; i++)
          badchar[(int) str[i]] = i;
    }

    static int NO_OF_CHARS = 256;
    
    static int max (int a, int b) { return (a > b)? a: b; }
    
    public static int findKMP(char[] text, char[] pattern) {
		// Works Cited: Text Processing. CS 340. (n.d.). Retrieved November 30, 2022, from http://www2.cs.uregina.ca/~beattieb/CS340/ 
		// Works Cited: Implementation of KMP algorithm – C, C++, Java, and python. Techie Delight. (2021, November 23). Retrieved November 30, 2022, from https://www.techiedelight.com/implementation-kmp-algorithm-c-cpp-java/ 
		// Works Cited : https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
		
		// base case 1: pattern is null or empty
        if (pattern == null || pattern.length == 0) { return -1; } 
        // base case 2: text is NULL, or text's length is less than that of pattern's
        if (text == null || pattern.length > text.length) { return -1; } // Pattern not found 
        
        int m = pattern.length, n = text.length, comparison = 0;
        int lps[] = new int[m];
        int j = 0;
        computeLPSArray(pattern, m, lps);
 
        int i = 0;
        while ((n - i) >= (m - j)) {
        	comparison++;
            if (pattern[j] == text[i]) {
                j++;
                i++;
            }
            if (j == m) {
            	comparison++;
            	System.out.println("K_M_P: Pattern at index: " + (i - j));
            	System.out.println("Comparisons: " + comparison);
                return i;
            } else if (i < n && pattern[j] != text[i]){
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        } 
        System.out.println("K_M_P: SUBSTRING_NOT_FOUND");
		return -1; // return SUBSTRING_NOT_FOUND
	}

    public static int findBoyerMoore( char text[],  char pattern[]) { // Works Cited: https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
		// base case 1: pattern is null or empty
	    if (pattern == null || pattern.length == 0) { return -1; } 
	    // base case 2: text is NULL, or text's length is less than that of pattern's
	    if (text == null || pattern.length > text.length) { return -1; } // Pattern not found 
	    
		int m = pattern.length;
		int n = text.length;
		int comparison = 0;
		int badchar[] = new int[NO_OF_CHARS];
		badCharHeuristic(pattern, m, badchar);
	
		int s = 0;
		while(s <= (n - m)){
			comparison++;
			int j = m-1;
	        while(j >= 0 && pattern[j] == text[s+j]) {
	            j--;
	            comparison++;
	        }
	        if (j < 0) {
	            System.out.println("BoyerMoore: Pattern at index: " + s);
				System.out.println("Comparisons: " + comparison);
	            comparison++;
	        	return s;
	        } else {
				s += max(1, j - badchar[text[s+j]]);
			} 
		}
		System.out.println("BoyerMoore: SUBSTRING_NOT_FOUND");
		return -1;
		}	

	public static int findBrute(char[] text, char[] pattern) { 
		// Works Cited: Text Processing. CS 340. (n.d.). Retrieved November 30, 2022, from http://www2.cs.uregina.ca/~beattieb/CS340/
		// base case 1: pattern is null or empty
        if (pattern == null || pattern.length == 0) { return -1; } 
        // base case 2: text is NULL, or text's length is less than that of pattern's
        if (text == null || pattern.length > text.length) { return -1; } // Pattern not found 
		
		int m = pattern.length, n = text.length, j, comparison = 0;
		for(int i = 0; i < (n - m); i++) {
			j = 0; comparison++;
			while ((j < m) && (text[i + j] == pattern[j])) {
				j++; comparison++;
			}
			if(j == m) { 	
				System.out.println("Brute Force: Pattern at index " + i);
				System.out.println("Comparisons: " + comparison);
				return i; }
		}
		System.out.println("Brute Force: SUBSTRING_NOT_FOUND");
		return -1; // SUBSTRING_NOT_FOUND
	}
	
	
	public static int findRabinKarp(char[] text, char[] pattern) {
	    	// Works Cited: Text Processing. CS 340. (n.d.). Retrieved November 30, 2022, from http://www2.cs.uregina.ca/~beattieb/CS340/ 
	    	// Works Cited: https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
			// base case 1: pattern is null or empty
	        if (pattern == null || pattern.length == 0) { return -1; } 
	        // base case 2: text is NULL, or text's length is less than that of pattern's
	        if (text == null || pattern.length > text.length) { return -1; } // Pattern not found 
	    	
	        int M = pattern.length, N = text.length, i, j, p = 0, t = 0, h = 1, q = 101, comparison = 0; // q = prime number
	 
	        // The value of h would be "pow(d, M-1)%q"
	        for (i = 0; i < M - 1; i++)
	            h = (h * NO_OF_CHARS) % q;
	 
	        // Calculate the hash value of pattern and first window of text
	        for (i = 0; i < M; i++) {
	            p = (NO_OF_CHARS * p + pattern[i]) % q;
	            t = (NO_OF_CHARS * t + text[i]) % q;
	        }
	 
	        for (i = 0; i <= N - M; i++) { 
	        	comparison++;
	            if (p == t) {
	                for (j = 0; j < M; j++) { comparison++;
	                    if (text[i+j] != pattern[j]) {
	                        break;
	                    }
	                }
	                if (j == M) {
	                    System.out.println("Rabin Karp: Pattern at index " + i);
	                    System.out.println("Comparisons: " + comparison);
	                    comparison++;
	                    return i;
	                }
	            }
	            if (i < N - M) {
	                t = (NO_OF_CHARS * (t - text[i] * h) + text[i+M]) % q;
	                if (t < 0)
	                    t = (t + q);
	            }
	        }
	        System.out.println("Rabin Karp: SUBSTRING_NOT_FOUND");
			return -1;
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char pattern[] = "must".toCharArray();
		String web = "There are numerous benefits of writing unit tests; they help with regression, provide documentation, and facilitate good design. However, hard to read and brittle "
				+ "unit tests can wreak havoc on your code base. This article describes some best practices regarding unit test design for your .NET Core and .NET Standard projects."
				+ "In this guide, you learn some best practices when writing unit tests to keep your tests resilient and easy to understand. By John Reese with special thanks to Roy "
				+ "Osherove Why unit test? Less time performing functional test Functional tests are expensive. They typically involve opening up the application and performing a series "
				+ "of steps that you (or someone else) must follow in order to validate the expected behavior. These steps might not always be known to the tester. They'll have to reach "
				+ "out to someone more knowledgeable in the area in order to carry out the test. Testing itself could take seconds for trivial changes, or minutes for larger changes. "
				+ "Lastly, this process must be repeated for every change that you make in the system.";
		char[] webText = web.toCharArray();

		int compKMP = 			findKMP(webText, pattern);
		int compBoyerMoore = 	findBoyerMoore(webText, pattern);	
		int compBruteForce = 	findBrute(webText, pattern);
		int compRabinKarp = 	findRabinKarp(webText, pattern);
	}
}
