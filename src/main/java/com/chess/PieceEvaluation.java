package main.java.com.chess;

public class PieceEvaluation {
	public Board b;

	public PieceEvaluation() {
		this.b = new Board();
	}

	public PieceEvaluation(Board b) {
		this.b = b;
	}

	/**
	 * If you are playing as the black player, mirror the evaluation functions
	 * so that you can get an accurate evaluation function
	 */
	public void mirrorForBlack() {
		// Create a copy of the starting board
		int[][] originalBoard = new int[8][8];
		for (int rank = 0; rank < this.b.board.length; rank++) {
			for (int file = 0; file < this.b.board[rank].length; file++) {
				originalBoard[rank][file] = this.b.board[rank][file];
			}
		}

		// Do the swap
		for (int rank = 0; rank < this.b.board.length; rank++) {
			for (int file = 0; file < this.b.board[rank].length; file++) {
				this.b.board[rank][file] = originalBoard[7 - rank][7 - file];
			}
		}
	}
}
