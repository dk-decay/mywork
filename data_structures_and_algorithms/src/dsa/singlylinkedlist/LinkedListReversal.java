package dsa.singlylinkedlist;

/**
 * @author deveshkandpal
 *Given a linked list , returns a reversed LL
 */
public class LinkedListReversal {

	public LinkedList reverse(LinkedList input) {

		Node current = input.getHead();
		Node next = null;
		Node prev = null; // 40,30,20,10, null
		while (current != null) { // 40
			next = current.getNextNode();
			current.setNextNode(prev);
			prev = current;
			current = next;
			
		}
		input.setHead(prev);
		return input;
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.insertNodeAtHead(10);
		ll.insertNodeAtHead(20);
		ll.insertNodeAtHead(30);
		ll.insertNodeAtHead(40);
		new LinkedListReversal().reverse(ll);
		System.out.println(ll.toString());

	}

}
