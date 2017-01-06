package dsa.singlylinkedlist;


/**
 * @author deveshkandpal
 *Shifts each element of the LinkedList by 'n' elements in a circular fashion
 */
public class LinkedListShift {

	public LinkedList shift(LinkedList input, int n) {
		int len = input.length();
		int k = len - n;

		while (k > 0) {
			Node current = input.getHead();
			Node target = current;
			int shift = len - 1;
			while (shift > 0) {
				target = target.getNextNode();
				shift--;
			}
			input.setHead(current.getNextNode());
			current.setNextNode(target.getNextNode());
			target.setNextNode(current);
			k--;
		}

		return input;
	}

	public void shift2(LinkedList ll, int n) {

		Node ref1 = ll.getHead();
		Node ref2 = ll.getHead();
		// move ref1 by n
		for (int i = 0; i < n; i++) {
			ref1 = ref1.getNextNode();
		}
		while (ref1.getNextNode() != null) {
			ref1 = ref1.getNextNode();
			ref2 = ref2.getNextNode();
		} // now ref1 should be pointing to the last node

		ref1.setNextNode(ll.getHead()); // last node now connects with the head
		// we need to make the next node of ref2 as the head of the list
		ll.setHead(ref2.getNextNode());
		ref2.setNextNode(null);

	}

	public Node findKth(LinkedList input, int n) {

		int len = input.length();
		if (n > len) {
			return null;
		}
		int k = len - n;
		Node current = input.getHead();
		while (k > 0) {
			current = current.getNextNode();
			k--;
		}
		return current;
	}

	public LinkedList removeDuplicates(LinkedList input) {
		Node current = input.getHead();
		while (current != null) {
			Node next = current;
			while (next != null && next.getNextNode() != null) {
				if (current.getData() == next.getNextNode().getData()) {
					next.setNextNode(next.getNextNode().getNextNode());
				}
				next = next.getNextNode();
			}
			current = current.getNextNode();
		}

		return input;
	}

	public boolean isCyclic(LinkedList input) {

		if (input.getHead() == null || input.getHead().getNextNode() == null) {
			return false;
		}
		Node tortoise = input.getHead();
		Node hare = input.getHead().getNextNode();
		while (true) {
			if (hare == tortoise || hare.getNextNode() == tortoise) {
				return true;
			} else {
				if (tortoise.getNextNode() != null && hare.getNextNode() != null
						&& hare.getNextNode().getNextNode() != null) {
					tortoise = tortoise.getNextNode();
					hare = hare.getNextNode().getNextNode();
				} else {
					return false;
				}

			}
		}

	}

	public Node deleteNode(Node v) {

		if (v != null && v.getNextNode() != null) {
			v.setData(v.getNextNode().getData());
			v.setNextNode(v.getNextNode().getNextNode());
		}
		return v;
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.insertNodeAtHead(10);
		ll.insertNodeAtHead(20);
		ll.insertNodeAtHead(30);
		ll.insertNodeAtHead(40);

		LinkedListShift lls = new LinkedListShift();
		lls.shift2(ll, 2);

		System.out.println(ll);

		// LinkedList output = lls.removeDuplicates(ll);
		// System.out.println(output);

	}

}
