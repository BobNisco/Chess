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
}
