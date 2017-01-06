package dsa.singlylinkedlist;

public class SortedLinkedList {

	private Node head;

	public void sortedInsertion(int data) {

		Node node = new Node(data);
		// head is empty
		if (this.head == null) {
			this.head = node;
			return;
		}
		// compare with head
		if (this.head.getData() > data) {
			node.setNextNode(this.head);
			this.head = node;
			return;
		}

		Node currentNode = this.head;
		// compare with all others
		while (currentNode != null) {
			if (currentNode.getNextNode() != null) {
				if (currentNode.getNextNode().getData() > data) {
					node.setNextNode(currentNode.getNextNode());
					currentNode.setNextNode(node);
					return;
				} else {
					currentNode = currentNode.getNextNode();
				}
			} else {
				currentNode.setNextNode(node);
				return;
			}

		}

	}

	@Override
	public String toString() {
		String result = "{";
		Node current = this.head;
		while (current != null) {
			result = result + "data : " + current.getData() + ",";
			current = current.getNextNode();
		}
		result = result + "}";
		return result;
	}

	public static void main(String[] args) {
		SortedLinkedList sll = new SortedLinkedList();

		sll.sortedInsertion(40);
		sll.sortedInsertion(20);

		sll.sortedInsertion(50);
		sll.sortedInsertion(45);
		sll.sortedInsertion(15);
		sll.sortedInsertion(10);
		sll.sortedInsertion(5);
		sll.sortedInsertion(100);

		System.out.println(sll.toString());
	}
}
