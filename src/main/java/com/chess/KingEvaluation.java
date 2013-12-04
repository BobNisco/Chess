package main.java.com.chess;

public class KingEvaluation extends PieceEvaluation {

	public KingEvaluation(int color) {
		super(color);
		this.setKingEvaluation();
		if (color == Board.black) {
			this.mirrorForBlack();
		}
	}

    private void setKingEvaluation() {
        this.b.board[0][0] = -30;
        this.b.board[0][1] = -40;
        this.b.board[0][2] = -40;
        this.b.board[0][3] = -50;
        this.b.board[0][4] = -50;
        this.b.board[0][5] = -40;
        this.b.board[0][6] = -40;
        this.b.board[0][7] = -30;

        this.b.board[1][0] = -30;
        this.b.board[1][1] = -40;
        this.b.board[1][2] = -40;
        this.b.board[1][3] = -50;
        this.b.board[1][4] = -50;
        this.b.board[1][5] = -40;
        this.b.board[1][6] = -40;
        this.b.board[1][7] = -30;

        this.b.board[2][0] = -30;
        this.b.board[2][1] = -40;
        this.b.board[2][2] = -40;
        this.b.board[2][3] = -50;
        this.b.board[2][4] = -50;
        this.b.board[2][5] = -40;
        this.b.board[2][6] = -40;
        this.b.board[2][7] = -30;

        this.b.board[3][0] = -30;
        this.b.board[3][1] = -40;
        this.b.board[3][2] = -40;
        this.b.board[3][3] = -50;
        this.b.board[3][4] = -50;
        this.b.board[3][5] = -40;
        this.b.board[3][6] = -40;
        this.b.board[3][7] = -30;

        this.b.board[4][0] = -20;
        this.b.board[4][1] = -30;
        this.b.board[4][2] = -30;
        this.b.board[4][3] = -40;
        this.b.board[4][4] = -40;
        this.b.board[4][5] = -30;
        this.b.board[4][6] = -30;
        this.b.board[4][7] = -20;

        this.b.board[5][0] = -10;
        this.b.board[5][1] = -20;
        this.b.board[5][2] = -20;
        this.b.board[5][3] = -20;
        this.b.board[5][4] = -20;
        this.b.board[5][5] = -20;
        this.b.board[5][6] = -20;
        this.b.board[5][7] = -10;

        this.b.board[6][0] = 20;
        this.b.board[6][1] = 20;
        this.b.board[6][2] = 0;
        this.b.board[6][3] = 0;
        this.b.board[6][4] = 0;
        this.b.board[6][5] = 0;
        this.b.board[6][6] = 20;
        this.b.board[6][7] = 20;

        this.b.board[7][0] = 20;
        this.b.board[7][1] = 30;
        this.b.board[7][2] = 10;
        this.b.board[7][3] = 0;
        this.b.board[7][4] = 0;
        this.b.board[7][5] = 10;
        this.b.board[7][6] = 30;
        this.b.board[7][7] = 20;




    }

}
