package main.java.com.chess;

public class Node implements Comparable<Node> {
	public Board b;
	public Move m;
	public int evaluation;

	public Node() {
		this.b = new Board();
		this.m = new Move();
		this.evaluation = 0;
	}

	public Node(Board b) {
		this.b = b;
		this.m = new Move();
		this.evaluation = 0;
	}

	public Node(Board b, int evaluation) {
		this.b = b;
		this.evaluation = evaluation;
	}

	public Node(Board b, Move m, int evaluation) {
		this.b = b;
		this.m = m;
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Node with evaluation of " + this.evaluation + "\n" + this.b;
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
