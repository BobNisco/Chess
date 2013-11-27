package test;

import chess.*;
import org.junit.*;
import static org.junit.Assert.* ;

public class BoardTest {

	private Board b;

	@Before
	public void setUp() throws Exception {
		b = new Board();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testHandleMove00() throws Exception {
		assertEquals("The board should have the pawn in the initial state", b.board[6][3], b.whitePawn);
		Response r = new Response();
		r.lastmove = "Pd2d3";
		b.handleMove(r);
		assertEquals("The board should have moved a Pawn into the new position", b.board[5][3], b.whitePawn);
		assertEquals("There should be no piece in the initial spot", b.board[6][3], 0);
	}

	@Test
	public void testHandleMove01() throws Exception {
		assertEquals("The board should have the knight in the initial state", b.board[7][1], b.whiteKnight);
		Response r = new Response();
		r.lastmove = "Nb1c3";
		b.handleMove(r);
		assertEquals("The board should have moved a knight", b.board[5][2], b.whiteKnight);
		assertEquals("There should be no piece in the initial spot", b.board[7][1], 0);
	}

	@Test
	public void testHandleMove02() throws Exception {
		assertEquals("The board should have the pawn in the initial state", b.board[1][7], b.blackPawn);
		Response r = new Response();
		r.lastmove = "Ph7h5";
		b.handleMove(r);
		assertEquals("The board should have moved a Pawn into the new position", b.board[3][7], b.blackPawn);
		assertEquals("There should be no piece in the initial spot", b.board[1][7], 0);
	}

	@Test
	public void testHandleMove03() throws Exception {
		assertEquals("The board should have the knight in the initial state", b.board[0][1], b.blackKnight);
		Response r = new Response();
		r.lastmove = "Nb8c6";
		b.handleMove(r);
		assertEquals("The board should have moved a knight", b.board[2][2], b.blackKnight);
		assertEquals("There should be no piece in the initial spot", b.board[0][1], 0);
	}

	@Test
	public void testConvertMoveToServerNotation00() throws Exception {
		String serverMove = b.convertMoveToServerNotation(6, 3, 5, 3);
		assertEquals("The move of a pawn from [6][3] to [5][3] must be Pd2d3", "Pd2d3", serverMove);
	}

	@Test
	public void testConvertMoveToServerNotation01() throws Exception {
		String serverMove = b.convertMoveToServerNotation(7, 1, 5, 2);
		assertEquals("The move of a knight from [7][1] to [5][2] must be Nb1c3", "Nb1c3", serverMove);
	}

	@Test
	public void testConvertMoveToServerNotation02() throws Exception {
		String serverMove = b.convertMoveToServerNotation(1, 7, 3, 7);
		assertEquals("The move of a pawn from [1][7] to [3][7] must be Ph7h5", "Ph7h5", serverMove);
	}

	@Test
	public void testConvertMoveToServerNotation03() throws Exception {
		String serverMove = b.convertMoveToServerNotation(0, 1, 2, 2);
		assertEquals("The move of a knight from [0][1] to [2][2] must be Nb8c6", "Nb8c6", serverMove);
	}
}
