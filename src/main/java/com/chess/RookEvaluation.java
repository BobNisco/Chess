package main.java.com.chess;

public class RookEvaluation extends PieceEvaluation {

	public RookEvaluation(int color) {
		super(color);
		this.setRookEvaluation();
		if (color == Board.black) {
			this.mirrorForBlack();
		}
	}

	private void setRookEvaluation() {
		this.b.board[0][0] = 0;
		this.b.board[0][1] = 0;
		this.b.board[0][2] = 0;
		this.b.board[0][3] = 0;
		this.b.board[0][4] = 0;
		this.b.board[0][5] = 0;
		this.b.board[0][6] = 0;
		this.b.board[0][7] = 0;
		this.b.board[1][0] = 5;
		this.b.board[1][1] = 10;
		this.b.board[1][2] = 10;
		this.b.board[1][3] = 10;
		this.b.board[1][4] = 10;
		this.b.board[1][5] = 10;
		this.b.board[1][6] = 10;
		this.b.board[1][7] = 5;
		this.b.board[2][0] = -5;
		this.b.board[2][1] = 0;
		this.b.board[2][2] = 0;
		this.b.board[2][3] = 0;
		this.b.board[2][4] = 0;
		this.b.board[2][5] = 0;
		this.b.board[2][6] = 0;
		this.b.board[2][7] = -5;
		this.b.board[3][0] = -5;
		this.b.board[3][1] = 0;
		this.b.board[3][2] = 0;
		this.b.board[3][3] = 0;
		this.b.board[3][4] = 0;
		this.b.board[3][5] = 0;
		this.b.board[3][6] = 0;
		this.b.board[3][7] = -5;
		this.b.board[4][0] = -5;
		this.b.board[4][1] = 0;
		this.b.board[4][2] = 0;
		this.b.board[4][3] = 0;
		this.b.board[4][4] = 0;
		this.b.board[4][5] = 0;
		this.b.board[4][6] = 0;
		this.b.board[4][7] = -5;
		this.b.board[5][0] = -5;
		this.b.board[5][1] = 0;
		this.b.board[5][2] = 0;
		this.b.board[5][3] = 0;
		this.b.board[5][4] = 0;
		this.b.board[5][5] = 0;
		this.b.board[5][6] = 0;
		this.b.board[5][7] = -5;
		this.b.board[6][0] = -5;
		this.b.board[6][1] = 0;
		this.b.board[6][2] = 0;
		this.b.board[6][3] = 0;
		this.b.board[6][4] = 0;
		this.b.board[6][5] = 0;
		this.b.board[6][6] = 0;
		this.b.board[6][7] = -5;
		this.b.board[7][0] = 0;
		this.b.board[7][1] = 0;
		this.b.board[7][2] = 0;
		this.b.board[7][3] = 5;
		this.b.board[7][4] = 5;
		this.b.board[7][5] = 0;
		this.b.board[7][6] = 0;
		this.b.board[7][7] = 0;
	}
}
