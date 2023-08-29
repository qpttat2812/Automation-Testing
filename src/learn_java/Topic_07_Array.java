package learn_java;

import java.util.ArrayList;
import java.util.List;

public class Topic_07_Array {
	
	public static int MaxElementOfArray(int[] arr) {
		int max = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}
	
	public static int SumOfFirstAndLastElement(int[] arr) {
		return arr[0] + arr[arr.length-1];
	}
	
	public static ArrayList<Integer> EvenElementOfArray(int[] arr) {
		ArrayList<Integer> arr1 = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				arr1.add(arr[i]);
			}
		}
		return arr1;
	}
	
	public static int SumOfOddElementGreatherThanZero(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] % 2 != 0) && (arr[i] > 0)) {
				sum += arr[i];
			}
		}
		
		return sum;
	}
	
	public static ArrayList<Integer> NumberLessThanTen(int[] arr) {
		ArrayList<Integer> arr1 = new ArrayList<>();
		int i = 0;
		while (i < arr.length) {
			if ((arr[i] >= 0) && (arr[i] < 11)) {
				arr1.add(arr[i]);
			}
			i++;
		}
		return arr1;
	}
	
	public static void SumAndMeanOfArray(int[] arr) {
		int sum = 0, i = 0;
		float mean = 0;
		
		do {
			sum += arr[i];
			i++;
		} while (i < arr.length);
		
		mean = sum / (float)(arr.length);
		
		System.out.println("Sum of array = " + sum);
		System.out.println("Mean of array = " + mean);
	}

	public static void main(String[] args) {
		int[]a = {9, 7, 6, 87, 19, 12};
		int[]b = {-9, -7, -6, -87, 19, 12, 11};
		int[]c = {3, -4, 1, 0, 9, 20, -6, 2, 5};
		System.out.println("Max number is " + MaxElementOfArray(a));
		System.out.println("Sum of first and last element of array is " + SumOfFirstAndLastElement(a));
		System.out.println("Even elements of array is " + EvenElementOfArray(a));
		System.out.println("Sum of positive odd number of elemement is " + SumOfOddElementGreatherThanZero(b));
		System.out.println("Element of array is between 0 and 10 = " + NumberLessThanTen(c));
		SumAndMeanOfArray(c);
	}
}
