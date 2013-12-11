package main.java.com.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.*;

public class MiniMax {

	public static class TimedJob implements Callable<Node> {

		private Node n;
		private int color;
		private int depth;

		public TimedJob(Node n, int color, int d) {
			this.n = n;
			this.color = color;
			this.depth = d;
		}

		@Override
		public Node call() throws Exception {
			Node turn = maxValue(n, color, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
			return turn;
		}
	}

	public static Node performMiniMax(Board b, int color, int depth, Response r) {
		Node root = new Node(b, Integer.MIN_VALUE);
		Node move = null;
		if (r.secondsleft > 60) {
			Future<Node> control = Executors.newSingleThreadExecutor().submit(new TimedJob(root, color, depth));

			try {
				// Try to get the result within 45 seconds
				move = control.get(45, TimeUnit.SECONDS);
			} catch (TimeoutException ex) {
				// We went over the time, cancel the job
				control.cancel(true);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		if (move == null || move.m == null) {
			System.out.println("This is null");
			move = maxValue(root, color, 4, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		return move;
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
