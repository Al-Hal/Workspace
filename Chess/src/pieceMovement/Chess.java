package pieceMovement;

public class Chess {

	public static void main(String[] args) {
		Board board = new Board();
		PieceMovement movement = new PieceMovement(board);
		FileRead read = new FileRead(board, movement);
		read.readFile("C:\\Users\\Baldielocks\\workspace\\Chess\\PieceMoves");
		
	}

}
