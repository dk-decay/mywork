package dsa.stacksandqueues;

public class ArrayBasedStacks {

	private int top = -1;
	private int[] data;

	public ArrayBasedStacks() {
		this.data = new int[1024];
	}

	public ArrayBasedStacks(int size) {
		this.data = new int[size];
	}

	public int push(int el) throws Exception {
		if (++top >= data.length) {
			updateArr();
			data[top] = el;
		} else {
			data[top] = el;
		}
		return el;
	}

	public void updateArr() {
		System.out.println("Updating array");
		int[] newArr = new int[2 * data.length];
		for (int i = 0; i < data.length; i++) {
			newArr[i] = data[i];
		}
		data = newArr;
	}

	public String toString() {
		String result = "{";
		for (int d : data) {
			result = result + "data :" + d + ",";
		}
		result = result + "}";
		return result;
	}

	public int peek() throws Exception {
		if (top == -1) {
			throw new Exception("Stack empty exception");
		} else {
			return data[top];
		}
	}

	public int pop() throws Exception {
		int el;
		if (top == -1) {
			throw new Exception("Stack empty exception");
		} else {
			el = data[top];
			data[top] = 0;
			top--;
			return el;
		}
	}

	public static void main(String[] args) throws Exception {
		ArrayBasedStacks s = new ArrayBasedStacks(2);
		s.push(10);
		s.push(20);
		System.out.println(s.toString());
		s.push(30);
		System.out.println(s.toString());
		s.push(50);
		System.out.println(s.toString());
		
	}

}
