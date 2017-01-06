package dsa.stacksandqueues;

public class LinkedListStack {

	private Node head;
	private int size;

	public LinkedListStack() {

	}

	public LinkedListStack(int size) {
		this.size = size;
	}

	public void push(int el) throws Exception {
		if (length() > size) {
			throw new Exception("Stack Full Exception");
		}
		Node node = new Node(el);
		node.setNext(this.head);
		this.head = node;

	}

	public int pop() throws Exception {
		if (length() == 0) {
			throw new Exception("Stack Empty Exception");
		}
		Node node = this.head;
		this.head = this.head.getNext();
		return node.getData();
	}

	public int length() {
		Node current = this.head;
		int count = 0;
		while (current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}

	@Override
	public String toString() {
		String result = "{";
		Node current = this.head;
		while (current != null) {
			result = result + "data : " + current.getData() + ",";
			current = current.getNext();
		}
		result = result + "}";
		return result;
	}

	public static void main(String[] args) throws Exception {
		LinkedListStack llq = new LinkedListStack(5);
		llq.push(10);
		llq.push(20);
		llq.push(30);
		System.out.println(llq);
		llq.pop();
		System.out.println(llq);
		llq.push(40);
		System.out.println(llq);
		llq.pop();
		System.out.println(llq);

	}
}
