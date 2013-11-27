/**
 * A class to represent the Chess board (8 files by 8 ranks).
 * Also holds the current state of the game.
 */
public class Board {
	/**
	 * The board's spaces represented by integers for the pieces
	 */
	public int[][] board;
	public boolean isOurTurn;

	// All of the pieces
	public final int whiteKing = 6;
	public final int whiteQueen = 5;
	public final int whiteRook = 4;
	public final int whiteBishop = 3;
	public final int whiteKnight = 2;
	public final int whitePawn = 1;
	public final int blackKing = -6;
	public final int blackQueen = -5;
	public final int blackRook = -4;
	public final int blackBishop = -3;
	public final int blackKnight = -2;
	public final int blackPawn = -1;

	public Board() {
		this.board = new int[8][8];
		this.isOurTurn = false;
		// Set the game state to the proper start chess state
		this.setStartGameState();
	}

	/**
	 * Sets the state of the board to the initial Chess game state
	 */
	private void setStartGameState() {
		this.board[0][0] = blackRook;
		this.board[0][1] = blackKnight;
		this.board[0][2] = blackBishop;
		this.board[0][3] = blackQueen;
		this.board[0][4] = blackKing;
		this.board[0][5] = blackBishop;
		this.board[0][6] = blackKnight;
		this.board[0][7] = blackRook;
		this.board[1][0] = blackPawn;
		this.board[1][1] = blackPawn;
		this.board[1][2] = blackPawn;
		this.board[1][3] = blackPawn;
		this.board[1][4] = blackPawn;
		this.board[1][5] = blackPawn;
		this.board[1][6] = blackPawn;
		this.board[1][7] = blackPawn;

		this.board[7][0] = whiteRook;
		this.board[7][1] = whiteKnight;
		this.board[7][2] = whiteBishop;
		this.board[7][3] = whiteKing;
		this.board[7][4] = whiteQueen;
		this.board[7][5] = whiteBishop;
		this.board[7][6] = whiteKnight;
		this.board[7][7] = whiteRook;
		this.board[6][0] = whitePawn;
		this.board[6][1] = whitePawn;
		this.board[6][2] = whitePawn;
		this.board[6][3] = whitePawn;
		this.board[6][4] = whitePawn;
		this.board[6][5] = whitePawn;
		this.board[6][6] = whitePawn;
		this.board[6][7] = whitePawn;
	}

	/**
	 * A utility function to convert the integer representation of a piece in the board
	 * to a string representation of a piece.
	 * @param piece the integer representation of a piece
	 * @param includeColor if the return value should be prepended with a "W"
	 *                     for a white piece or a "B" for a black piece.
	 * @return string representation of a piece
	 */
	private String convertIntToPieceString(int piece, boolean includeColor) {
		String p = "";
		switch (piece) {
			case whiteBishop:
			case blackBishop:
				p = "B";
				break;
			case whiteKing:
			case blackKing:
				p = "K";
				break;
			case whiteKnight:
			case blackKnight:
				p = "N";
				break;
			case whitePawn:
			case blackPawn:
				p = "P";
				break;
			case whiteQueen:
			case blackQueen:
				p = "Q";
				break;
			case whiteRook:
			case blackRook:
				p = "R";
				break;
			default:
				p = " ";
				break;
		}
		if (includeColor) {
			if (piece < 0) {
				p = "B" + p;
			} else if (piece > 0) {
				p = "W" + p;
			} else {
				p = "  ";
			}
		}
		return p;
	}

	/**
	 * Prints out the chess board in a nice 2D grid
	 * @return string representing the state of the grid
	 */
	public String toString() {
		String rep = "";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				rep += this.convertIntToPieceString(this.board[i][j], true) + " ";
			}
			rep += "\n";
		}
		return rep;
	}

	public static void main(String[] args) {
		Board b = new Board();
		System.out.println(b);
	}
}
