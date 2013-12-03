package main.java.com.chess;

import java.util.ArrayList;

public class GenerateSuccessors {

	/**
	 * Generates all of the possible moves for a pawn given a board
	 * and the color of the player
	 * @param b the board
	 * @param color the color of the board
	 * @return An ArrayList<Move> of all possible moves for a given
	 * player's pawns
	 */
	public static ArrayList<Move> pawn(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		// Set the piece we want to look for to the WhitePawn
		int piece = Board.whitePawn;
		// But if the color passed in is -1, we want to look for the black pawn
		if (color == Board.black) {
			piece = Board.blackPawn;
		}
		ArrayList<Position> pieces = findPieces(b, piece);
		// Some variables we will use for moving the piece in the loop
		// This is defaulted to the case of white
		int beginningRank = 6;
		int forwardMove = -1;
		int opponentColor = Board.black;
		if (color == Board.black) {
			// Handle the case of black
			beginningRank = 1;
			forwardMove = 1;
			opponentColor = Board.white;
		}

		for (Position p : pieces) {
			if (p.rank == beginningRank) {
				// Handle starting position
				if (color == Board.black) {
					// Black Pawns in the starting position can either move
					// one or two spaces towards white as long as there are
					// no pieces in between in the way
					for (int i = 2; i < 4; i++) {
						Position possiblePosition = new Position(i, p.file);
						if (b.spaceIsEmpty(possiblePosition)) {
							moves.add(new Move(p, possiblePosition));
						} else {
							break;
						}
					}
				} else if (color == Board.white) {
					// White Pawns in the starting position can either move
					// one or two spaces towards black as long as there are
					// no pieces in between in the way
					for (int i = 5; i > 3; i--) {
						Position possiblePosition = new Position(i, p.file);
						if (b.spaceIsEmpty(possiblePosition)) {
							moves.add(new Move(p, possiblePosition));
						} else {
							break;
						}
					}
				}
			} else {
				// Handle any non-starting position
				Position possiblePosition = new Position(p.rank + forwardMove, p.file);
				if (b.spaceIsEmpty(possiblePosition)) {
					moves.add(new Move(p, possiblePosition));
				}
			}
			// Handle any possible diagonal captures
			if (p.file < 7) {
				Position possiblePosition = new Position(p.rank + forwardMove, p.file + 1);
				if (b.spaceHasOpponent(possiblePosition, opponentColor)) {
					moves.add(new Move(p, possiblePosition));
				}
			}
			if (p.file > 0) {
				Position possiblePosition = new Position(p.rank + forwardMove, p.file - 1);
				if (b.spaceHasOpponent(possiblePosition, opponentColor)) {
					moves.add(new Move(p, possiblePosition));
				}
			}
		}

		return moves;
	}

	public static ArrayList<Move> rook(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		// Set the piece we want to look for to the WhitePawn
		int piece = Board.whiteRook;
		// But if the color passed in is -1, we want to look for the black pawn
		if (color == Board.black) {
			piece = Board.blackRook;
		}
		int opponentColor = Board.black;
		if (color == Board.black) {
			opponentColor = Board.white;
		}

		ArrayList<Position> pieces = findPieces(b, piece);

		for (Position p : pieces) {
			// Handle all possible vertical (file) moves down the board
			for (int file = p.file; file < b.board[p.rank].length; file++) {
				Position currentPosition = new Position(p.rank, file);
				boolean shouldKeepGoing = handleRookMove(b, moves, p, currentPosition, opponentColor, piece);
				if (!shouldKeepGoing) {
					break;
				}
			}
			// Handle all possible vertical (file) moves up the board
			for (int file = p.file; file >= 0; file--) {
				Position currentPosition = new Position(p.rank, file);
				boolean shouldKeepGoing = handleRookMove(b, moves, p, currentPosition, opponentColor, piece);
				if (!shouldKeepGoing) {
					break;
				}
			}

			// Handle all possible horizontal (rank) moves down the board
			for (int rank = p.rank; rank < b.board.length; rank++) {
				Position currentPosition = new Position(rank, p.file);
				boolean shouldKeepGoing = handleRookMove(b, moves, p, currentPosition, opponentColor, piece);
				if (!shouldKeepGoing) {
					break;
				}
			}
			// Handle all possible horizontal (rank) moves up the board
			for (int rank = p.rank; rank >= 0; rank--) {
				Position currentPosition = new Position(rank, p.file);
				boolean shouldKeepGoing = handleRookMove(b, moves, p, currentPosition, opponentColor, piece);
				if (!shouldKeepGoing) {
					break;
				}
			}
		}

		return moves;
	}

