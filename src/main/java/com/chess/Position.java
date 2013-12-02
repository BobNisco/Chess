package main.java.com.chess;

public class Position {
	public int rank;
	public int file;

	public Position() {
		this.rank = -1;
		this.file = -1;
	}

	public Position(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}

	@Override
	public String toString() {
		return "(" + this.rank + ", " + this.file + ")";
	}

	@Override
	public boolean equals(Object m) {
		if (m == this) {
			return true;
		}
		if (m == null || m.getClass() != this.getClass()) {
			return false;
		}

		Position p = (Position) m;

		if (p.file == this.file &&
			p.rank == this.rank) {
			return true;
		}
		return false;
	}
}
