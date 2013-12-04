package main.java.com.chess;

public class Node implements Comparable<Node> {
	public Board b;
	public int evaluation;

	public Node() {
		this.b = new Board();
		this.evaluation = 0;
	}

	public Node(Board b) {
		this.b = b;
		this.evaluation = 0;
	}

	public Node(Board b, int evaluation) {
		this.b = b;
		this.evaluation = evaluation;
	}

	@Override
	public int compareTo(Node o) {
		if (this.evaluation > o.evaluation) {
			return 1;
		} else if (this.evaluation < o.evaluation) {
			return -1;
		} else {
			return 0;
		}
	}
}