	private static boolean handleRookMove(Board b, ArrayList<Move> moves, Position p, Position currentPosition, int opponentColor, int piece) {
		// TODO: Fix this, don't want to mix pieces
		if (b.currentPieceInPosition(currentPosition) == piece) {
			return true;
		}
		if (b.spaceHasOpponent(currentPosition, opponentColor)) {
			// There is an opponent here, this will be a possible move
			moves.add(new Move(p, currentPosition));
			// But the rook can not jump over this, so stop the loop
			return false;
		}
		if (b.spaceIsEmpty(currentPosition)) {
			// If the space is just empty, then it's a valid move
			moves.add(new Move(p, currentPosition));
			return true;
		}
		// Space is taken by one of our pieces, all other
		// moves after this are invalid, break.
		return false;
	}

	public static ArrayList<Move> bishop(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int piece = Board.whiteBishop;
		if (color == Board.black) {
			piece = Board.blackBishop;
		}
		ArrayList<Position> pieces = findPieces(b, piece);
		int opponentColor = Board.black;
		if (color == Board.black) {
			opponentColor = Board.white;
		}

		for (Position p : pieces) {
			// Handle any up/left moves
			int curRank = p.rank - 1;
			int curFile = p.file - 1;
			while (curRank >= 0 && curFile >= 0) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				// Move diagonally up and to the left
				curRank--;
				curFile--;
			}
			// Handle any up/right moves
			curRank = p.rank - 1;
			curFile = p.file + 1;
			while (curRank >= 0 && curFile < b.board.length) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				curRank--;
				curFile++;
			}
			// Handle any down/left moves
			curRank = p.rank + 1;
			curFile = p.file - 1;
			while (curRank < b.board.length && curFile >= 0) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				curRank++;
				curFile--;
			}
			// Handle any down/right moves
			curRank = p.rank + 1;
			curFile = p.file + 1;
			while (curRank < b.board.length && curFile < b.board.length) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				curRank++;
				curFile++;
			}
		}

		return moves;
	}

	private static boolean handleBishopMove(Board b, ArrayList<Move> moves, Position p, Position currentPosition, int opponentColor) {
		if (b.spaceHasOpponent(currentPosition, opponentColor)) {
			moves.add(new Move(p, currentPosition));
			return false;
		} else if (b.spaceIsEmpty(currentPosition)) {
			moves.add(new Move(p, currentPosition));
			return true;
		}
		return false;
	}

	public static ArrayList<Move> knight(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int piece = (color == Board.white) ? Board.whiteKnight : Board.blackKnight;
		ArrayList<Position> pieces = findPieces(b, piece);
		int opponentColor = (color == Board.white) ? Board.black : Board.white;

		for (Position p : pieces) {
			// Move up 2, left 1
			Move possibleMove = new Move(p, new Position(p.rank + 2, p.file - 1));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move up 2, right 1
			possibleMove = new Move(p, new Position(p.rank + 2, p.file + 1));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move up 1, left 2
			possibleMove = new Move(p, new Position(p.rank + 1, p.file - 2));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move up 1, right 2
			possibleMove = new Move(p, new Position(p.rank + 1, p.file + 2));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move down 2, left 1
			possibleMove = new Move(p, new Position(p.rank - 2, p.file - 1));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move down 2, right 1
			possibleMove = new Move(p, new Position(p.rank - 2, p.file + 1));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move down 1, left 2
			possibleMove = new Move(p, new Position(p.rank - 1, p.file - 2));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
			// Move down 1, right 2
			possibleMove = new Move(p, new Position(p.rank - 1, p.file + 2));
			if (isValidKnightMove(b, possibleMove, opponentColor)) {
				moves.add(possibleMove);
			}
		}

		return moves;
	}

	private static boolean isValidKnightMove(Board b, Move m, int opponentColor) {
		try {
			if (b.spaceIsEmpty(m.end) || b.spaceHasOpponent(m.end, opponentColor)) {
				return true;
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			// Since we're not checking bounds before passing the possible move
			// into this function, we'll just catch it here and return it as
			// not a valid move.
			return false;
		}
	}

	public static ArrayList<Move> queen(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int piece = (color == Board.white) ? Board.whiteQueen : Board.blackQueen;
		ArrayList<Position> pieces = findPieces(b, piece);
		int opponentColor = (color == Board.white) ? Board.black : Board.white;

		for (Position p : pieces) {
			// Move left
			for (int file = p.file - 1; file >= 0; file--) {
				Move possibleMove = new Move(p, new Position(p.rank, file));
				boolean shouldKeepGoing = handlePossibleMove(b, moves, possibleMove, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
			}
			// Move right
			for (int file = p.file + 1; file < b.board.length; file++) {
				Move possibleMove = new Move(p, new Position(p.rank, file));
				boolean shouldKeepGoing = handlePossibleMove(b, moves, possibleMove, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
			}
			// Move up
			for (int rank = p.rank - 1; rank >= 0; rank--) {
				Move possibleMove = new Move(p, new Position(rank, p.file));
				boolean shouldKeepGoing = handlePossibleMove(b, moves, possibleMove, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
			}
			// Move down
			for (int rank = p.rank + 1; rank < b.board.length; rank++) {
				Move possibleMove = new Move(p, new Position(rank, p.file));
				boolean shouldKeepGoing = handlePossibleMove(b, moves, possibleMove, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
			}
			// Handle any up/left moves
			int curRank = p.rank - 1;
			int curFile = p.file - 1;
			while (curRank >= 0 && curFile >= 0) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				// Move diagonally up and to the left
				curRank--;
				curFile--;
			}
			// Handle any up/right moves
			curRank = p.rank - 1;
			curFile = p.file + 1;
			while (curRank >= 0 && curFile < b.board.length) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				curRank--;
				curFile++;
			}
			// Handle any down/left moves
			curRank = p.rank + 1;
			curFile = p.file - 1;
			while (curRank < b.board.length && curFile >= 0) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				curRank++;
				curFile--;
			}
			// Handle any down/right moves
			curRank = p.rank + 1;
			curFile = p.file + 1;
			while (curRank < b.board.length && curFile < b.board.length) {
				Position possiblePosition = new Position(curRank, curFile);
				boolean shouldKeepGoing = handleBishopMove(b, moves, p, possiblePosition, opponentColor);
				if (!shouldKeepGoing) {
					break;
				}
				curRank++;
				curFile++;
			}
		}
		return moves;
	}

	private static boolean handlePossibleMove(Board b, ArrayList<Move> moves, Move m, int opponentColor) {
		try {
			if (b.spaceHasOpponent(m.end, opponentColor)) {
				moves.add(m);
				return false;
			} else if (b.spaceIsEmpty(m.end)) {
				moves.add(m);
				return true;
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	public static ArrayList<Move> king(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int piece = (color == Board.white) ? Board.whiteKing : Board.blackKing;
		ArrayList<Position> pieces = findPieces(b, piece);
		int opponentColor = (color == Board.white) ? Board.black : Board.white;

		for (Position p : pieces) {
			// Move up
			Move possibleMove = new Move(p, new Position(p.rank - 1, p.file));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move up/right
			possibleMove = new Move(p, new Position(p.rank - 1, p.file + 1));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move right
			possibleMove = new Move(p, new Position(p.rank, p.file + 1));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move down/right
			possibleMove = new Move(p, new Position(p.rank + 1, p.file + 1));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move down
			possibleMove = new Move(p, new Position(p.rank + 1, p.file));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move down/left
			possibleMove = new Move(p, new Position(p.rank + 1, p.file - 1));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move left
			possibleMove = new Move(p, new Position(p.rank, p.file - 1));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
			// Move up/left
			possibleMove = new Move(p, new Position(p.rank - 1, p.file - 1));
			handlePossibleMove(b, moves, possibleMove, opponentColor);
		}

		return moves;
	}

	public static ArrayList<Move> allPossibleSuccessors(Board b, int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		moves.addAll(pawn(b, color));
		moves.addAll(rook(b, color));
		moves.addAll(bishop(b, color));
		moves.addAll(knight(b, color));
		moves.addAll(queen(b, color));
		moves.addAll(king(b, color));
		return moves;
	}
	/**
	 * Returns a list of the positions of the pieces given by the int piece param
	 * @param b the board
	 * @param piece the piece we are looking for
	 * @return an ArrayList<Position>
	 */
	public static ArrayList<Position> findPieces(Board b, int piece) {
		ArrayList<Position> pieces = new ArrayList<Position>();

		for (int rank = 0; rank < b.board.length; rank++) {
			for (int file = 0; file < b.board[rank].length; file++) {
				if (b.board[rank][file] == piece) {
					pieces.add(new Position(rank, file));
				}
			}
		}

		return pieces;
	}

	public static ArrayList<Position> findOpponentPieces(Board b, int opponentColor) {
		ArrayList<Position> pieces = new ArrayList<Position>();

		for (int rank = 0; rank < b.board.length; rank++) {
			for (int file = 0; file < b.board[rank].length; file++) {
				if (opponentColor == Board.black) {
					if (b.board[rank][file] < 0) {
						pieces.add(new Position(rank, file));
					}
				} else {
					if (b.board[rank][file] > 0) {
						pieces.add(new Position(rank, file));
					}
				}
			}
		}

		return pieces;
	}
}
