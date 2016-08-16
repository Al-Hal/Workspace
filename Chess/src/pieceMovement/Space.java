package pieceMovement;

public class Space {
	
	private int letterSpace;
	private int number;
	private char piece;
	Board board;
	
	public Space(String space, Board board){
		this.board = board;
		getSpace(space);
	}
	
	public int getLetterSpace(){
		return letterSpace;
	}

	public int getNumber(){
		return number;
	}
	
	public char getPiece(){
		return piece;
	}
	
	public void setLetterSpace(int letterSpace){
		this.letterSpace = letterSpace;
	}

	public void setNumber(int number){
		this.number = number;
	}
	
	public void setPiece(char piece){
		this.piece = piece;
	}
	
	public void getSpace(String space){
		char letter = space.charAt(0);
		switch(letter){
		case 'a':
			setLetterSpace(0);
			break;
		case 'b':
			setLetterSpace(1);
			break;
		case 'c':
			setLetterSpace(2);
			break;
		case 'd':
			setLetterSpace(3);
			break;
		case 'e':
			setLetterSpace(4);
			break;
		case 'f':
			setLetterSpace(5);
			break;
		case 'g':
			setLetterSpace(6);
			break;
		case 'h':
			setLetterSpace(7);
			break;
		}
		setNumber(Integer.parseInt(space.substring(1)) - 1);
		setPiece(board.getPiece(number, letterSpace));
	}
}
