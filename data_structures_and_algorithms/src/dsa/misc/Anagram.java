package dsa.misc;

import java.util.Arrays;

public class Anagram {

	public boolean checkAnagram(char[] s1, char[] s2) {
		int[] originalString = new int[256];
		int[] newString = new int[256];
		for (int i = 0; i < s1.length; i++) {
			int location = (int) s1[i];
			originalString[location] = ++originalString[location];
		}
		for (int i = 0; i < s2.length; i++) {
			int location = (int) s2[i];
			newString[location] = ++newString[location];
		}
		return Arrays.equals(originalString, newString);
	}

	public static void main(String[] args) {
		System.out.println("hello:" + new Anagram().checkAnagram("abdcd".toCharArray(), "ddcba".toCharArray()));
	}
}
