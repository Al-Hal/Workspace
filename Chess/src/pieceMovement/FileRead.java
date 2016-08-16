package pieceMovement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {

	static char token;
	Board board;
	PieceMovement movement;

	//	public static void main(String[] args) {
	//		String fileName = args[0];
	//		readFile("C:\\Users\\Baldielocks\\workspace\\Chess\\Chess Directives");

	//	}
	public FileRead(Board board, PieceMovement movement) {
		this.board = board;
		this.movement = movement;
	}
	public void readFile(String fileName){

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fileReader);

			while(reader.ready()){
				String s = reader.readLine();
				//				placePiece(s);
				movement.moveSinglePiece(s);
				movement.moveTwoPieces(s);
			}
		} catch (IOException e){
			e.printStackTrace();
		}

	}

	//	public static void placePiece(String s) {
	//		String pattern = "([KQBNRP])([ld])([a-h][1-8])";
	//		Pattern p = Pattern.compile(pattern);
	//		Matcher m = p.matcher(s);
	//
	//		if(m.matches()){
	//			token = m.group(1).charAt(0);
	//			String piece = m.group(1);
	//			String color = m.group(2);
	//			switch(piece){
	//			case "K":
	//				piece = "king";
	//				break;
	//			case "Q":
	//				piece = "queen";
	//				break;
	//			case "B":
	//				piece = "bishop";
	//				break;
	//			case "N":
	//				piece = "knight";
	//				break;
	//			case "R":
	//				piece = "rook";
	//				break;
	//			case "P":
	//				piece = "pawn";
	//				break;
	//			}
	//			if(color.equals("l")){
	//				color = "white";
	//				token+=32;
	//			}else if(color.equals("d")){
	//				color = "black";
	//			}
	//			String statement = String.format("Place the %s %s on %s" , color, piece, m.group(3));
	//			System.out.println(statement);
	//			board.getSpace(m.group(3), token);
	//		}
	//	}
}

