package main.java.com.chess;

public class PieceEvaluation {

	public static final int[][] whiteBishop = {
			{-20, -10, -10, -10, -10, -10, -10, -20},
			{-10, 0, 0, 0, 0, 0, 0, -10},
			{-10, 0, 5, 10, 10, 5, 0, -10},
			{-10, 5, 5, 10, 10, 5, 5, -10},
			{0, 10, 10, 10, 10, 10, 0, -10},
			{-10, 10, 10, 10, 10, 10, 10, -10},
			{-10, 5, 0, 0, 0, 0, 5, -10},
			{-20, -10, -10, -10, -10, -10, -10, -20}
	};

	public static final int[][] blackBishop = mirrorForBlack(whiteBishop);

	public static final int[][] whiteKing = {
			{-30, -40, -40, -50, -50, -40, -40, -30},
			{-30, -40, -40, -50, -50, -40, -40, -30},
			{-30, -40, -40, -50, -50, -40, -40, -30},
			{-30, -40, -40, -50, -50, -40, -40, -30},
			{-20, -30, -30, -40, -40, -30, -30, -20},
			{-10, -20, -20, -20, -20, -20, -20, -10},
			{20, 20, 0, 0, 0, 0, 20, 20},
			{20, 30, 10, 0, 0, 10, 30, 20}
	};

	public static final int[][] blackKing = mirrorForBlack(whiteKing);

	public static final int[][] whiteKnight = {
			{-50, -40, -30, -30, -30, -30, -40, -50},
			{-40, -20, 0, 0, 0, 0, -20, -40},
			{-30, 0, 0, 0, 0, 0, 0, -30},
			{-30, 0, 0, 0, 0, 0, 0, -30},
			{-30, 0, 0, 0, 0, 0, 0, -30},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{40, 0, 0, 0, 0, 0, 0, -40},
			{-50, 0, -30, -30, -30, -30, 0, -50}
	};

	public static final int[][] blackKnight = mirrorForBlack(whiteKnight);

	public static final int[][] whitePawn = {
			{70, 70, 70, 70, 70, 70, 70, 70},
			{50, 50, 50, 50, 50, 50, 50, 50},
			{10, 10, 20, 30, 30, 20, 10, 10},
			{5, 0, 0, 0, 0, 0, 0, 5},
			{200, 200, 200, 200, 200, 200, 200, 200},
			{100, 100, 100, 100, 100, 100, 100, 100},
			{-100, -100, -100, -100, -100, -100, -100, -100},
			{0, 0, 0, 0, 0, 0, 0, 0}
	};

	public static final int[][] blackPawn = mirrorForBlack(whitePawn);

	public static final int[][] whiteQueen = {
			{-20, -10, -10, -5, -5, -10, -10, -20},
			{-10, 0, 0, 0, 0, 0, 0, -10},
			{-10, 0, 5, 5, 5, 5, 0, -10},
			{-5, 0, 5, 5, 5, 5, 0, -5},
			{0, 0, 5, 5, 5, 5, 0, -5},
			{-10, 5, 5, 5, 5, 5, 0, -10},
			{-10, 0, 5, 0, 0, 0, 0, -10},
			{-20, -10, -10, -5, -5, -10, -10, -20}
	};

	public static final int[][] blackQueen = mirrorForBlack(whiteQueen);

	public static final int[][] whiteRook = {
			{0, 0, 0, 0, 0, 0, 0, 0},
			{5, 10, 10, 10, 10, 10, 10, 5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{0, 0, 0, 5, 5, 0, 0, 0}
	};

	public static int[][] blackRook = mirrorForBlack(whiteRook);

	/**
	 * If you are playing as the black player, mirror the evaluation functions
	 * so that you can get an accurate evaluation function
	 */
	public static int[][] mirrorForBlack(int[][] piece) {
		// Create a copy of the starting board
		int[][] copyBoard = new int[8][8];
		for (int rank = 0; rank < piece.length; rank++) {
			for (int file = 0; file < piece[rank].length; file++) {
				copyBoard[rank][file] = piece[rank][file];
			}
		}

		// Do the swap
		for (int rank = 0; rank < piece.length; rank++) {
			for (int file = 0; file < piece[rank].length; file++) {
				copyBoard[rank][file] = piece[7 - rank][7 - file];
			}
		}

		return copyBoard;
	}

	public static int evaluatePiece(int piece, int rank, int file, int color) {
		try {
			if (color == Board.white) {
				switch (Math.abs(piece)) {
					case 1:
						return PieceEvaluation.whitePawn[rank][file];
					case 2:
						return PieceEvaluation.whiteKnight[rank][file];
					case 3:
						return PieceEvaluation.whiteBishop[rank][file];
					case 4:
						return PieceEvaluation.whiteRook[rank][file];
					case 5:
						return PieceEvaluation.whiteQueen[rank][file];
					case 6:
						return PieceEvaluation.whiteKing[rank][file];
					case 0:
						return 0;
					default:
						throw new Exception("Unknown piece");
				}
			} else if (color == Board.black) {
				switch (Math.abs(piece)) {
					case 1:
						return PieceEvaluation.blackPawn[rank][file];
					case 2:
						return PieceEvaluation.blackKnight[rank][file];
					case 3:
						return PieceEvaluation.blackBishop[rank][file];
					case 4:
						return PieceEvaluation.blackRook[rank][file];
					case 5:
						return PieceEvaluation.blackQueen[rank][file];
					case 6:
						return PieceEvaluation.blackKing[rank][file];
					case 0:
						return 0;
					default:
						throw new Exception("Unknown piece");
				}
			} else {
				throw new Exception("Unknown color");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
