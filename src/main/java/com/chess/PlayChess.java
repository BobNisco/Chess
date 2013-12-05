package main.java.com.chess;

public class PlayChess {

	public Board board;
	public int color;

	public PlayChess(int color) {
		this.board = new Board();
		this.color = color;
	}

	public static void main(String[] args) {
		PlayChess play = new PlayChess(Board.white);

		Node nextNode = MiniMax.performMiniMax(play.board, play.color, 2);
		System.out.println(nextNode.b);
	}
}
