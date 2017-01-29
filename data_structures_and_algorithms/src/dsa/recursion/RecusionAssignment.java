package dsa.recursion;

import java.util.stream.IntStream;

public class RecusionAssignment {

	public int addRecusion(int a, int b) {
		if (b > 0) {
			return addRecusion(a + 1, b - 1);
		}
		return a;

	}

	public int move(int n, char from, char to, char inter, int acc) {

		if (n == 1) {
			System.out.println("Moving disc " + n + " from " + from + " to " + to);
			return acc + 1;
		} else {
			acc = move(n - 1, from, inter, to, acc);
			System.out.println("Moving disc " + n + " from " + from + " to " + to);
			acc++;
			return move(n - 1, inter, to, from, acc);
		}
	}

	public int sumOfSquares(int n, int sum) {

		if (n > 0) {
			return sumOfSquares(n - 1, (n * n) + sum);
		}
		return sum;
	}

	public int[] mergeSort(int[] input, int start, int end) {

		if (start < end) {
			int middle = (int) Math.floor((start + end) / 2.0);
			mergeSort(input, start, middle);
			mergeSort(input, middle + 1, end);
			merge(input, start, middle, end);

		}
		return input;
	}

	public int[] merge(int[] input, int start, int middle, int end) {
		int[] arr1 = new int[middle - start + 1];
		int[] arr2 = new int[end - middle];

		int i = 0, j = 0;

		for (int k = 0; k < arr1.length; k++) {
			arr1[k] = input[start + i];
			i++;
		}

		for (int k = 0; k < arr2.length; k++) {
			arr2[k] = input[middle + 1 + j];
			j++;
		}

		i = j = 0;
		for (int k = start; k <= end; k++) {

			if (i < arr1.length && j < arr2.length) {

				if (arr1[i] < arr2[j]) {
					input[k] = arr1[i];
					i++;
				} else {
					input[k] = arr2[j];
					j++;
				}
			} else {
				if (i >= arr1.length) {
					input[k] = arr2[j];
					j++;
				} else {
					input[k] = arr1[i];
					i++;
				}
			}

		}

		return input;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 10, 20, 90, 80, 70, 60, 50 };
		int[] output = new RecusionAssignment().mergeSort(input, 0, input.length - 1);
		IntStream.of(output).forEach(a -> System.out.println(a));

	}

}
