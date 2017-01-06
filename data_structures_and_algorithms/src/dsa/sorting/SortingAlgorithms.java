package dsa.sorting;

import java.util.stream.IntStream;

import dsa.doublylinkedlist.DoublyLinkedList;
import dsa.doublylinkedlist.DoublyNode;


/**
 * @author deveshkandpal
 * This Class is supporting following sorting algorithms - Bubble Sort, Selection Sort, Insertion Sort,Merge Sort
 * and Insertion Sort on Doubly Linked List. Shell , Radix, Counting and Heap Sort are to be added next.
 *
 */
public class SortingAlgorithms {

	public int[] bubbleSort(int[] input) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length - 1 - i; j++) {
				if (input[j] > input[j + 1]) {
					int temp = input[j + 1];
					input[j + 1] = input[j];
					input[j] = temp;
				}
			}
		}

		return input;
	}

	public int[] selectionSort(int[] input) {
		for (int i = 0; i < input.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < input.length; j++) {
				if (input[j] < input[minIndex]) {
					minIndex = j;
				}
			}
			int temp = input[minIndex];
			input[minIndex] = input[i];
			input[i] = temp;
		}
		return input;
	}

	public int[] insertionSort(int[] input) {

		for (int i = 0; i < input.length; i++) {
			int current = input[i];
			int j = i - 1;
			while (j >= 0 && input[j] > current) {
				input[j + 1] = input[j];
				j--;
			}
			input[j + 1] = current;

		}
		return input;
	}

	
	public int[] mergeSort(int[] arr, int start, int end) {

		if (start < end) {
			int middle = (int) Math.floor((start + end) / 2);
			mergeSort(arr, start, middle);
			mergeSort(arr, middle + 1, end);
			merge(arr, start, middle, end);
		}

		return arr;
	}

	public int[] merge(int[] input, int start, int middle, int end) {

		int[] arr1 = new int[middle - start + 1];
		int[] arr2 = new int[end - middle];

		int i = 0;
		int j = 0;

		while (i < arr1.length) {
			arr1[i] = input[start + i];
			i++;
		}

		while (j < arr2.length) {
			arr2[j] = input[middle + 1 + j];
			j++;
		}
		i = 0;
		j = 0;
		for (int k = 0; k < (arr1.length + arr2.length); k++) {

			if (i < arr1.length && j < arr2.length) {
				if (arr1[i] < arr2[j]) {
					input[start + k] = arr1[i];
					i++;
				} else {
					input[start + k] = arr2[j];
					j++;
				}

			} else {
				if (i >= arr1.length) {
					input[start + k] = arr2[j];
					j++;
				} else {
					input[start + k] = arr1[i];
					i++;
				}
			}
		}

		return input;
	}

	
	
	public DoublyLinkedList insertionSortWithLinkedList(DoublyLinkedList input) {

		DoublyNode start = input.getHead().getNext();
		while (start != null) {
			DoublyNode previous = start.getPrevious();
			while (previous != null && previous.getData() > start.getData()) {

				previous = previous.getPrevious();
			}
			// previous next node becomes start;
			if (start.getPrevious() != null) {
				start.getPrevious().setNext(start.getNext());
			}
			if (start.getNext() != null) {
				start.getNext().setPrevious(start.getPrevious());
			}
			start.setPrevious(previous);
			if (previous != null) {
				if (previous.getNext() != null) {
					previous.getNext().setPrevious(start);
				}

				start.setNext(previous.getNext());
				previous.setNext(start);
			} else {
				// if (start != input.getHead()) {
				input.getHead().setPrevious(start);
				start.setNext(input.getHead());
				input.setHead(start);
				// }

			}

			start = start.getNext();
		}
		return input;
	}

	public static void main(String[] args) {
		SortingAlgorithms sat = new SortingAlgorithms();
		int[] input = new int[] { 10, 9, 8, 7, 6, 5, 4 };
		int[] output = sat.insertionSort(input);
		IntStream.of(output).forEach(a -> System.out.println(a));

		DoublyLinkedList dll = new DoublyLinkedList();
		dll.insertAtHead(10);
		dll.insertAtHead(20);
		dll.insertAtHead(30);
		dll.insertAtHead(40);
		dll.insertAtHead(50);
		DoublyLinkedList out = sat.insertionSortWithLinkedList(dll);
		System.out.println(out.toString());

	}
}
