package pieceMovement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PieceMovement {

	Board board;
	private boolean valid;

	public PieceMovement(Board board) {
		this.board = board;
	}

	public boolean checkValidity(Space currentSpace, Space newSpace){
		switch(currentSpace.getPiece()){
		case 'R':
		case 'r':
			valid = rookMovement(currentSpace, newSpace);
			break;
		case 'B':
		case 'b':
			valid = bishopMovement(currentSpace, newSpace);
			break;
		case 'N':
		case 'n':
			valid = knightMovement(currentSpace, newSpace);
			break;
		case 'K':
		case 'k':
			valid = kingMovement(currentSpace, newSpace);
			break;
		case 'Q':
		case 'q':
			valid = queenMovement(currentSpace, newSpace);
			break;
		}
		return valid;
	}
	public boolean rookMovement(Space currentSpace, Space newSpace){
		boolean valid = false;
		if((newSpace.getNumber() == currentSpace.getNumber() && newSpace.getLetterSpace() != currentSpace.getLetterSpace()) || 
				(newSpace.getLetterSpace() == currentSpace.getLetterSpace() && newSpace.getNumber() != currentSpace.getNumber())){
			valid = true;
		}
		return valid;
	}
	public boolean bishopMovement(Space currentSpace, Space newSpace){
		boolean valid = false;
		int difference = Math.abs(newSpace.getNumber() - currentSpace.getNumber());
		if(Math.abs(newSpace.getLetterSpace() - currentSpace.getLetterSpace()) == difference){
			valid = true;
		}
		return valid;
	}
	public boolean knightMovement(Space currentSpace, Space newSpace){
		boolean valid = false;
		if((Math.abs(newSpace.getNumber() - currentSpace.getNumber()) == 2 && Math.abs(newSpace.getLetterSpace() - currentSpace.getLetterSpace()) == 1) || 
				(Math.abs(newSpace.getNumber() - currentSpace.getNumber()) == 1 && Math.abs(newSpace.getLetterSpace() - currentSpace.getLetterSpace()) == 2)){
			valid = true;
		}
		return valid;
	}
	public boolean kingMovement(Space currentSpace, Space newSpace){
		boolean valid = false;
		if((newSpace.getNumber() == currentSpace.getNumber() && Math.abs(newSpace.getLetterSpace() - currentSpace.getLetterSpace()) == 1) || 
				(Math.abs(newSpace.getNumber() - currentSpace.getNumber()) == 1 && newSpace.getLetterSpace() == currentSpace.getLetterSpace()) || 
				(Math.abs(newSpace.getNumber() - currentSpace.getNumber()) == 1 && Math.abs(newSpace.getLetterSpace() - currentSpace.getLetterSpace()) == 1)){
			valid = true;
		}
		return valid;
	}
	public boolean queenMovement(Space currentSpace, Space newSpace){
		boolean valid;
		valid = rookMovement(currentSpace, newSpace);
		if(!valid){
			valid = bishopMovement(currentSpace, newSpace);
		}
		return valid;
	}
	public void moveTwoPieces(String s) {
		String pattern = "([a-h][1-8])\\s([a-h][1-8])\\s([a-h][1-8])\\s([a-h][1-8])";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);

		if(m.matches()){
			System.out.println(String.format("Move the king from %s to %s and move the rook from %s to %s",
					m.group(1).toUpperCase(), m.group(2).toUpperCase(), m.group(3).toUpperCase(), m.group(4).toUpperCase()));

			Space kingCurrentSpace = new Space(m.group(1), board);
			Space kingNewSpace = new Space(m.group(2), board);
			boolean check1 = checkValidity(kingCurrentSpace, kingNewSpace);

			Space rookCurrentSpace = new Space(m.group(3), board);
			Space rookNewSpace = new Space(m.group(4), board);
			boolean check2 = checkValidity(rookCurrentSpace, rookNewSpace);
			
			if(check1 && check2){
				board.changeBoard( kingCurrentSpace.getNumber(), kingCurrentSpace.getLetterSpace(),'-');
				board.changeBoard(kingNewSpace.getNumber(), kingNewSpace.getLetterSpace(), kingCurrentSpace.getPiece());
				board.changeBoard(rookCurrentSpace.getNumber(), rookCurrentSpace.getLetterSpace(), '-');
				board.changeBoard(rookNewSpace.getNumber(), rookNewSpace.getLetterSpace(), rookCurrentSpace.getPiece());
				board.printBoard();
			}else{
				System.out.println("INVALID MOVE");
			}
		}
	}

	public void moveSinglePiece(String s) {
		String pattern = "([a-h][1-8])\\s([a-h][1-8])\\**";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);

		if(m.matches()){
			String statement;
			if(s.endsWith("*")){
				statement = String.format("Move the piece at %s to %s and capture piece at %s",
						m.group(1).toUpperCase(), m.group(2).toUpperCase(), m.group(2).toUpperCase());
			}else{
				statement = String.format("Move the piece at %s to %s",
						m.group(1).toUpperCase(), m.group(2).toUpperCase());
			}
			System.out.println(statement);

			Space currentSpace = new Space(m.group(1), board);
			Space newSpace = new Space(m.group(2), board);
			boolean check = checkValidity(currentSpace, newSpace);
			
			if(check){
				board.changeBoard(currentSpace.getNumber(), currentSpace.getLetterSpace(), '-');
				System.out.println(currentSpace.getPiece());
				board.changeBoard(newSpace.getNumber(), newSpace.getLetterSpace(), currentSpace.getPiece());
				board.printBoard();
			}else{
				System.out.println("INVALID MOVE");
			}
		}
	}
}