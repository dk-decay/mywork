package dsa.binarysearchtree;

public class TreeNode {
	private int data;
	private TreeNode leftNode;
	private TreeNode rightNode;

	public int getData() {
		return data;
	}

	public TreeNode(int data) {
		this.data = data;
	}

	public TreeNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	public TreeNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	public void insert(int data) {
		if (this.data >= data) {
			if (this.leftNode == null) {
				this.leftNode = new TreeNode(data);
			} else {
				this.leftNode.insert(data);
			}
		} else {
			if (this.rightNode == null) {
				this.rightNode = new TreeNode(data);
			} else {
				this.rightNode.insert(data);
			}
		}
	}

	public TreeNode find(int data) {

		if (this.data == data) {
			return this;
		}
		if (this.data > data && this.leftNode != null) {
			return this.leftNode.find(data);
		}
		if (this.data < data && this.rightNode != null) {
			return this.rightNode.find(data);
		}
		return null;
	}

	public boolean remove(int data, TreeNode parent) {
		if (data < this.data) {
			if (this.leftNode != null) {
				this.leftNode.remove(data, this);
			} else {
				return false;
			}
		} else if (data > this.data) {
			if (this.rightNode != null) {
				this.rightNode.remove(data, this);
			} else {
				return false;
			}
		} else {

			if (this.leftNode != null && this.rightNode != null) {
				this.data = this.rightNode.findMin();
				this.rightNode.remove(this.data, this);
			} else if (parent.getLeftNode() == this) {
				parent.setLeftNode(this.leftNode != null ? this.leftNode : this.rightNode);
			} else if (parent.getRightNode() == this) {
				parent.setRightNode(this.leftNode != null ? this.leftNode : this.rightNode);
			}

		}

		return true;
	}

	public int findMin() {
		if (leftNode != null) {
			return leftNode.findMin();
		} else {
			return this.getData();
		}
	}

	public int min() {
		if (this.leftNode == null)
			return this.data;
		return this.leftNode.min();
	}

	public int max() {
		if (this.rightNode == null)
			return this.data;
		return this.rightNode.max();
	}

	public String inOrder(String result) {
		if (this.leftNode != null) {
			result = this.leftNode.inOrder(result) + " ";
		}
		result = result + this.data + " ";
		if (this.rightNode != null) {
			result = this.rightNode.inOrder(result) + " ";
		}
		return result;
	}

	public String preOrder(String result) {
		result = result + this.data + " ";
		if (this.leftNode != null) {
			result = this.leftNode.inOrder(result) + " ";
		}
		if (this.rightNode != null) {
			result = this.rightNode.inOrder(result) + " ";
		}
		return result;
	}

	public String postOrder(String result) {

		if (this.leftNode != null) {
			result = this.leftNode.inOrder(result) + " ";
		}
		if (this.rightNode != null) {
			result = this.rightNode.inOrder(result) + " ";
		}
		result = result + this.data + " ";
		return result;
	}

	public int leafNodeCount(int count) {

		if (this.leftNode == null && this.rightNode == null) {
			count++;
		} else {
			if (this.leftNode != null) {
				count = this.leftNode.leafNodeCount(count);
			}
			if (this.rightNode != null) {
				count = this.rightNode.leafNodeCount(count);
			}
		}
		return count;
	}

	public int height(int h) {
		int l = h, r = h;
		if (this.leftNode != null) {
			l = this.leftNode.height(h);
		}
		if (this.rightNode != null) {
			r = this.rightNode.height(h);
		}
		h = Math.max(l, r);
		h++;
		return h;
	}

}
