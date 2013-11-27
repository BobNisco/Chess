package chess;

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

		if (move.lastmove.length() == 6) {
			// TODO: Handle the promotion
			// For example Pb7b8Q where the pawn is promoted to a queen.
			// Type of piece for promotion ∈ {Q, R, B, N}
		}

		/* This is what the start board is with coordinates
		-4(0,0) -2(0,1) -3(0,2) -5(0,3) -6(0,4) -3(0,5) -2(0,6) -4(0,7)
		-1(1,0) -1(1,1) -1(1,2) -1(1,3) -1(1,4) -1(1,5) -1(1,6) -1(1,7)
		0(2,0) 0(2,1) 0(2,2) 0(2,3) 0(2,4) 0(2,5) 0(2,6) 0(2,7)
		0(3,0) 0(3,1) 0(3,2) 0(3,3) 0(3,4) 0(3,5) 0(3,6) 0(3,7)
		0(4,0) 0(4,1) 0(4,2) 0(4,3) 0(4,4) 0(4,5) 0(4,6) 0(4,7)
		0(5,0) 0(5,1) 0(5,2) 0(5,3) 0(5,4) 0(5,5) 0(5,6) 0(5,7)
		1(6,0) 1(6,1) 1(6,2) 1(6,3) 1(6,4) 1(6,5) 1(6,6) 1(6,7)
		4(7,0) 2(7,1) 3(7,2) 6(7,3) 5(7,4) 3(7,5) 2(7,6) 4(7,7)
		 */

		int[][] oldBoard = b.board.clone();
		// Move the piece from the original space to the new space
		b.board[endRow][endFile] = oldBoard[startRow][startFile];
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

	public static String convertMoveToServerNotation(Board b, int startRow, int startFile, int endRow, int endFile) {
		String serverNotation = "";
		// Get the piece's letter notation and add it to the return string
		serverNotation += convertIntToServerChar(b.board[startRow][startFile]);
		// Get the file notation for the start row
		serverNotation += fileIntegerToFile.get(startFile);
		serverNotation += convertRowToServerRow(startRow);
		serverNotation += fileIntegerToFile.get(endFile);
		serverNotation += convertRowToServerRow(endRow);
		// TODO: Handle promotion
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
}
