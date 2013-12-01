package chess;

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
}
