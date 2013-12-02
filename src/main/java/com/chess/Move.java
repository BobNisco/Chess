package main.java.com.chess;

/**
 * A class representing a single move on the board
 */
public class Move {
	public Position start;
	public Position end;

	public Move() {
		this.start = new Position();
		this.end = new Position();
	}

	public Move(int startRank, int startFile, int endRank, int endFile) {
		this.start = new Position(startRank, startFile);
		this.end = new Position(endRank, endFile);
	}

	public Move(Position start, Position end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "Start: " + this.start.toString() + " End: " + this.end.toString();
	}

	@Override
	public boolean equals(Object m) {
		if (m == this) {
			return true;
		}
		if (m == null || m.getClass() != this.getClass()) {
			return false;
		}

		Move move = (Move) m;

		if (move.start.equals(this.start) &&
			move.end.equals(this.end)) {
			return true;
		}
		return false;
	}
}
