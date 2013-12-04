package main.java.com.chess;

public class QueenEvaluation extends PieceEvaluation {

	public QueenEvaluation(int color) {
		super(color);
		this.setQueenEvaluation();
		if (color == Board.black) {
			this.mirrorForBlack();
		}
	}

    private void setQueenEvaluation() {
        this.b.board[0][0] = -20;
        this.b.board[0][1] = -10;
        this.b.board[0][2] = -10;
        this.b.board[0][3] = -5;
        this.b.board[0][4] = -5;
        this.b.board[0][5] = -10;
        this.b.board[0][6] = -10;
        this.b.board[0][7] = -20;

        this.b.board[1][0] = -10;
        this.b.board[1][1] = 0;
        this.b.board[1][2] = 0;
        this.b.board[1][3] = 0;
        this.b.board[1][4] = 0;
        this.b.board[1][5] = 0;
        this.b.board[1][6] = 0;
        this.b.board[1][7] = -10;

        this.b.board[2][0] = -10;
        this.b.board[2][1] = 0;
        this.b.board[2][2] = 5;
        this.b.board[2][3] = 5;
        this.b.board[2][4] = 5;
        this.b.board[2][5] = 5;
        this.b.board[2][6] = 0;
        this.b.board[2][7] = -10;

        this.b.board[3][0] = -5;
        this.b.board[3][1] = 0;
        this.b.board[3][2] = 5;
        this.b.board[3][3] = 5;
        this.b.board[3][4] = 5;
        this.b.board[3][5] = 5;
        this.b.board[3][6] = 0;
        this.b.board[3][7] = -5;

        this.b.board[4][0] = 0;
        this.b.board[4][1] = 0;
        this.b.board[4][2] = 5;
        this.b.board[4][3] = 5;
        this.b.board[4][4] = 5;
        this.b.board[4][5] = 5;
        this.b.board[4][6] = 0;
        this.b.board[4][7] = -5;

        this.b.board[5][0] = -10;
        this.b.board[5][1] = 5;
        this.b.board[5][2] = 5;
        this.b.board[5][3] = 5;
        this.b.board[5][4] = 5;
        this.b.board[5][5] = 5;
        this.b.board[5][6] = 0;
        this.b.board[5][7] = -10;

        this.b.board[6][0] = -10;
        this.b.board[6][1] = 0;
        this.b.board[6][2] = 5;
        this.b.board[6][3] = 0;
        this.b.board[6][4] = 0;
        this.b.board[6][5] = 0;
        this.b.board[6][6] = 0;
        this.b.board[6][7] = -10;

        this.b.board[7][0] = -20;
        this.b.board[7][1] = -10;
        this.b.board[7][2] = -10;
        this.b.board[7][3] = -5;
        this.b.board[7][4] = -5;
        this.b.board[7][5] = -10;
        this.b.board[7][6] = -10;
        this.b.board[7][7] = -20;



    }
}
