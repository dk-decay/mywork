package dsa.singlylinkedlist;

/**
 * @author deveshkandpal
 *Linked List Implementation that supports insert at head, removeAtHead, find and length method
 */
public class LinkedList {

	private Node head;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
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

	public void insertNodeAtHead(int data) {
		Node node = new Node(data);
		node.setNextNode(this.head);
		this.head = node;
	}

	public void removeAtHead() {
		this.head = this.head.getNextNode();
	}

	public int length() {
		Node current = this.head;
		int count = 0;
		while (current != null) {
			count++;
			current = current.getNextNode();
		}

		return count;
	}

	public Node find(int data) {
		Node current = this.head;
		while (current != null) {
			if (current.getData() == data) {
				return current;
			}
			current = current.getNextNode();
		}
		return null;
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.insertNodeAtHead(10);
		ll.insertNodeAtHead(20);
		ll.insertNodeAtHead(30);
		ll.insertNodeAtHead(40);
		System.out.println(ll.find(210));

	}

}
