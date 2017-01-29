package dsa.recursion;

public class TowerOfHannoi {

	public void move(int n, char from, char to, char inter) {

		if (n == 1) {
			System.out.println("Moving disc" + n + " from " + from + " to " + to);
		} else {
			move(n - 1, from, inter, to);
			System.out.println("Moving disc" + n + " from " + from + " to " + to);
			move(n - 1, inter, to, from);

		}

	}

	public static void main(String[] args) {
		TowerOfHannoi toh = new TowerOfHannoi();
		toh.move(3, 'A', 'C', 'B');
	}

}
