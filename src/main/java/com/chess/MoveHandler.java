package main.java.com.chess;

import java.util.HashMap;

public class MoveHandler {
	/**
	 * Some mappings that we'll need to convert what we get from the server
	 * into our own representation of the board
	 */
	public static HashMap<String, Integer> fileToFileIntegers = initFileToFileIntegers();
	public static HashMap<Integer, String> fileIntegerToFile = initFileIntegersToFile();
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

	public static HashMap<Integer, String> initFileIntegersToFile() {
		HashMap<Integer, String> r = new HashMap<Integer, String>();
		r.put(0, "a");
		r.put(1, "b");
		r.put(2, "c");
		r.put(3, "d");
		r.put(4, "e");
		r.put(5, "f");
		r.put(6, "g");
		r.put(7, "h");
		return r;
	}

	public static String convertIntToServerChar(int piece) {
		piece = Math.abs(piece);
		switch (piece) {
			case 1:
				return "P";
			case 2:
				return "N";
			case 3:
				return "B";
			case 4:
				return "R";
			case 5:
				return "Q";
			case 6:
				return "K";
			default:
				return "";
		}
	}

	public static void handleMove(Board b, Response move) {
		// Parse the input as per our rules
		// For example, a move may be Pd2d3 or Nb1c3. (both opening moves)
		int startFile = fileToFileIntegers.get(move.lastmove.substring(1, 2));
		// We have a zero-indexed int[][], but the data returned to us is
		// one-indexed, we fix that by subtracting one from the row
		int startRow = convertServerRow(move.lastmove.substring(2, 3));
		int endFile = fileToFileIntegers.get(move.lastmove.substring(3, 4));
		int endRow = convertServerRow(move.lastmove.substring(4, 5));

		int[][] oldBoard = b.board.clone();
		if (move.lastmove.length() == 6) {
			// Promotion!
			// For example Pb7b8Q where the pawn is promoted to a queen.
			// Type of piece for promotion ∈ {Q, R, B, N}
			int newPiece = convertLetterToPieceInt(move.lastmove.substring(5, 6)) * getColorOfPiece(b, new Position(startRow, startFile));
			b.board[endRow][endFile] = newPiece;
		} else {
			// No promotion, just move the piece from the original space to the new space
			b.board[endRow][endFile] = oldBoard[startRow][startFile];
		}
		// Set the original space to empty
		b.board[startRow][startFile] = b.empty;
	}

	/**
	 * The server uses a grid that goes from 8 to 1 from top to bottom
	 * our int[][] goes from 0 to 7 from top to bottom. This function
	 * converts the representation
	 * @param row
	 * @return
	 */
	private static int convertServerRow(String row) {
		// Fix the off-by-one
		int ourRow = Integer.parseInt(row) - 1;
		return 7 - ourRow;
	}

	private static int convertRowToServerRow(int row) {
		return 8 - row;
	}

	public static String convertMoveToServerNotation(Board b, Move m) {
		String serverNotation = "";
		// Get the piece's letter notation and add it to the return string
		serverNotation += convertIntToServerChar(b.board[m.start.rank][m.start.file]);
		// Get the file notation for the start row
		serverNotation += fileIntegerToFile.get(m.start.file);
		serverNotation += convertRowToServerRow(m.start.rank);
		serverNotation += fileIntegerToFile.get(m.end.file);
		serverNotation += convertRowToServerRow(m.end.rank);
		// For promotion stuff
		int color = MoveHandler.getColorOfPiece(b, m.start);
		if (Board.isPawnPromotion(b, m, color)) {
			// Just assume that we want to promote to the queen.
			// Can tweak later if we feel inclined to do so.
			if (color == Board.white) {
				serverNotation += convertIntToServerChar(Board.whiteQueen);
			} else if (color == Board.black) {
				serverNotation += convertIntToServerChar(Board.blackQueen);
			}
		}
		return serverNotation;
	}

	/**
	 * A utility function to convert the integer representation of a piece in the board
	 * to a string representation of a piece.
	 * @param piece the integer representation of a piece
	 * @param includeColor if the return value should be prepended with a "W"
	 *                     for a white piece or a "B" for a black piece.
	 * @return string representation of a piece
	 */
	public static String convertIntToPieceString(int piece, boolean includeColor) {
		String p = "";
		switch (piece) {
			case Board.whiteBishop:
			case Board.blackBishop:
				p = "B";
				break;
			case Board.whiteKing:
			case Board.blackKing:
				p = "K";
				break;
			case Board.whiteKnight:
			case Board.blackKnight:
				p = "N";
				break;
			case Board.whitePawn:
			case Board.blackPawn:
				p = "P";
				break;
			case Board.whiteQueen:
			case Board.blackQueen:
				p = "Q";
				break;
			case Board.whiteRook:
			case Board.blackRook:
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

	public static int getColorOfPiece(Board b, Position p) {
		if (b.board[p.rank][p.file] < 0) {
			return Board.black;
		} else if (b.board[p.rank][p.file] > 0) {
			return Board.white;
		}
		return 0;
	}

	public static int convertLetterToPieceInt(String letter) {
		if (letter.equals("P")) {
			return 1;
		} else if (letter.equals("N")) {
			return 2;
		} else if (letter.equals("B")) {
			return 3;
		} else if (letter.equals("R")) {
			return 4;
		} else if (letter.equals("Q")) {
			return 5;
		} else if (letter.equals("K")) {
			return 6;
		}
		return 0;
	}
}
