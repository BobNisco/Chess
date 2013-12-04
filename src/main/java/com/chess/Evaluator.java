package main.java.com.chess;

public class Evaluator {

	public static int evaluateBoard(Board b, int color) {
		int sum = 0;

		try {
			for (int rank = 0; rank < b.board.length; rank++) {
				for (int file = 0; file < b.board[rank].length; file++) {
					switch (Math.abs(b.board[rank][file])) {
						case 1:
							PawnEvaluation p = new PawnEvaluation(color);
							sum += p.b.board[rank][file];
							break;
						case 2:
							KnightEvaluation n = new KnightEvaluation(color);
							sum += n.b.board[rank][file];
							break;
						case 3:
							BishopEvaluation bishop = new BishopEvaluation(color);
							sum += bishop.b.board[rank][file];
							break;
						case 4:
							RookEvaluation r = new RookEvaluation(color);
							sum += r.b.board[rank][file];
							break;
						case 5:
							QueenEvaluation q = new QueenEvaluation(color);
							sum += q.b.board[rank][file];
							break;
						case 6:
							KingEvaluation k = new KingEvaluation(color);
							sum += k.b.board[rank][file];
							break;
						case 0:
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
