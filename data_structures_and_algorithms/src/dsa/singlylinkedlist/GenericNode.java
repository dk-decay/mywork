package dsa.singlylinkedlist;

public class GenericNode<T> {

	private T data;
	private GenericNode<T> nextNode;

	public GenericNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public GenericNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(GenericNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		return String.valueOf(data);
	}

}
