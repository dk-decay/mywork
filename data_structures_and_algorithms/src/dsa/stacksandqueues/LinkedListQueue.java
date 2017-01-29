package dsa.stacksandqueues;

public class LinkedListQueue {

	private Node head;
	private int size;

	public LinkedListQueue() {

	}

	public LinkedListQueue(int size) {
		this.size = size;
	}

	public void enqueue(int el) throws Exception {
		if (length() > size) {
			throw new Exception("Queue Full Exception");
		}
		Node node = new Node(el);
		node.setNext(this.head);
		this.head = node;
	}

	public int dequeue() throws Exception {
		if (length() <= 0) {
			throw new Exception("Queue Empty exception");
		}

		if (length() == 1) {
			int data = this.head.getData();
			this.head = null;
			return data;
		}
		Node current = this.head;

		while (current.getNext() != null) {
			if (current.getNext().getNext() != null) {
				current = current.getNext();
			} else {
				current.setNext(null);
			}

		}
		return current.getData();
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
		LinkedListQueue llq = new LinkedListQueue(5);
		llq.enqueue(10);
		llq.enqueue(20);
		System.out.println(llq);
		llq.dequeue();
		System.out.println(llq);

	}
}
