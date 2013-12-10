package test.java.com.chess;

import main.java.com.chess.Board;
import main.java.com.chess.Evaluator;
import main.java.com.chess.PieceEvaluation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PieceEvaluationTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testDoubledWhitePawns00() throws Exception {
		Board b = new Board();
		b.board[5][0] = Board.whitePawn;
		b.board[6][1] = Board.empty;
		int doubledPawns = Evaluator.numberOfDoubledPawns(b, Board.white);
		assertEquals(1, doubledPawns);
	}

	@Test
	public void testDoubledWhitePawns01() throws Exception {
		Board b = new Board();
		b.board[5][7] = Board.whitePawn;
		b.board[6][6] = Board.empty;
		int doubledPawns = Evaluator.numberOfDoubledPawns(b, Board.white);
		assertEquals(1, doubledPawns);
	}

	@Test
	public void testDoubledBlackPawns00() throws Exception {
		Board b = new Board();
		b.board[3][0] = Board.blackPawn;
		b.board[2][1] = Board.empty;
		int doubledPawns = Evaluator.numberOfDoubledPawns(b, Board.blackPawn);
		assertEquals(1, doubledPawns);
	}

	@Test
	public void testDoubledBlackPawns01() throws Exception {
		Board b = new Board();
		b.board[3][7] = Board.blackPawn;
		b.board[2][6] = Board.empty;
		int doubledPawns = Evaluator.numberOfDoubledPawns(b, Board.blackPawn);
		assertEquals(1, doubledPawns);
	}

	@Test
	public void testBlockedWhitePawns00() throws Exception {
		Board b = new Board();
		b.board[5][0] = Board.blackPawn;
		b.board[1][0] = Board.empty;
		System.out.println(b);
		int blockedPawns = Evaluator.numberOfBlockedPawns(b, Board.white);
		assertEquals(1, blockedPawns);
	}
}