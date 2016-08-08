package fileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {

	public static void main(String[] args) {
		String fileName = args[0];
		readFile(fileName);
	}

	public static void readFile(String fileName){

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fileReader);
			while(reader.ready()){
				String s = reader.readLine();
				placePiece(s);
				moveSinglePiece(s);
				moveTwoPieces(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void moveTwoPieces(String s) {
		String pattern = "[a-h][1-8]\\s[a-h][1-8]\\s[a-h][1-8]\\s[a-h][1-8]";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		
		if(m.matches()){
			String kingSquare = Character.toString(s.charAt(0)).toUpperCase() + Character.toString(s.charAt(1));
			String newKingSquare = Character.toString(s.charAt(3)).toUpperCase() + Character.toString(s.charAt(4));
			String rookSquare = Character.toString(s.charAt(6)).toUpperCase() + Character.toString(s.charAt(7));
			String newRookSquare = Character.toString(s.charAt(9)).toUpperCase() + Character.toString(s.charAt(10));
			System.out.println(String.format("Move the king from %s to %s and move the rook from %s to %s",
					kingSquare, newKingSquare, rookSquare, newRookSquare));
		}
	}

	public static void moveSinglePiece(String s) {
		String pattern = "[a-h][1-8]\\s[a-h][1-8]\\**";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);

		if(m.matches()){
			String statement;
			String newSquare = Character.toString(s.charAt(3)).toUpperCase() + Character.toString(s.charAt(4));
			if(s.endsWith("*")){
				statement = String.format("Move the piece at %s%s to %s and capture piece at %s" ,
						Character.toString(s.charAt(0)).toUpperCase(), Character.toString(s.charAt(1)), newSquare, newSquare);
			}else{
				statement = String.format("Move the piece at %s%s to %s",
						Character.toString(s.charAt(0)).toUpperCase(), Character.toString(s.charAt(1)), newSquare);
			}
			System.out.println(statement);
		}
	}

	public static void placePiece(String s) {
		String pattern = "[KQBNRP][ld][a-h][1-8]";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);

		if(m.matches()){
			String piece = Character.toString(s.charAt(0));
			String color = Character.toString(s.charAt(1));
			switch(piece){
			case "K":
				piece = "king";
				break;
			case "Q":
				piece = "queen";
				break;
			case "B":
				piece = "bishop";
				break;
			case "N":
				piece = "knight";
				break;
			case "R":
				piece = "rook";
				break;
			case "P":
				piece = "pawn";
				break;
			}
			if(color.equals("l")){
				color = "white";
			}else if(color.equals("d")){
				color = "black";
			}
			String statement = String.format("Place the %s %s on %s%s" , color, piece, 
					Character.toString(s.charAt(2)).toUpperCase(), Character.toString(s.charAt(3)));
			System.out.println(statement);
		}
	}
}
