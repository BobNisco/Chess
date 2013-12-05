package main.java.com.chess;

public class Evaluator {

	public static int kingBounty = 150;
	public static int queenBounty = 90;
	public static int rookBounty = 50;
	public static int bishopBounty = 30;
	public static int knightBounty = 30;
	public static int pawnBounty = 10;

	public static int evaluateBoard(Board b, int color) {
		int sum = 0;

		int opponentColor = 0;
		if (color == Board.white) {
			opponentColor = Board.black;
		} else if (color == Board.black) {
			opponentColor = Board.white;
		}

		try {
			for (int rank = 0; rank < b.board.length; rank++) {
				for (int file = 0; file < b.board[rank].length; file++) {
					switch (Math.abs(b.board[rank][file])) {
						case 1:
							PawnEvaluation p = new PawnEvaluation(color);
							sum += p.b.board[rank][file];
							bounty(b, sum, rank, file, opponentColor);
							break;
						case 2:
							KnightEvaluation n = new KnightEvaluation(color);
							sum += n.b.board[rank][file];
							bounty(b, sum, rank, file, opponentColor);
							break;
						case 3:
							BishopEvaluation bishop = new BishopEvaluation(color);
							sum += bishop.b.board[rank][file];
							bounty(b, sum, rank, file, opponentColor);
							break;
						case 4:
							RookEvaluation r = new RookEvaluation(color);
							sum += r.b.board[rank][file];
							bounty(b, sum, rank, file, opponentColor);
							break;
						case 5:
							QueenEvaluation q = new QueenEvaluation(color);
							sum += q.b.board[rank][file];
							bounty(b, sum, rank, file, opponentColor);
							break;
						case 6:
							KingEvaluation k = new KingEvaluation(color);
							sum += k.b.board[rank][file];
							bounty(b, sum, rank, file, opponentColor);
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

	private static void bounty(Board b, int sum, int rank, int file, int opponentColor) {
		if (b.spaceHasOpponent(new Position(rank, file), opponentColor)) {
			switch (Math.abs(b.board[rank][file])) {
				case 1:
					sum += pawnBounty;
					break;
				case 2:
					sum += knightBounty;
					break;
				case 3:
					sum += bishopBounty;
					break;
				case 4:
					sum += rookBounty;
					break;
				case 5:
					sum += queenBounty;
					break;
				case 6:
					sum += kingBounty;
					break;
			}
		}
	}
}
