package main.java.com.chess;

public class PawnEvaluation extends PieceEvaluation {

	public PawnEvaluation(int color) {
		super(color);
		this.setPawnEvaluation();
		if (color == Board.black) {
			this.mirrorForBlack();
		}
	}

	public void setPawnEvaluation() {
		this.b.board[0][0] = 70;
		this.b.board[0][1] = 70;
		this.b.board[0][2] = 70;
		this.b.board[0][3] = 70;
		this.b.board[0][4] = 70;
		this.b.board[0][5] = 70;
		this.b.board[0][6] = 70;
		this.b.board[0][7] = 70;
		this.b.board[1][0] = 50;
		this.b.board[1][1] = 50;
		this.b.board[1][2] = 50;
		this.b.board[1][3] = 50;
		this.b.board[1][4] = 50;
		this.b.board[1][5] = 50;
		this.b.board[1][6] = 50;
		this.b.board[1][7] = 50;
		this.b.board[2][0] = 10;
		this.b.board[2][1] = 10;
		this.b.board[2][2] = 20;
		this.b.board[2][3] = 30;
		this.b.board[2][4] = 30;
		this.b.board[2][5] = 20;
		this.b.board[2][6] = 10;
		this.b.board[2][7] = 10;
		this.b.board[3][0] = 5;
		this.b.board[3][1] = 5;
		this.b.board[3][2] = 10;
		this.b.board[3][3] = 25;
		this.b.board[3][4] = 25;
		this.b.board[3][5] = 10;
		this.b.board[3][6] = 5;
		this.b.board[3][7] = 5;
		this.b.board[4][0] = 0;
		this.b.board[4][1] = 0;
		this.b.board[4][2] = 0;
		this.b.board[4][3] = 20;
		this.b.board[4][4] = 20;
		this.b.board[4][5] = 0;
		this.b.board[4][6] = 0;
		this.b.board[4][7] = 0;
		this.b.board[5][0] = 5;
		this.b.board[5][1] = -5;
		this.b.board[5][2] = -10;
		this.b.board[5][3] = 0;
		this.b.board[5][4] = 0;
		this.b.board[5][5] = -10;
		this.b.board[5][6] = -5;
		this.b.board[5][7] = 5;
		this.b.board[6][0] = 5;
		this.b.board[6][1] = 10;
		this.b.board[6][2] = 10;
		this.b.board[6][3] = -20;
		this.b.board[6][4] = -20;
		this.b.board[6][5] = 10;
		this.b.board[6][6] = 10;
		this.b.board[6][7] = 5;
		this.b.board[7][0] = 0;
		this.b.board[7][1] = 0;
		this.b.board[7][2] = 0;
		this.b.board[7][3] = 0;
		this.b.board[7][4] = 0;
		this.b.board[7][5] = 0;
		this.b.board[7][6] = 0;
		this.b.board[7][7] = 0;
	}
}
