package dsa.stacksandqueues;

public class DoubleEndedQueues {

	private int[] data;
	private int head = -1;
	private int tail = -1;

	public DoubleEndedQueues() {
		this.data = new int[10];
	}

	public DoubleEndedQueues(int size) {
		this.data = new int[size];
	}

	public int enqueueAtTail(int el) throws Exception {

		if (head == -1) {
			data[0] = el;
			head++;
			tail++;
		} else {
			this.tail = findPosition(false);
			data[this.tail] = el;
		}
		return el;
	}

	public int enqueueAtHead(int el) throws Exception {
		if (head == -1) {
			data[0] = el;
			head++;
			tail++;
		} else {
			this.head = findPosition(true);
			data[this.head] = el;
		}
		return el;
	}

	public int dequeueAtHead() {

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

	public int dequeueAtTail() {

		int el = data[tail];
		if (this.tail == this.head) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.tail = findTailPos();
		}
		return el;
	}

	public int findTailPos() {
		return (this.tail - 1 + data.length) % data.length;
	}

	public int findPosition(boolean isHead) throws Exception {

		if (isEmpty()) {
			if (!isHead) {
				return (tail + 1) % data.length;
			}
			return (head - 1 + data.length) % data.length;
		}

		throw new Exception("Stack Full Exception");
	}

	public boolean isEmpty() {
		if (data.length - (this.tail - this.head + 1) % data.length > 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		DoubleEndedQueues deq = new DoubleEndedQueues(5);
		deq.enqueueAtHead(10);
		deq.enqueueAtTail(20);
		deq.enqueueAtHead(30);
		deq.enqueueAtTail(40);
		deq.enqueueAtTail(50);
		deq.dequeueAtHead();
		deq.dequeueAtTail();
		deq.dequeueAtTail();
		deq.dequeueAtTail();
		deq.dequeueAtHead();
	}

}
