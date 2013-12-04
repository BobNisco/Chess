package main.java.com.chess;

import java.util.ArrayList;

public class MiniMax {

	public static Node performMiniMax(Board b, int color, int depth) {
		Node root = new Node(b, Evaluator.evaluateBoard(b, color));
		if (color == Board.white) {
			return minValue(root, color, depth);
		} else {
			return maxValue(root, color, depth);
		}
	}

	private static Node maxValue(Node n, int color, int depth) {
		// Some sort of terminal test, still needs to be tweaked
		if (n.evaluation > 1000 || depth < 0) {
			return n;
		}

		Node v = new Node();
		v.evaluation = Integer.MIN_VALUE;

		ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(n.b, color);
		for (Move m : actions) {
			Board tempBoard = new Board(n.b);
			tempBoard.handleMove(m);
			Node tempNode = new Node(tempBoard, Evaluator.evaluateBoard(tempBoard, color));
			Node minNode = minValue(tempNode, color, depth);
			if (minNode.compareTo(v) > 0) {
				v = minNode;
			}
		}
		return v;
	}

	private static Node minValue(Node n, int color, int depth) {
		if (n.evaluation < 1000 || depth < 0) {
			return n;
		}

		Node v = new Node();
		v.evaluation = Integer.MAX_VALUE;

		ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(n.b, color);
		for (Move m : actions) {
			Board tempBoard = new Board(n.b);
			tempBoard.handleMove(m);
			Node tempNode = new Node(tempBoard, Evaluator.evaluateBoard(tempBoard, color));
			Node maxNode = maxValue(tempNode, color, depth);
			if (maxNode.compareTo(v) > 0) {
				v = maxNode;
			}
		}
		return v;
	}
}
