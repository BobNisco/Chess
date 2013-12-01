package chess;

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
		int startingRank = 4;
		int endingRank = 6;
		int forwardMove = -1;
		int opponentColor = Board.black;
		if (color == Board.black) {
			// Handle the case of black
			beginningRank = 1;
			startingRank = 2;
			endingRank = 4;
			forwardMove = 1;
			opponentColor = Board.white;
		}

		for (Position p : pieces) {
			if (p.rank == beginningRank) {
				// Handle starting position
				// Pawns in the starting position can either move
				// one or two spaces towards the opposing team
				for (int i = startingRank; i < endingRank; i++) {
					// Generate the move by moving forward to the ith space
					Position possiblePosition = new Position(i, p.file);
					if (b.spaceIsEmpty(possiblePosition)) {
						moves.add(new Move(p, possiblePosition));
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
