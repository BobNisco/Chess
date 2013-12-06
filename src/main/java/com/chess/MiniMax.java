package main.java.com.chess;

import java.util.ArrayList;

public class MiniMax {

	public static Node performMiniMax(Board b, int color, int depth) {
		Node root = new Node(b, Evaluator.evaluateBoard(b, color));
		return maxValue(root, color, depth);
	}

	private static Node maxValue(Node n, int color, int depth) {
		// Some sort of terminal test, still needs to be tweaked
		if (depth <= 0) {
			return n;
		}

		Node v = new Node();
		v.evaluation = Integer.MIN_VALUE;

		int opponentColor = 0;
		if (color == Board.white) {
			opponentColor = Board.black;
		} else if (color == Board.black) {
			opponentColor = Board.white;
		}

		ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(n.b, color);
		for (Move m : actions) {
			Board tempBoard = new Board(n.b);
			tempBoard.handleMove(m);
			Node tempNode = new Node(tempBoard, m, Evaluator.evaluateBoard(tempBoard, color));
			Node minNode = minValue(tempNode, opponentColor, depth - 1);
			if (minNode.compareTo(v) > 0) {
				v = tempNode;
			}
		}
		return v;
	}

	private static Node minValue(Node n, int color, int depth) {
		if (depth <= 0) {
			return n;
		}

		Node v = new Node();
		v.evaluation = Integer.MAX_VALUE;

		int opponentColor = 0;
		if (color == Board.white) {
			opponentColor = Board.black;
		} else if (color == Board.black) {
			opponentColor = Board.white;
		}

		ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(n.b, color);
		for (Move m : actions) {
			Board tempBoard = new Board(n.b);
			tempBoard.handleMove(m);
			Node tempNode = new Node(tempBoard, m, Evaluator.evaluateBoard(tempBoard, color));
			Node maxNode = maxValue(tempNode, opponentColor, depth - 1);
			if (maxNode.compareTo(v) < 0) {
				v = tempNode;
			}
		}
		return v;
	}
}
