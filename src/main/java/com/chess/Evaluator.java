package main.java.com.chess;

public class Evaluator {

	public static int evaluateBoard(Board b, int color) {
		int sum = 0;

		try {
			for (int rank = 0; rank < b.board.length; rank++) {
				for (int file = 0; file < b.board.length; file++) {
					switch (Math.abs(b.board[rank][file])) {
						case 1:
							PawnEvaluation p = new PawnEvaluation(b, color);
							break;
						case 2:
							KnightEvaluation n = new KnightEvaluation(b, color);
							break;
						case 3:
							BishopEvaluation bishop = new BishopEvaluation(b, color);
							break;
						case 4:
							RookEvaluation r = new RookEvaluation(b, color);
							break;
						case 5:
							QueenEvaluation q = new QueenEvaluation(b, color);
							break;
						case 6:
							KingEvaluation k = new KingEvaluation(b, color);
							break;
						default:
							throw new Exception("Unknown piece");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sum;
	}
}
