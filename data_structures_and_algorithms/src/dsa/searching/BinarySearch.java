package dsa.searching;



public class BinarySearch {

	public static int search(int[] input, int element) {
		int index = -1;
		int start = 0;
		int end = input.length - 1;
		boolean status = false;
		while (!status) {
			int floorIndex = (int) Math.floor((end + start) / 2);
			if (input[floorIndex] == element) {
				index = floorIndex;
				status = true;
			} else if (start == end) {
				status = true;
			} else if (input[floorIndex] > element) {
				end = floorIndex;
			} else {
				start = floorIndex + 1;
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		
		
	}
}
