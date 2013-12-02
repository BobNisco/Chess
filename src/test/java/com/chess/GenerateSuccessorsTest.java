package test.java.com.chess;

import main.java.com.chess.Board;
import main.java.com.chess.GenerateSuccessors;
import main.java.com.chess.Move;
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
		ArrayList<Move> expected = expectedPawnInitialBlack();
		assertEquals(expected, pawnMoves);
	}

	private ArrayList<Move> expectedPawnInitialBlack() {
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
		return expected;
	}

	@Test
	public void testPawnInitialWhite() throws Exception {
		ArrayList<Move> pawnMoves = GenerateSuccessors.pawn(b, Board.white);
		ArrayList<Move> expected = expectedPawnInitialWhite();
		assertEquals(expected, pawnMoves);
	}

	private ArrayList<Move> expectedPawnInitialWhite() {
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(6, 0, 5, 0));
		expected.add(new Move(6, 0, 4, 0));
		expected.add(new Move(6, 1, 5, 1));
		expected.add(new Move(6, 1, 4, 1));
		expected.add(new Move(6, 2, 5, 2));
		expected.add(new Move(6, 2, 4, 2));
		expected.add(new Move(6, 3, 5, 3));
		expected.add(new Move(6, 3, 4, 3));
		expected.add(new Move(6, 4, 5, 4));
		expected.add(new Move(6, 4, 4, 4));
		expected.add(new Move(6, 5, 5, 5));
		expected.add(new Move(6, 5, 4, 5));
		expected.add(new Move(6, 6, 5, 6));
		expected.add(new Move(6, 6, 4, 6));
		expected.add(new Move(6, 7, 5, 7));
		expected.add(new Move(6, 7, 4, 7));
		return expected;
	}

	@Test
	public void testPawnTakeoverBlack() throws  Exception {
		// Move a white pawn to a space that will allow for a takeover
		// by a black pawn from an initial starting point
		b.board[2][7] = Board.whitePawn;
		b.board[6][7] = Board.empty;
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
		expected.add(new Move(1, 6, 2, 7)); // Takeover
		assertEquals(expected, pawnMoves);
	}

	@Test
	public void testPawnTakeoverWhite() throws Exception {
		// Move a black pawn to a space that will allow for a takeover
		// by a white pawn from an initial starting point
		b.board[5][0] = Board.blackPawn;
		b.board[1][0] = Board.empty;
		ArrayList<Move> pawnMoves = GenerateSuccessors.pawn(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(6, 1, 5, 1));
		expected.add(new Move(6, 1, 4, 1));
		expected.add(new Move(6, 1, 5, 0)); // Takeover
		expected.add(new Move(6, 2, 5, 2));
		expected.add(new Move(6, 2, 4, 2));
		expected.add(new Move(6, 3, 5, 3));
		expected.add(new Move(6, 3, 4, 3));
		expected.add(new Move(6, 4, 5, 4));
		expected.add(new Move(6, 4, 4, 4));
		expected.add(new Move(6, 5, 5, 5));
		expected.add(new Move(6, 5, 4, 5));
		expected.add(new Move(6, 6, 5, 6));
		expected.add(new Move(6, 6, 4, 6));
		expected.add(new Move(6, 7, 5, 7));
		expected.add(new Move(6, 7, 4, 7));
		assertEquals(expected, pawnMoves);
	}

	@Test
	public void testRookInitialBlack() throws Exception {
		ArrayList<Move> rookMoves = GenerateSuccessors.rook(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testRookInitialWhite() throws Exception {
		ArrayList<Move> rookMoves = GenerateSuccessors.rook(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testRookMoveBlack00() throws Exception {
		// Remove a single pawn in front of a rook
		b.board[1][0] = Board.empty;
		ArrayList<Move> rookMoves = GenerateSuccessors.rook(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(0, 0, 1, 0));
		expected.add(new Move(0, 0, 2, 0));
		expected.add(new Move(0, 0, 3, 0));
		expected.add(new Move(0, 0, 4, 0));
		expected.add(new Move(0, 0, 5, 0));
		expected.add(new Move(0, 0, 6, 0));
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testRookMoveBlack01() throws Exception {
		// Move a rook into a position in the middle of the board
		b.board[3][3] = Board.blackRook;
		b.board[0][7] = Board.empty;
		ArrayList<Move> rookMoves = GenerateSuccessors.rook(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(3, 3, 3, 4));
		expected.add(new Move(3, 3, 3, 5));
		expected.add(new Move(3, 3, 3, 6));
		expected.add(new Move(3, 3, 3, 7));
		expected.add(new Move(3, 3, 3, 2));
		expected.add(new Move(3, 3, 3, 1));
		expected.add(new Move(3, 3, 3, 0));
		expected.add(new Move(3, 3, 4, 3));
		expected.add(new Move(3, 3, 5, 3));
		expected.add(new Move(3, 3, 6, 3));
		expected.add(new Move(3, 3, 2, 3));
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testRookMoveWhite00() throws Exception {
		// Remove a single pawn in front of a rook
		b.board[6][7] = Board.empty;
		ArrayList<Move> rookMoves = GenerateSuccessors.rook(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(7, 7, 6, 7));
		expected.add(new Move(7, 7, 5, 7));
		expected.add(new Move(7, 7, 4, 7));
		expected.add(new Move(7, 7, 3, 7));
		expected.add(new Move(7, 7, 2, 7));
		expected.add(new Move(7, 7, 1, 7));
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testRookMoveWhite01() throws Exception {
		// Move a rook into a position in the middle of the board
		b.board[3][3] = Board.whiteRook;
		b.board[7][0] = Board.empty;
		ArrayList<Move> rookMoves = GenerateSuccessors.rook(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(3, 3, 3, 4));
		expected.add(new Move(3, 3, 3, 5));
		expected.add(new Move(3, 3, 3, 6));
		expected.add(new Move(3, 3, 3, 7));
		expected.add(new Move(3, 3, 3, 2));
		expected.add(new Move(3, 3, 3, 1));
		expected.add(new Move(3, 3, 3, 0));
		expected.add(new Move(3, 3, 4, 3));
		expected.add(new Move(3, 3, 5, 3));
		expected.add(new Move(3, 3, 2, 3));
		expected.add(new Move(3, 3, 1, 3));
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testBishopInitialBlack() throws Exception {
		ArrayList<Move> rookMoves = GenerateSuccessors.bishop(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testBishopInitialWhite() throws Exception {
		ArrayList<Move> rookMoves = GenerateSuccessors.bishop(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, rookMoves);
	}

	@Test
	public void testBishopMoveBlack00() throws Exception {
		// Remove a single pawn in the path of a bishop from an initial point
		b.board[1][4] = Board.empty;
		ArrayList<Move> bishopMoves = GenerateSuccessors.bishop(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(0, 5, 1, 4));
		expected.add(new Move(0, 5, 2, 3));
		expected.add(new Move(0, 5, 3, 2));
		expected.add(new Move(0, 5, 4, 1));
		expected.add(new Move(0, 5, 5, 0));
		assertEquals(expected, bishopMoves);
	}

	@Test
	public void testBishopMoveWhite00() throws Exception {
		// Remove a single pawn in the path of a bishop from an initial point
		b.board[6][1] = Board.empty;
		ArrayList<Move> bishopMoves = GenerateSuccessors.bishop(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(7, 2, 6, 1));
		expected.add(new Move(7, 2, 5, 0));
		assertEquals(expected, bishopMoves);
	}

	@Test
	public void testBishopMoveBlack01() throws Exception {
		// Put a bishop somewhere in the middle of the board
		b.board[3][5] = Board.blackBishop;
		b.board[0][5] = Board.empty;
		ArrayList<Move> bishopMoves = GenerateSuccessors.bishop(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(3, 5, 2, 4));
		expected.add(new Move(3, 5, 2, 6));
		expected.add(new Move(3, 5, 4, 4));
		expected.add(new Move(3, 5, 5, 3));
		expected.add(new Move(3, 5, 6, 2));
		expected.add(new Move(3, 5, 4, 6));
		expected.add(new Move(3, 5, 5, 7));
		assertEquals(expected, bishopMoves);
	}

	@Test
	public void testBishopMoveWhite01() throws Exception {
		// Put a bishop somewhere in the middle of the board
		b.board[5][1] = Board.whiteBishop;
		b.board[7][5] = Board.empty;
		ArrayList<Move> bishopMoves = GenerateSuccessors.bishop(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(5, 1, 4, 0));
		expected.add(new Move(5, 1, 4, 2));
		expected.add(new Move(5, 1, 3, 3));
		expected.add(new Move(5, 1, 2, 4));
		expected.add(new Move(5, 1, 1, 5));
		assertEquals(expected, bishopMoves);
	}

	@Test
	public void testKnightInitialBlack() throws Exception {
		ArrayList<Move> knightMoves = GenerateSuccessors.knight(b, Board.black);
		ArrayList<Move> expected = expectedKnightInitialBlack();
		assertEquals(expected, knightMoves);
	}

	private ArrayList<Move> expectedKnightInitialBlack() throws Exception {
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(0, 1, 2, 0));
		expected.add(new Move(0, 1, 2, 2));
		expected.add(new Move(0, 6, 2, 5));
		expected.add(new Move(0, 6, 2, 7));
		return expected;
	}

	@Test
	public void testKnightInitialWhite() throws Exception {
		ArrayList<Move> knightMoves = GenerateSuccessors.knight(b, Board.white);
		ArrayList<Move> expected = expectedKnightInitialWhite();
		assertEquals(expected, knightMoves);
	}

	private ArrayList<Move> expectedKnightInitialWhite() {
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(7, 1, 5, 0));
		expected.add(new Move(7, 1, 5, 2));
		expected.add(new Move(7, 6, 5, 5));
		expected.add(new Move(7, 6, 5, 7));
		return expected;
	}

	@Test
	public void testKnightMoveBlack00() throws Exception {
		b.board[2][0] = Board.blackKnight;
		b.board[0][1] = Board.empty;
		ArrayList<Move> knightMoves = GenerateSuccessors.knight(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(0, 6, 2, 5));
		expected.add(new Move(0, 6, 2, 7));
		expected.add(new Move(2, 0, 4, 1));
		expected.add(new Move(2, 0, 3, 2));
		expected.add(new Move(2, 0, 0, 1));
		assertEquals(expected, knightMoves);
	}

	@Test
	public void testKnightMoveWhite00() throws Exception {
		b.board[5][5] = Board.whiteKnight;
		b.board[7][6] = Board.empty;
		ArrayList<Move> knightMoves = GenerateSuccessors.knight(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(5, 5, 7, 6));
		expected.add(new Move(5, 5, 3, 4));
		expected.add(new Move(5, 5, 3, 6));
		expected.add(new Move(5, 5, 4, 3));
		expected.add(new Move(5, 5, 4, 7));
		expected.add(new Move(7, 1, 5, 0));
		expected.add(new Move(7, 1, 5, 2));
		assertEquals(expected, knightMoves);
	}

	@Test
	public void testQueenInitialBlack() throws Exception {
		ArrayList<Move> queenMoves = GenerateSuccessors.queen(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, queenMoves);
	}

	@Test
	public void testQueenInitialWhite() throws Exception {
		ArrayList<Move> queenMoves = GenerateSuccessors.queen(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, queenMoves);
	}

	@Test
	public void testQueenMoveBlack00() throws Exception {
		b.board[4][3] = Board.blackQueen;
		b.board[0][3] = Board.empty;
		ArrayList<Move> queenMoves = GenerateSuccessors.queen(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(4, 3, 4, 2));
		expected.add(new Move(4, 3, 4, 1));
		expected.add(new Move(4, 3, 4, 0));
		expected.add(new Move(4, 3, 4, 4));
		expected.add(new Move(4, 3, 4, 5));
		expected.add(new Move(4, 3, 4, 6));
		expected.add(new Move(4, 3, 4, 7));
		expected.add(new Move(4, 3, 3, 3));
		expected.add(new Move(4, 3, 2, 3));
		expected.add(new Move(4, 3, 5, 3));
		expected.add(new Move(4, 3, 6, 3));
		expected.add(new Move(4, 3, 3, 2));
		expected.add(new Move(4, 3, 2, 1));
		expected.add(new Move(4, 3, 3, 4));
		expected.add(new Move(4, 3, 2, 5));
		expected.add(new Move(4, 3, 5, 2));
		expected.add(new Move(4, 3, 6, 1));
		expected.add(new Move(4, 3, 5, 4));
		expected.add(new Move(4, 3, 6, 5));
		assertEquals(expected, queenMoves);
	}

	@Test
	public void testQueenMoveWhite00() throws Exception {
		b.board[4][4] = Board.whiteQueen;
		b.board[7][4] = Board.empty;
		ArrayList<Move> queenMoves = GenerateSuccessors.queen(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(4, 4, 4, 3));
		expected.add(new Move(4, 4, 4, 2));
		expected.add(new Move(4, 4, 4, 1));
		expected.add(new Move(4, 4, 4, 0));
		expected.add(new Move(4, 4, 4, 5));
		expected.add(new Move(4, 4, 4, 6));
		expected.add(new Move(4, 4, 4, 7));
		expected.add(new Move(4, 4, 3, 4));
		expected.add(new Move(4, 4, 2, 4));
		expected.add(new Move(4, 4, 1, 4));
		expected.add(new Move(4, 4, 5, 4));
		expected.add(new Move(4, 4, 3, 3));
		expected.add(new Move(4, 4, 2, 2));
		expected.add(new Move(4, 4, 1, 1));
		expected.add(new Move(4, 4, 3, 5));
		expected.add(new Move(4, 4, 2, 6));
		expected.add(new Move(4, 4, 1, 7));
		expected.add(new Move(4, 4, 5, 3));
		expected.add(new Move(4, 4, 5, 5));
		assertEquals(expected, queenMoves);
	}

	@Test
	public void testKingInitialBlack() throws Exception {
		ArrayList<Move> kingMoves = GenerateSuccessors.king(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, kingMoves);
	}

	@Test
	public void testKingInitialWhite() throws Exception {
		ArrayList<Move> kingMoves = GenerateSuccessors.king(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		assertEquals(expected, kingMoves);
	}

	@Test
	public void testKingMoveBlack00() throws Exception {
		b.board[5][6] = Board.blackKing;
		b.board[0][4] = Board.empty;
		ArrayList<Move> kingMoves = GenerateSuccessors.king(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(5, 6, 4, 6));
		expected.add(new Move(5, 6, 4, 7));
		expected.add(new Move(5, 6, 5, 7));
		expected.add(new Move(5, 6, 6, 7));
		expected.add(new Move(5, 6, 6, 6));
		expected.add(new Move(5, 6, 6, 5));
		expected.add(new Move(5, 6, 5, 5));
		expected.add(new Move(5, 6, 4, 5));
		assertEquals(expected, kingMoves);
	}

	@Test
	public void testKingMoveWhite00() throws Exception {
		b.board[2][0] = Board.whiteKing;
		b.board[7][3] = Board.empty;
		ArrayList<Move> kingMoves = GenerateSuccessors.king(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(2, 0, 1, 0));
		expected.add(new Move(2, 0, 1, 1));
		expected.add(new Move(2, 0, 2, 1));
		expected.add(new Move(2, 0, 3, 1));
		expected.add(new Move(2, 0, 3, 0));
		assertEquals(expected, kingMoves);
	}

	@Test
	public void testAllPossibleSuccessorsInitialBlack() throws Exception {
		ArrayList<Move> allMoves = GenerateSuccessors.allPossibleSuccessors(b, Board.black);
		ArrayList<Move> expected = new ArrayList<Move>();
		// Only pawns and knights can make an initial move
		expected.addAll(expectedPawnInitialBlack());
		expected.addAll(expectedKnightInitialBlack());
		assertEquals(expected, allMoves);
	}

	@Test
	public void testAllPossibleSuccessorsInitialWhite() throws Exception {
		ArrayList<Move> allMoves = GenerateSuccessors.allPossibleSuccessors(b, Board.white);
		ArrayList<Move> expected = new ArrayList<Move>();
		// Only pawns and knights can make an initial move
		expected.addAll(expectedPawnInitialWhite());
		expected.addAll(expectedKnightInitialWhite());
		assertEquals(expected, allMoves);
	}
}
