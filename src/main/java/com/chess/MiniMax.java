package main.java.com.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MiniMax {

	public static Node performMiniMax(Board b, int color, int depth) {
//		int numOfPiecesOnBoard = Board.totalNumberOfPieces(b);
//		if (numOfPiecesOnBoard >= 20) {
//			depth = 4;
//		} else if (numOfPiecesOnBoard >= 10) {
//			depth = 5;
//		} else {
//			depth = 6;
//		}
//		System.out.println("SEARCHING AT DEPTH " + depth);
		Node root = new Node(b, Integer.MIN_VALUE);
		return maxValue(root, color, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static Node maxValue(Node n, int color, int depth, int alpha, int beta) {
		// Test if we have hit the max depth, or if the node has a state of solved
		if (depth <= 0 || n.b.gameIsOver()) {
			return n;
		}

		Node a = new Node();
		a.evaluation = alpha;
		int opponentColor = Board.opponentColor(color);

		ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(n.b, color);
		long seed = System.nanoTime();
		Collections.shuffle(actions, new Random(seed));
		int numberOfSuccessors = actions.size();
		for (Move m : actions) {
			Board tempBoard = new Board(n.b);
			//tempBoard.handleMove(m);
			Node tempNode = new Node(tempBoard, m, Evaluator.evaluateBoard(tempBoard, m, color, numberOfSuccessors));
			Node minNode = minValue(tempNode, opponentColor, depth - 1, a.evaluation, beta);
			if (minNode.compareTo(a) > 0) {
				a = tempNode;
			}
			if (beta <= a.evaluation) {
				break;
			}
		}
		return a;
	}

	private static Node minValue(Node n, int color, int depth, int alpha, int beta) {
		// Test if we have hit the max depth, or if the node has a state of solved
		if (depth <= 0 || n.b.gameIsOver()) {
			return n;
		}

		Node b = new Node();
		b.evaluation = beta;
		int opponentColor = Board.opponentColor(color);

		ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(n.b, color);
		long seed = System.nanoTime();
		Collections.shuffle(actions, new Random(seed));
		int numberOfSuccessors = actions.size();
		for (Move m : actions) {
			Board tempBoard = new Board(n.b);
			//tempBoard.handleMove(m);
			Node tempNode = new Node(tempBoard, m, Evaluator.evaluateBoard(tempBoard, m, color, numberOfSuccessors));
			Node maxNode = maxValue(tempNode, opponentColor, depth - 1, alpha, b.evaluation);
			if (maxNode.compareTo(b) < 0) {
				b = tempNode;
			}
			if (b.evaluation <= alpha) {
				break;
			}
		}
		return b;
	}
}
