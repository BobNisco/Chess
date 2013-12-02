package main.java.com.chess;

/**
 * A class representing the JSON response from the game server
 * as outlined in the assignment PDF.
 */
public class Response {
	public boolean ready;
	public float secondsleft;
	public int lastmovenumber;
	public String lastmove;

	public Response() {
		this.ready = false;
		this.secondsleft = -1;
		this.lastmovenumber = -1;
		this.lastmove = "";
	}

	public Response(boolean ready, float secondsleft, int lastmovenumber) {
		this.ready = ready;
		this.secondsleft = secondsleft;
		this.lastmovenumber = lastmovenumber;
		this.lastmove = null;
	}

	public Response(boolean ready, float secondsleft, int lastmovenumber, String lastmove) {
		this.ready = ready;
		this.secondsleft = secondsleft;
		this.lastmovenumber = lastmovenumber;
		this.lastmove = lastmove;
	}
}
