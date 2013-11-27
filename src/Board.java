import java.util.HashMap;

/**
 * A class to represent the Chess board (8 files by 8 ranks).
 * Also holds the current state of the game.
 */
public class Board {
	/**
	 * The board's spaces represented by integers for the pieces
	 */
	public int[][] board;
	/**
	 * Boolean that tells us if it is our turn or not
	 */
	public boolean isOurTurn;

	/**
	 * Some mappings that we'll need to convert what we get from the server
	 * into our own representation of the board
	 */
	public static HashMap<String, Integer> fileToFileIntegers = initFileToFileIntegers();
	/**
	 * The integer values we'll use for our pieces
	 */
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
	 * Initializes the file to file integers hashmap
	 * @return
	 */
	public static HashMap<String, Integer> initFileToFileIntegers() {
		HashMap<String, Integer> r = new HashMap<String, Integer>();
		r.put("a", 0);
		r.put("b", 1);
		r.put("c", 2);
		r.put("d", 3);
		r.put("e", 4);
		r.put("f", 5);
		r.put("g", 6);
		r.put("h", 7);
		return r;
	}

	public void handleMove(Response move) {
		// Parse the input as per our rules
		// For example, a move may be Pd2d3 or Nb1c3. (both opening moves)
		int startFile = fileToFileIntegers.get(move.lastmove.substring(1, 2));
		// We have a zero-indexed int[][], but the data returned to us is
		// one-indexed, we fix that by subtracting one from the row
		int startRow = Integer.parseInt(move.lastmove.substring(2, 3)) - 1;
		int endFile = fileToFileIntegers.get(move.lastmove.substring(3, 4));
		int endRow = Integer.parseInt(move.lastmove.substring(4, 5)) - 1;

		if (move.lastmove.length() == 6) {
			// TODO: Handle the promotion
			// For example Pb7b8Q where the pawn is promoted to a queen.
			// Type of piece for promotion ∈ {Q, R, B, N}
		}

		// Move the piece from the original space to the new space
		this.board[endFile][endRow] = this.board[startFile][startRow];
		// Set the original space to empty
		this.board[startFile][startRow] = 0;
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
