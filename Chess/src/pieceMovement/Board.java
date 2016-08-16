package pieceMovement;


public class Board {

	private static char[][] board = new char[8][8];

	public Board() {
		placePieces();
	}
	
	public void printBoard(){
		System.out.println();
		for(int i = 0; i < 8; i++){
			System.out.print(String.format("\t%c", 'A'+i));
		}
		System.out.println("\n\t---------------------------------------------------------");
		for(int i = board.length - 1; i >= 0; i--){
			System.out.print(String.format("\n%d", i+1));
			for(int j = 0; j < board.length; j++){
				System.out.print("\t" + board[j][i]);
			}
			System.out.println();
		}

		System.out.println("\t---------------------------------------------------------\n");
	}

	public char getPiece(int letter, int number){
		return board[number][letter];
	}
	
	public void changeBoard(int letter, int number, char token){
		board[number][letter] = token;
	}

	private void placePieces(){
		changeBoard(0, 0, 'r');
		changeBoard(0, 1, 'n');
		changeBoard(0, 2, 'b');
		changeBoard(0, 3, 'q');
		changeBoard(0, 4, 'k');
		changeBoard(0, 5, 'b');
		changeBoard(0, 6, 'n');
		changeBoard(0, 7, 'r');
		
		changeBoard(7, 0, 'R');
		changeBoard(7, 1, 'N');
		changeBoard(7, 2, 'B');
		changeBoard(7, 3, 'Q');
		changeBoard(7, 4, 'K');
		changeBoard(7, 5, 'B');
		changeBoard(7, 6, 'N');
		changeBoard(7, 7, 'R');
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == 0){
					board[i][j] = '-';
				}
			}
		}
	}
	
}
