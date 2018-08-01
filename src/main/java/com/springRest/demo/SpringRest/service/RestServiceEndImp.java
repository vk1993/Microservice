package com.springRest.demo.SpringRest.service;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.springRest.demo.SpringRest.utility.BadRequest;

@Service
public class RestServiceEndImp implements RestServiceEnd {

	@Override
	public int getNthFebbonic(long n) {
		int n1 = 1;
		int n2 = 1;
		int n3 = 0;
		int i;
		if (n <= n1) {
			return n1;
		}
		for (i = 2; i < n; ++i)// loop starts from 2 because 1 and 1 are already printed
		{
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		}
		return n3;
	}

	@Override
	public String getReverse(String inputString) {

		String[] words = inputString.split(" ");
		String reversedString = "";
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String reverseWord = "";
			for (int j = word.length() - 1; j >= 0; j--) {
				reverseWord = reverseWord + word.charAt(j);
			}
			reversedString = reversedString + reverseWord + " ";
		}
		return reversedString.trim();
	}

	@Override
	public String getTriangle(int a, int b, int c) {
//		    if (a == b && b == c) return "Equilateral";
////		    if (a >= b+c || c >= b+a || b >= a+c) return "Invalid";
////		    if (b==c || a==b || c==a) return "Isosceles";
////		    return "scalene";
		if((a + b > c)) {
			if ((a == b) && (b == c)) return "Equilateral";
			if ((a == b) & (b != c) || (b == c) & (c != a)) return "Isosceles";
			if ((a != b) & (b != c)) return "Scalene";
		}
			return "Invalid";

	}

	@Override
	public Map<String, Integer[]> getArray(Map<String, Integer[]> array) {
		Set<Integer> tree = new TreeSet<>();
		for (Integer[] value : array.values()) {
			for (Integer integer : value) {
				tree.add(integer);
			}
		}
		array.clear();
		array.put("Array", tree.toArray(new Integer[tree.size()]));
		return array;
	}
}
