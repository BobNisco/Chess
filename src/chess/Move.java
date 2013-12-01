package chess;

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
		return "Start: (" + this.start.rank + ", " + this.start.file +
				") End: (" + this.end.rank + ", " + this.end.file + ")";
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

		if (move.start.file == this.start.file &&
			move.start.rank == this.start.rank &&
			move.end.file == this.end.file &&
			move.end.rank == this.end.rank) {
			return true;
		}
		return false;
	}
}
