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
			// Adjust for the color
			//sum *= color;
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

	public static int numberOfIsolatedPawns(Board b, int color) {
		int isolatedPawns = 0;

		for (int file = 0; file < b.board.length; file++) {
			boolean pawnFound = false;
			for (int rank = 0; rank < b.board[file].length; rank++) {
				if (b.board[rank][file] == 1 * color) {
					pawnFound = true;
				}
			}

			if (pawnFound) {
				boolean pawnFoundL = false;
				if (file > 0) {
					for (int rank = 0; rank < b.board[file - 1].length; rank++) {
						if (b.board[rank][file - 1] == 1 * color) {
							pawnFoundL = true;
						}
					}
				}
				boolean pawnFoundR = false;
				if (file < 7) {
					for (int rank = 0; rank < b.board[file + 1].length; rank++) {
						if (b.board[rank][file + 1] == 1 * color) {
							pawnFoundL = true;
						}
					}
				}

				if (file > 0 && file < 7) {
					if (!pawnFoundL && !pawnFoundR) {
						isolatedPawns++;
					}
				} else if (file == 7) {
					if (!pawnFoundL) {
						isolatedPawns++;
					}
				} else if (file == 0) {
					if (!pawnFoundR) {
						isolatedPawns++;
					}
				}
			}
		}

		return isolatedPawns;
	}

	public static int numberOfBlockedPawns(Board b, int color) {
		int blockedPawns = 0;
		if (color == Board.white) {
			for (int file = 0; file < b.board.length; file++) {
				for (int rank = 0; rank < b.board[file].length; rank++) {
					if (b.board[rank][file] == Board.whitePawn) {
						// Scenario 1
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file - 1] == Board.empty && b.board[rank - 1][file + 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 2
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file - 1] > 0 && b.board[rank - 1][file + 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 3
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file - 1] > 0 && b.board[rank - 1][file + 1] > 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 4
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file - 1] == Board.empty && b.board[rank - 1][file + 1] > 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 5
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file + 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 6
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file + 1] > 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 7
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file - 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 8
						try {
							if (b.board[rank - 1][file] != Board.empty && b.board[rank - 1][file - 1] > 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
					}
				}
			}
		} else if (color == Board.black) {
			for (int file = 0; file < b.board.length; file++) {
				for (int rank = 0; rank < b.board[file].length; rank++) {
					if (b.board[rank][file] == Board.blackPawn) {
						// Scenario 1
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] == Board.empty && b.board[rank + 1][file + 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 2
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] < 0 && b.board[rank + 1][file + 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 3
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] < 0 && b.board[rank + 1][file + 1] < 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 4
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] == Board.empty && b.board[rank + 1][file + 1] < 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 5
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file + 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 6
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file + 1] < 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 7
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] == Board.empty) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
						// Scenario 8
						try {
							if (b.board[rank + 1][file] != Board.empty && b.board[rank + 1][file - 1] < 0) {
								blockedPawns++;
								break;
							}
						} catch (Exception e) {
						}
					}
				}
			}
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
