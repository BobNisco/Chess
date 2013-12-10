package main.java.com.chess;

import java.util.ArrayList;

public class Evaluator {

	public static int kingBounty = 150;
	public static int queenBounty = 90;
	public static int rookBounty = 50;
	public static int bishopBounty = 30;
	public static int knightBounty = 30;
	public static int pawnBounty = 10;

	public static int evaluateBoard(Board b, Move m, int color, int numberOfSuccessors) {
		int sum = 0;
		int opponentColor = Board.opponentColor(color);

		try {
			boolean isCapture = Board.isCaptureMove(b, m, color);
			if (isCapture) {
				sum += bounty(b, m.end.rank, m.end.file, opponentColor);
			}
			b.handleMove(m);
			// Handle kings
			sum += 200 * (b.numberOfPieces(6 * color) - b.numberOfPieces(6 * opponentColor));
			// Handle queens
			sum += 9 * (b.numberOfPieces(5 * color) - b.numberOfPieces(5 * opponentColor));
			// Handle rooks
			sum += 5 * (b.numberOfPieces(4 * color) - b.numberOfPieces(4 * opponentColor));
			// Handle Bishops and Knights
			sum += 3 * (
					(b.numberOfPieces(3 * color) - b.numberOfPieces(3 * opponentColor) +
					(b.numberOfPieces(2 * color) - b.numberOfPieces(2 * opponentColor))));
			// Handle pawns
			sum += 1 * (b.numberOfPieces(1 * color) - b.numberOfPieces(1 * opponentColor));
			// Handle doubled, blocked, and isolated pawns
			sum -= 0.5 * ((numberOfDoubledPawns(b, color) - numberOfDoubledPawns(b, opponentColor) +
					(numberOfBlockedPawns(b, color) - numberOfBlockedPawns(b, opponentColor)) +
					(numberOfIsolatedPawns(b, color) - numberOfIsolatedPawns(b, opponentColor))));
			// Handle number of legal successors for opponent
			ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(b, opponentColor);
			sum += 0.1 * (numberOfSuccessors - actions.size());
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

	private static int numberOfIsolatedPawns(Board b, int color) {
		int isolatedPawns = 0;

		try {
			for (int file = 0; file < b.board.length; file++) {
				boolean pawnFound = false;
				for (int rank = 0; rank < b.board[file].length; rank++) {
					if (b.board[rank][file] == 1 * color) {
						pawnFound = true;
					}
				}

				if (pawnFound) {
					boolean pawnFoundL = false;
					for (int rank = 0; rank < b.board[file - 1].length; rank++) {
						if (b.board[rank][file - 1] == 1 * color) {
							pawnFoundL = true;
						}
					}
					boolean pawnFoundR = false;
					for (int rank = 0; rank < b.board[file + 1].length; rank++) {
						if (b.board[rank][file + 1] == 1 * color) {
							pawnFoundL = true;
						}
					}

					if (!pawnFoundL && !pawnFoundR) {
						isolatedPawns++;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		return isolatedPawns;
	}

	public static int numberOfBlockedPawns(Board b, int color) {
		int blockedPawns = 0;
		try {
			if (color == Board.white) {
				for (int file = 0; file < b.board.length; file++) {
					for (int rank = 0; rank < b.board[file].length; rank++) {
						//System.out.println(b.board[rank][file]);
						if (b.board[rank][file] == Board.whitePawn) {
//							if (rank > 0) {
//								int pieceAhead = b.board[rank - 1][file];
//								if (file > 0) {
//
//								}
//								if (file < 7) {
//
//								}
//							}



							int pieceAhead = b.board[rank - 1][file];
							int pieceInTopLeft = b.board[rank - 1][file - 1];
							int pieceInTopRight = b.board[rank - 1][file + 1];
							//System.out.println("Ahead " + pieceAhead + " topLeft " + pieceInTopLeft + " topRight " + pieceInTopRight);
							if (pieceAhead != Board.empty && (pieceInTopLeft > 0 || pieceInTopLeft == Board.empty) && (pieceInTopRight > 0 || pieceInTopRight == Board.empty)) {
								blockedPawns++;
							}
						}
					}
				}
			} else if (color == Board.black) {
				for (int file = 0; file < b.board.length; file++) {
					for (int rank = 0; rank < b.board[file].length; rank++) {
						if (b.board[rank][file] == Board.blackPawn) {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] < 0 && b.board[rank + 1][file + 1] < 0) {
								blockedPawns++;
							}
						}
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return blockedPawns;
	}

	public static int numberOfDoubledPawns(Board b, int color) {
		int doubledPawns = 0;
		for (int file = 0; file < b.board.length; file++) {
			boolean firstPawnFound = false;
			for (int rank = 0; rank < b.board[file].length; rank++) {
				if (b.board[rank][file] == 1 * color) {
					if (!firstPawnFound) {
						firstPawnFound = true;
					} else {
						doubledPawns++;
						break;
					}
				}
			}
		}
		return doubledPawns;
	}
}
