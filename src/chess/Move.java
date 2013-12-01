package chess;

/**
 * A class representing a single move on the board
 */
public class Move {
	public int startRank;
	public int startFile;
	public int endRank;
	public int endFile;

	public Move() {
		this.startRank = -1;
		this.startFile = -1;
		this.endRank = -1;
		this.endFile = -1;
	}

	public Move(int startRank, int startFile, int endRank, int endFile) {
		this.startRank = startRank;
		this.startFile = startFile;
		this.endRank = endRank;
		this.endFile = endFile;
	}
}
