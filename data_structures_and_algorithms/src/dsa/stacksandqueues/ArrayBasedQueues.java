package dsa.stacksandqueues;

public class ArrayBasedQueues {

	private int[] data;
	private int head = -1;
	private int tail = -1;

	public ArrayBasedQueues(int size) {
		this.data = new int[size];
	}

	public ArrayBasedQueues() {
		this.data = new int[1024];
	}

	public int enqueue(int el) throws Exception {

		if (head == -1) {
			this.data[0] = el;
			this.head++;
			this.tail++;
		} else {
			this.tail = findPosition();
			data[this.tail] = el;
		}
		return el;
	}

	public int findPosition() throws Exception {
		if (data.length - (this.tail - this.head + 1) % data.length > 0) {
			return (tail + 1) % data.length;
		}
		throw new Exception("Queue full exception");
	}

	public int dequeue() {

		int el = data[head];
		if (this.tail == this.head) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = findHeadPos();
		}
		return el; 
	}

	public int findHeadPos() {
		return (this.head + 1) % data.length;
	}

	public String toString() {
		String result = "{";
		int pos = this.head;
		while (pos < this.tail) {
			result = result + "data: " + data[pos] + ",";
			pos = (pos + 1) % this.data.length;
		}
		result = result + data[tail] + "}";
		return result;

	}

	public static void main(String[] args) throws Exception {
		ArrayBasedQueues q = new ArrayBasedQueues(4);
		q.enqueue(10);
		q.enqueue(20);
		q.enqueue(30);
		q.enqueue(40);
		System.out.println(q.toString());
		q.dequeue();
		 System.out.println(q.toString());
		q.dequeue();
		 System.out.println(q.toString());
		q.enqueue(50);
		 System.out.println(q.toString());
		q.enqueue(60);
		 System.out.println(q.toString());
	}
}
