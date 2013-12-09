package main.java.com.chess;

public class Evaluator {

	public static int kingBounty = 1000;
	public static int queenBounty = 90;
	public static int rookBounty = 50;
	public static int bishopBounty = 30;
	public static int knightBounty = 30;
	public static int pawnBounty = 10;

	public static int fullEvaluate(Board b, int color) {
		return evaluateBoard(b, color) - evaluateBoard(b, Board.opponentColor(color));
	}

	public static int evaluateBoard(Board b, int color) {
		int sum = 0;
		int opponentColor = Board.opponentColor(color);

		try {
			for (int rank = 0; rank < b.board.length; rank++) {
				for (int file = 0; file < b.board[rank].length; file++) {
					sum += PieceEvaluation.evaluatePiece(b.board[rank][file], rank, file, color) + bounty(b, rank, file, opponentColor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
	}

	private static int bounty(Board b, int rank, int file, int opponentColor) {
		if (b.spaceHasOpponent(new Position(rank, file), opponentColor)) {
			switch (Math.abs(b.board[rank][file])) {
				case 1:
					return pawnBounty;
				case 2:
					return knightBounty;
				case 3:
					return bishopBounty;
				case 4:
					return rookBounty;
				case 5:
					return queenBounty;
				case 6:
					return kingBounty;
			}
		}
		return 0;
	}
}
