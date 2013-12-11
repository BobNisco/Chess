package main.java.com.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.Gson;

public class PlayChess {

	public Board board;
	public int color;
	public int gameId;
	public int teamNumber;
	public String teamSecret;
	public static int plyLookhead = 4;

	public PlayChess(int color) {
		this.board = new Board(color);
		this.color = color;
		this.gameId = -1;
		this.teamNumber = -1;
		this.teamSecret = "";
	}

	public PlayChess(int color, int gameId, int teamNumber, String teamSecret) {
		this.board = new Board(color);
		this.color = color;
		this.gameId = gameId;
		this.teamNumber = teamNumber;
		this.teamSecret = teamSecret;
	}

	public Response poll() {
		return handleRequest("http://www.bencarle.com/chess/poll/" + this.gameId + "/" +
				this.teamNumber + "/" + this.teamSecret + "/");
	}

	public Response move(String moveString) {
		// http://www.bencarle.com/chess/move/GAMEID/TEAMNUMBER/TEAMSECRET/MOVESTRING/
		return handleRequest("http://www.bencarle.com/chess/move/" + this.gameId + "/" + this.teamNumber + "/" + this.teamSecret + "/" + moveString +"/");
	}

	private Response handleRequest(String url) {
		Response r = new Response();
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				System.err.println(conn.getURL());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			System.out.println(conn.getURL());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output = "";
			String jsonString = "";
			while ((output = br.readLine()) != null) {
				jsonString += output;
			}
			conn.disconnect();

			Gson gson = new Gson();
			r = gson.fromJson(jsonString, Response.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	private static void playAgainstSelfOnServer(int gameId) {
		PlayChess whiteTeam = new PlayChess(Board.white, gameId, 1, "32c68cae");
		PlayChess blackTeam = new PlayChess(Board.black, gameId, 2, "1a77594c");
		while (true) {
			System.out.println("POLLING AND MOVING FOR WHITE");
			Response r = whiteTeam.poll();
			System.out.println(r);
			if (whiteTeam.board.gameIsOver()) {
				break;
			}
			if (r.ready) {
				if (r.lastmove != null && r.lastmove.length() > 0) {
					MoveHandler.handleMove(whiteTeam.board, r);
				}
				Node nextNode = MiniMax.performMiniMax(whiteTeam.board, whiteTeam.color, PlayChess.plyLookhead, r);
				whiteTeam.move(MoveHandler.convertMoveToServerNotation(whiteTeam.board, nextNode.m));
				whiteTeam.board.handleMove(nextNode.m);
			}
			System.out.println("POLLING AND MOVING FOR BLACK");
			r = blackTeam.poll();
			System.out.println(r);
			if (blackTeam.board.gameIsOver()) {
				break;
			}
			if (r.ready) {
				if (r.lastmove != null && r.lastmove.length() > 0) {
					MoveHandler.handleMove(blackTeam.board, r);
				}
				Node nextNode = MiniMax.performMiniMax(blackTeam.board, blackTeam.color, PlayChess.plyLookhead, r);
				System.out.println(nextNode);
				blackTeam.move(MoveHandler.convertMoveToServerNotation(blackTeam.board, nextNode.m));
				blackTeam.board.handleMove(nextNode.m);
			}
		}
	}



	private static void playAgainstOpponentOnServer(int gameId, int color, int teamNumber, String secret) {
		PlayChess ourTeam = new PlayChess(color, gameId, teamNumber, secret);
		while (true) {
			System.out.println("POLLING AND MOVING FOR " + color);
			Response r = ourTeam.poll();
			System.out.println(r);
			if (ourTeam.board.gameIsOver()) {
				break;
			}
			if (r.ready) {
				if (r.lastmove != null && r.lastmove.length() > 0) {
					MoveHandler.handleMove(ourTeam.board, r);
				}
				Node nextNode = MiniMax.performMiniMax(ourTeam.board, ourTeam.color, PlayChess.plyLookhead, r);
				ourTeam.move(MoveHandler.convertMoveToServerNotation(ourTeam.board, nextNode.m));
				ourTeam.board.handleMove(nextNode.m);
			}
		}
	}

	private static void playLocallyAgainstRandomOpponent() {
		try{
			Random randomGenerator = new Random();
			PlayChess whiteTeam = new PlayChess(Board.white);
			while (true) {
				System.out.println("MOVING FOR WHITE");
				if (whiteTeam.board.gameIsOver()) {
					System.out.println("Game over");
					break;
				}
				Node nextNode = MiniMax.performMiniMax(whiteTeam.board, whiteTeam.color, PlayChess.plyLookhead, new Response(900));
				whiteTeam.board.handleMove(nextNode.m);
				System.out.println(whiteTeam.board + "\n\n");

				System.out.println("MOVING FOR BLACK");
				if (whiteTeam.board.gameIsOver()) {
					System.out.println("Game over");
					break;
				}
				ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(whiteTeam.board, Board.black);
				int index = randomGenerator.nextInt(actions.size());
				whiteTeam.board.handleMove(actions.get(index));
				System.out.println(whiteTeam.board);
				System.out.println("----------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void playLocallyAgainstAsBlackRandomOpponent() {
		try{
			Random randomGenerator = new Random();
			PlayChess blackTeam = new PlayChess(Board.black);
			while (true) {
				System.out.println("MOVING FOR WHITE RANDOMLY");
				if (blackTeam.board.gameIsOver()) {
					System.out.println("Game over");
					break;
				}
				ArrayList<Move> actions = GenerateSuccessors.allPossibleSuccessors(blackTeam.board, Board.white);
				int index = randomGenerator.nextInt(actions.size());
				blackTeam.board.handleMove(actions.get(index));
				System.out.println(blackTeam.board);


				System.out.println("MOVING FOR BLACK WITH MINIMAX");
				if (blackTeam.board.gameIsOver()) {
					System.out.println("Game over");
					break;
				}
				Node nextNode = MiniMax.performMiniMax(blackTeam.board, blackTeam.color, PlayChess.plyLookhead, new Response(900));
				blackTeam.board.handleMove(nextNode.m);
				System.out.println(blackTeam.board + "\n\n");

				System.out.println("----------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void playLocallyAgainstSelf() {
		PlayChess whiteTeam = new PlayChess(Board.white);
		PlayChess blackTeam = new PlayChess(Board.black);
		while (true) {
			System.out.println("MOVING FOR WHITE");
			if (whiteTeam.board.gameIsOver()) {
				System.out.println("Game over");
				break;
			}
			Node nextNode = MiniMax.performMiniMax(whiteTeam.board, whiteTeam.color, PlayChess.plyLookhead, new Response(900));
			whiteTeam.board.handleMove(nextNode.m);
			blackTeam.board.handleMove(nextNode.m);
			System.out.println(whiteTeam.board + "\n\n");

			System.out.println("MOVING FOR BLACK");
			if (blackTeam.board.gameIsOver()) {
				System.out.println("Game over");
				break;
			}
			nextNode = MiniMax.performMiniMax(blackTeam.board, blackTeam.color, PlayChess.plyLookhead, new Response(900));
			whiteTeam.board.handleMove(nextNode.m);
			blackTeam.board.handleMove(nextNode.m);
			System.out.println(blackTeam.board);
			System.out.println("----------------------------------");
		}
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			playAgainstOpponentOnServer(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		} else {
			playLocallyAgainstSelf();
		}
	}
}
