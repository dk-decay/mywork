package dsa.singlylinkedlist;

public class DoublyEndedLinkedList {

	private Node head;
	private Node tail;

	public void addAtTail(int data) {
		Node node = new Node(data);
		if (this.head == null) {
			this.head = node;
			this.head.setNextNode(node);
			this.tail = node;
		} else {
			this.tail.setNextNode(node);
			this.tail = node;
		}

	}

	@Override
	public String toString() {
		String result = "{";
		Node current = this.head;
		while (current != null) {
			result = result + "data: " + current.getData() + ",";
			current = current.getNextNode();
		}
		result = result + "}";
		return result;
	}

	public static void main(String[] args) {
		DoublyEndedLinkedList ll = new DoublyEndedLinkedList();
		ll.addAtTail(20);
		ll.addAtTail(30);
		ll.addAtTail(40);
		ll.addAtTail(50);
		ll.addAtTail(60);
		System.out.println(ll);

	}
}
