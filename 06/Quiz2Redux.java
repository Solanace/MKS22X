import java.util.*;
public class Quiz2Redux {
    /*Returns an ArrayList<String> that contains all subsets of the
   *characters of String s. Assume s has no duplicate characters.
   *the characters should appear in the same order that they occur 
   *in the original string.
   */

    public static ArrayList<String> combinations(String s) {
	ArrayList<String> words = new ArrayList<String>();
	help(s, words , 0, "");
	Collections.sort(words);
	return words;
    }
    
    private static void help(String s, ArrayList<String> words, int level, String temp) {
	if (level < s.length()) {
	    help(s, words, level + 1, temp);
	    help(s, words, level + 1, temp + s.charAt(level));
	}
	else {
	    words.add(temp);
	}
    }

    public static void main(String[] args) {
	System.out.println(combinations("a"));
	System.out.println(combinations("ab"));
	System.out.println(combinations("abc"));
	System.out.println(combinations("abcd"));
    }
}

