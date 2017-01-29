package dsa.singlylinkedlist;

public class GenericLinkedList<T> {

	private GenericNode<T> head;

	public void addAtStart(T data) {
		GenericNode<T> node = new GenericNode<T>(data);
		node.setNextNode(this.head);
		this.head = node;
	}

	public void removeAtStart() {
		this.head = this.head.getNextNode();
	}

	public int length() {
		int count = 0;
		GenericNode<T> current = this.head;
		while (current != null) {
			count++;
			current = current.getNextNode();
		}
		return count;
	}

	public GenericNode<T> find(T data) {
		GenericNode<T> current = this.head;
		while (current != null) {
			if (current.getData() == data) {
				return current;
			}
			current = current.getNextNode();
		}
		return null;
	}

	@Override
	public String toString() {
		String result = "{";
		GenericNode<T> current = this.head;
		while (current != null) {
			result = result + "Date : " + current.getData() + ",";
		}
		result = result + "}";
		return result;
	}

	public static void main(String[] args) {
		GenericLinkedList<Integer> ll = new GenericLinkedList<>();
		ll.addAtStart(20);
		ll.addAtStart(30);
		ll.addAtStart(40);
		System.out.println(ll.find(50));

	}

}
