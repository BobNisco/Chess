package main.java.com.chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayChess {

	public Board board;
	public int color;
	private Random randomGenerator;

	public PlayChess(int color) {
		this.board = new Board();
		this.color = color;
		this.randomGenerator = new Random();
	}

	public static void main(String[] args) {
		PlayChess play = new PlayChess(Board.white);
		System.out.println(play.board);
		int numberOfMoves = 12;
		for (int i = 0; i < numberOfMoves; i++) {
			Node nextNode = MiniMax.performMiniMax(play.board, play.color, 2);
			play.board.handleMove(nextNode.m);
			System.out.println(play.board + "\n\n");
			ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(play.board, Board.black);
			int index = play.randomGenerator.nextInt(actions.size());
			play.board.handleMove(actions.get(index));
			System.out.println(play.board);
			System.out.println("----------------------------------");
		}
	}
}
