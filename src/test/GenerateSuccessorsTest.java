package test;

import chess.Board;
import chess.GenerateSuccessors;
import chess.Move;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GenerateSuccessorsTest {
	private Board b;

	@Before
	public void setUp() throws Exception {
		b = new Board();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testPawnInitialBlack() throws Exception {
		ArrayList<Move> pawnMoves = GenerateSuccessors.pawn(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(1, 0, 2, 0));
		expected.add(new Move(1, 0, 3, 0));
		expected.add(new Move(1, 1, 2, 1));
		expected.add(new Move(1, 1, 3, 1));
		expected.add(new Move(1, 2, 2, 2));
		expected.add(new Move(1, 2, 3, 2));
		expected.add(new Move(1, 3, 2, 3));
		expected.add(new Move(1, 3, 3, 3));
		expected.add(new Move(1, 4, 2, 4));
		expected.add(new Move(1, 4, 3, 4));
		expected.add(new Move(1, 5, 2, 5));
		expected.add(new Move(1, 5, 3, 5));
		expected.add(new Move(1, 6, 2, 6));
		expected.add(new Move(1, 6, 3, 6));
		expected.add(new Move(1, 7, 2, 7));
		expected.add(new Move(1, 7, 3, 7));
		assertEquals(expected, pawnMoves);
	}

	@Test
	public void testPawnInitialWhite() throws Exception {
		ArrayList<Move> pawnMoves = GenerateSuccessors.pawn(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(6, 0, 4, 0));
		expected.add(new Move(6, 0, 5, 0));
		expected.add(new Move(6, 1, 4, 1));
		expected.add(new Move(6, 1, 5, 1));
		expected.add(new Move(6, 2, 4, 2));
		expected.add(new Move(6, 2, 5, 2));
		expected.add(new Move(6, 3, 4, 3));
		expected.add(new Move(6, 3, 5, 3));
		expected.add(new Move(6, 4, 4, 4));
		expected.add(new Move(6, 4, 5, 4));
		expected.add(new Move(6, 5, 4, 5));
		expected.add(new Move(6, 5, 5, 5));
		expected.add(new Move(6, 6, 4, 6));
		expected.add(new Move(6, 6, 5, 6));
		expected.add(new Move(6, 7, 4, 7));
		expected.add(new Move(6, 7, 5, 7));
		assertEquals(expected, pawnMoves);
	}
}
