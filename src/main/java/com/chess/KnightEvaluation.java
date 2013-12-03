package main.java.com.chess;

public class KnightEvaluation extends PieceEvaluation {

	public KnightEvaluation() {
		super();
		this.setKnightEvaluation();
	}

	public KnightEvaluation(Board b, int color) {
		super(b);
		this.setKnightEvaluation();
		if (color == Board.black) {
			this.mirrorForBlack();
		}
	}

	private void setKnightEvaluation() {
		this.b.board[0][0] = -50;
		this.b.board[0][1] = -40;
		this.b.board[0][2] = -30;
		this.b.board[0][3] = -30;
		this.b.board[0][4] = -30;
		this.b.board[0][5] = -30;
		this.b.board[0][6] = -40;
		this.b.board[0][7] = -50;
		this.b.board[1][0] = -40;
		this.b.board[1][1] = -20;
		this.b.board[1][2] = 0;
		this.b.board[1][3] = 0;
		this.b.board[1][4] = 0;
		this.b.board[1][5] = 0;
		this.b.board[1][6] = -20;
		this.b.board[1][7] = -40;
		this.b.board[2][0] = -30;
		this.b.board[2][1] = 0;
		this.b.board[2][2] = 10;
		this.b.board[2][3] = 15;
		this.b.board[2][4] = 15;
		this.b.board[2][5] = 10;
		this.b.board[2][6] = 0;
		this.b.board[2][7] = -30;
		this.b.board[3][0] = -30;
		this.b.board[3][1] = 5;
		this.b.board[3][2] = 15;
		this.b.board[3][3] = 20;
		this.b.board[3][4] = 20;
		this.b.board[3][5] = 15;
		this.b.board[3][6] = 5;
		this.b.board[3][7] = -30;
		this.b.board[4][0] = -30;
		this.b.board[4][1] = 0;
		this.b.board[4][2] = 15;
		this.b.board[4][3] = 20;
		this.b.board[4][4] = 20;
		this.b.board[4][5] = 15;
		this.b.board[4][6] = 0;
		this.b.board[4][7] = -30;
		this.b.board[5][0] = -30;
		this.b.board[5][1] = 5;
		this.b.board[5][2] = 10;
		this.b.board[5][3] = 15;
		this.b.board[5][4] = 15;
		this.b.board[5][5] = 10;
		this.b.board[5][6] = 5;
		this.b.board[5][7] = -30;
		this.b.board[6][0] = 40;
		this.b.board[6][1] = -20;
		this.b.board[6][2] = 0;
		this.b.board[6][3] = 5;
		this.b.board[6][4] = 5;
		this.b.board[6][5] = 0;
		this.b.board[6][6] = -20;
		this.b.board[6][7] = -40;
		this.b.board[7][0] = -50;
		this.b.board[7][1] = -40;
		this.b.board[7][2] = -30;
		this.b.board[7][3] = -30;
		this.b.board[7][4] = -30;
		this.b.board[7][5] = -30;
		this.b.board[7][6] = -40;
		this.b.board[7][7] = -50;
	}
}
