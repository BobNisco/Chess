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
		System.out.println("START BOARD");
		System.out.println(play.board);
		Node nextNode = MiniMax.performMiniMax(play.board, play.color, 2);
		System.out.println(nextNode.m);
		play.board.handleMove(nextNode.m);
		System.out.println(play.board);
		play.board.handleMove(new Move(1, 1, 3, 1));
		System.out.println(play.board);
		nextNode = MiniMax.performMiniMax(play.board, play.color, 2);
		System.out.println(nextNode.m);
		play.board.handleMove(nextNode.m);
		System.out.println(play.board);
		play.board.handleMove(new Move(0, 1, 2, 2));
		System.out.println(play.board);
		nextNode = MiniMax.performMiniMax(play.board, play.color, 2);
		System.out.println(nextNode.m);
		play.board.handleMove(nextNode.m);
		System.out.println(play.board);
	}
}
