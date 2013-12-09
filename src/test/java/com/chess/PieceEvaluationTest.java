package test.java.com.chess;

import main.java.com.chess.Board;
import main.java.com.chess.PieceEvaluation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PieceEvaluationTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testPawnEvaluationMirrorForBlack() throws Exception {
		int[][] expected = new int[8][8];
		expected[7][7] = 70;
		expected[7][6] = 70;
		expected[7][5] = 70;
		expected[7][4] = 70;
		expected[7][3] = 70;
		expected[7][2] = 70;
		expected[7][1] = 70;
		expected[7][0] = 70;
		expected[6][7] = 50;
		expected[6][6] = 50;
		expected[6][5] = 50;
		expected[6][4] = 50;
		expected[6][3] = 50;
		expected[6][2] = 50;
		expected[6][1] = 50;
		expected[6][0] = 50;
		expected[5][7] = 10;
		expected[5][6] = 10;
		expected[5][5] = 20;
		expected[5][4] = 30;
		expected[5][3] = 30;
		expected[5][2] = 20;
		expected[5][1] = 10;
		expected[5][0] = 10;
		expected[4][7] = 5;
		expected[4][6] = 5;
		expected[4][5] = 10;
		expected[4][4] = 25;
		expected[4][3] = 25;
		expected[4][2] = 10;
		expected[4][1] = 5;
		expected[4][0] = 5;
		expected[3][7] = 0;
		expected[3][6] = 0;
		expected[3][5] = 0;
		expected[3][4] = 20;
		expected[3][3] = 20;
		expected[3][2] = 0;
		expected[3][1] = 0;
		expected[3][0] = 0;
		expected[2][7] = 5;
		expected[2][6] = -5;
		expected[2][5] = -10;
		expected[2][4] = 0;
		expected[2][3] = 0;
		expected[2][2] = -10;
		expected[2][1] = -5;
		expected[2][0] = 5;
		expected[1][7] = 5;
		expected[1][6] = 10;
		expected[1][5] = 10;
		expected[1][4] = -20;
		expected[1][3] = -20;
		expected[1][2] = 10;
		expected[1][1] = 10;
		expected[1][0] = 5;
		expected[0][7] = 0;
		expected[0][6] = 0;
		expected[0][5] = 0;
		expected[0][4] = 0;
		expected[0][3] = 0;
		expected[0][2] = 0;
		expected[0][1] = 0;
		expected[0][0] = 0;
		assertArrayEquals(expected, PieceEvaluation.blackPawn);
	}
}