package dsa.doublylinkedlist;



public class DoublyLinkedList {

	private DoublyNode head;

	public void insertAtHead(int data) {
		DoublyNode node = new DoublyNode(data);
		if (this.head == null) {
			this.head = node;
			return;
		}
		node.setNext(this.head);
		this.head.setPrevious(node);
		this.head = node;

	}

	public int length() {
		int count = 0;
		DoublyNode current = this.head;
		while (current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}

	@Override
	public String toString() {
		String result = "{";
		DoublyNode current = this.head;
		while (current != null) {
			result = result + "Data : " + current.getData() + ",";
			current = current.getNext();
		}
		result = result + "}";
		return result;
	}

	public DoublyNode getHead() {
		return head;
	}

	public void setHead(DoublyNode head) {
		this.head = head;
	}

	
}
