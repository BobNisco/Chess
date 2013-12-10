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
		int blockedPawns = Evaluator.numberOfBlockedPawns(b, Board.white);
		assertEquals(1, blockedPawns);
	}

	@Test
	public void testBlockedWhitePawns01() throws Exception {
		Board b = new Board();
		b.board[4][3] = Board.whitePawn;
		b.board[3][4] = Board.whitePawn;
		b.board[3][3] = Board.blackPawn;
		b.board[6][3] = Board.empty;
		b.board[6][4] = Board.empty;
		int blockedPawns = Evaluator.numberOfBlockedPawns(b, Board.white);
		assertEquals(1, blockedPawns);
	}

	@Test
	public void testBlockedBlackPawns00() throws Exception {
		Board b = new Board();
		b.board[2][0] = Board.whitePawn;
		b.board[5][0] = Board.empty;
		int blockedPawns = Evaluator.numberOfBlockedPawns(b, Board.black);
		assertEquals(1, blockedPawns);
	}

	@Test
	public void testIsolatedWhitePawns00() throws Exception {
		Board b = new Board();
		b.board[6][1] = Board.empty;
		int isolatedPawns = Evaluator.numberOfIsolatedPawns(b, Board.white);
		System.out.println(b);
		assertEquals(1, isolatedPawns);
	}
}