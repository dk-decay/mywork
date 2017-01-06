package dsa.binarysearchtree;

/**
 * @author deveshkandpal
 * Custom Implementation of BST. It supports insert, find and delete, largest element, smallest
 * element, height of BST, count and additionally creates a Balanced BST from a sorted array
 *
 */
public class BinarySearchTree {

	private TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	
	public void insert(int data) {
		if (this.root == null) {
			this.root = new TreeNode(data);
		} else {
			this.root.insert(data);
		}
	}

	public TreeNode find(int data) {
		if (this.root == null) {
			return null;
		} else {
			return this.root.find(data);
		}
	}

	public boolean delete(int data) {
		if (this.root == null) {
			return false;
		} else if (this.root.getData() == data) {
			TreeNode auxNode = new TreeNode(0);
			auxNode.setLeftNode(this.root);
			boolean result = auxNode.remove(data, null);
			this.root = auxNode.getLeftNode();
			return result;
		} else {
			return this.root.remove(data, null);
		}
	}

	public int largest() {
		if (this.root == null)
			return 0;
		return this.root.max();
	}

	public int min() {
		if (this.root == null)
			return 0;
		return this.root.min();
	}

	public String inOrder() {

		if (this.root == null)
			return "";
		else {
			return this.root.inOrder("");
		}

	}

	public int count() {

		if (this.root == null)
			return 0;
		else {
			return this.root.leafNodeCount(0);
		}

	}

	public int height() {
		if (this.root == null)
			return 0;
		else
			return this.root.height(0);
	}

	public void balanceInsert(int[] input) {

		addNode(0, input.length - 1, input, false, null);

		int b = 1;
		System.out.println("done" + b);

	}

	public void addNode(int start, int end, int[] input, boolean isLeft, TreeNode parent) {
		int middle = (int) Math.floor(start + end) / 2;
		TreeNode node = new TreeNode(input[middle]);
		if (parent == null) {
			// Root node
			System.out.println("Parent is null");
			this.root = node;
		} else if (isLeft) {
			parent.setLeftNode(node);
		} else {
			parent.setRightNode(node);
		}
		parent = node;
		if (middle - 1 >= start) {
			addNode(start, middle - 1, input, true, parent);
		}
		if (middle + 1 <= end) {
			addNode(middle + 1, end, input, false, parent);
		}

	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		// bst.insert(40);
		// bst.insert(30);
		// bst.insert(45);
		// bst.insert(50);
		// bst.insert(48);
		// bst.insert(52);
		// bst.insert(55);
		//
		bst.balanceInsert(new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130 });

	}
}
