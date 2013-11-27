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
		assertEquals("The board should have the pawn in the initial state", b.whitePawn, b.board[6][3]);
		Response r = new Response();
		r.lastmove = "Pd2d3";
		MoveHandler.handleMove(b, r);
		assertEquals("The board should have moved a Pawn into the new position", b.whitePawn, b.board[5][3]);
		assertEquals("There should be no piece in the initial spot", b.empty, b.board[6][3]);
	}

	@Test
	public void testHandleMove01() throws Exception {
		assertEquals("The board should have the knight in the initial state", b.whiteKnight, b.board[7][1]);
		Response r = new Response();
		r.lastmove = "Nb1c3";
		MoveHandler.handleMove(b, r);
		assertEquals("The board should have moved a knight", b.whiteKnight, b.board[5][2]);
		assertEquals("There should be no piece in the initial spot", b.empty, b.board[7][1]);
	}

	@Test
	public void testHandleMove02() throws Exception {
		assertEquals("The board should have the pawn in the initial state", b.blackPawn, b.board[1][7]);
		Response r = new Response();
		r.lastmove = "Ph7h5";
		MoveHandler.handleMove(b, r);
		assertEquals("The board should have moved a Pawn into the new position", b.blackPawn, b.board[3][7]);
		assertEquals("There should be no piece in the initial spot", b.empty, b.board[1][7]);
	}

	@Test
	public void testHandleMove03() throws Exception {
		assertEquals("The board should have the knight in the initial state", b.blackKnight, b.board[0][1]);
		Response r = new Response();
		r.lastmove = "Nb8c6";
		MoveHandler.handleMove(b, r);
		assertEquals("The board should have moved a knight", b.blackKnight, b.board[2][2]);
		assertEquals("There should be no piece in the initial spot", b.empty, b.board[0][1]);
	}

	@Test
	public void testConvertMoveToServerNotation00() throws Exception {
		String serverMove = MoveHandler.convertMoveToServerNotation(b, 6, 3, 5, 3);
		assertEquals("The move of a pawn from [6][3] to [5][3] must be Pd2d3", "Pd2d3", serverMove);
	}

	@Test
	public void testConvertMoveToServerNotation01() throws Exception {
		String serverMove = MoveHandler.convertMoveToServerNotation(b, 7, 1, 5, 2);
		assertEquals("The move of a knight from [7][1] to [5][2] must be Nb1c3", "Nb1c3", serverMove);
	}

	@Test
	public void testConvertMoveToServerNotation02() throws Exception {
		String serverMove = MoveHandler.convertMoveToServerNotation(b, 1, 7, 3, 7);
		assertEquals("The move of a pawn from [1][7] to [3][7] must be Ph7h5", "Ph7h5", serverMove);
	}

	@Test
	public void testConvertMoveToServerNotation03() throws Exception {
		String serverMove = MoveHandler.convertMoveToServerNotation(b, 0, 1, 2, 2);
		assertEquals("The move of a knight from [0][1] to [2][2] must be Nb8c6", "Nb8c6", serverMove);
	}
}
