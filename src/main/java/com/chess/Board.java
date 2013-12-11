package main.java.com.chess;

/**
 * A class to represent the Chess board (8 files by 8 ranks).
 * Also holds the current state of the game.
 */
public class Board {
	/**
	 * The board's spaces represented by integers for the pieces
	 */
	public int[][] board;

	public int color;

	/**
	 * The integer values we'll use for our pieces
	 */
	public static final int whiteKing = 6;
	public static final int whiteQueen = 5;
	public static final int whiteRook = 4;
	public static final int whiteBishop = 3;
	public static final int whiteKnight = 2;
	public static final int whitePawn = 1;
	public static final int blackKing = -6;
	public static final int blackQueen = -5;
	public static final int blackRook = -4;
	public static final int blackBishop = -3;
	public static final int blackKnight = -2;
	public static final int blackPawn = -1;
	public static final int empty = 0;

	/**
	 * Integer values we will use to reference colors
	 */
	public static final int white = 1;
	public static final int black = -1;

	public Board() {
		this.board = new int[8][8];
		// Set the game state to the proper start chess state
		this.setStartGameState();
	}

	public Board(Board b) {
		this.board = copyBoard(b);
	}

	public Board(int[][] board, int color) {
		this.board = board;
		this.color = color;
	}

	public Board(int color) {
		this.board = new int[8][8];
		this.setStartGameState();
		this.color = color;
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

		// Initialize all the other states as empty.
		// Even though Java will automatically set it to 0, this is here
		// just in case we ever change the representation of empty to
		// something else
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] == 0) {
					this.board[i][j] = this.empty;
				}
			}
		}
	}

	public boolean spaceIsEmpty(Position p) {
		try {
			return this.board[p.rank][p.file] == Board.empty;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean spaceHasOpponent(Position p, int opponentColor) {
		try {
			if (opponentColor == Board.white) {
				if (this.board[p.rank][p.file] > 0) {
					return true;
				}
			} else {
				if (this.board[p.rank][p.file] < 0) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public int currentPieceInPosition(Position p) {
		return this.board[p.rank][p.file];
	}

	public boolean handleMove(Move m) {
		try {
			MoveHandler.handleMove(this, new Response(MoveHandler.convertMoveToServerNotation(this, m)));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private int[][] copyBoard(Board b) {
		int[][] oldBoard = new int[8][8];
		for (int rank = 0; rank < b.board.length; rank++) {
			for (int file = 0; file < b.board[rank].length; file++) {
				oldBoard[rank][file] = b.board[rank][file];
			}
		}
		return oldBoard;
	}

	public boolean gameIsOver() {
		boolean foundWhiteKing = false;
		boolean foundBlackKing = false;
		for (int rank = 0; rank < this.board.length; rank++) {
			for (int file = 0; file < this.board[rank].length; file++) {
				if (this.board[rank][file] == Board.whiteKing) {
					foundWhiteKing = true;
				} else if (this.board[rank][file] == Board.blackKing) {
					foundBlackKing = true;
				}
			}
		}
		if (!foundBlackKing || !foundWhiteKing) {
			return true;
		}
		return false;
	}

	private static boolean handleIsPawnPromotion(Board b, Move m, int color) {
		if (color == Board.white) {
			if ((m.start.rank == 1 && m.end.rank == 0) &&
					b.currentPieceInPosition(m.start) == Board.whitePawn) {
				return true;
			}
		} else if (color == Board.black) {
			if ((m.start.rank == 6 && m.end.rank == 7) &&
					b.currentPieceInPosition(m.start) == Board.blackPawn) {
				return true;
			}
		}
		return false;
	}

	public boolean isPawnPromotion(Move m, int color) {
		return handleIsPawnPromotion(this, m, color);
	}

	public static boolean isPawnPromotion(Board b, Move m, int color) {
		return handleIsPawnPromotion(b, m, color);
	}

	public static boolean isCaptureMove(Board b, Move m, int color) {
		int opponentColor = opponentColor(color);
		if (color == Board.white) {
			if (b.board[m.end.rank][m.end.file] < 0) {
				return true;
			}
		} else if (color == Board.black) {
			if (b.board[m.end.rank][m.end.file] > 0) {
				return true;
			}
		}
		return false;
	}

	public int numberOfPieces(int piece) {
		int num = 0;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] == piece) {
					num++;
				}
			}
		}
		return num;
	}

	public static int totalNumberOfPieces(Board b) {
		int sum = 0;
		for (int i = 0; i < b.board.length; i++) {
			for (int j = 0; j < b.board[i].length; j++) {
				if (b.board[i][j] != Board.empty) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * Returns the integer for your opponent's color
	 * @param color your color
	 * @return
	 */
	public static int opponentColor(int color) {
		return (color == Board.white) ? Board.black : Board.white;
	}

	/**
	 * Prints out the chess board in a nice 2D grid
	 * @return string representing the state of the grid
	 */
	public String toString() {
		String rep = "";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				rep += MoveHandler.convertIntToPieceString(this.board[i][j], true) + " ";
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
