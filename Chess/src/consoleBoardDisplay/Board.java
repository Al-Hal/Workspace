package consoleBoardDisplay;

public class Board {

	private static char[][] board = new char[8][8];

	public Board() {
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				board[i][j] = '-';
			}
		}
	}

	public void printBoard() {
		for(int i = 0; i < 8; i++){
			System.out.print(String.format("\t%c", 'A'+i));
		}
		System.out.println("\n\t---------------------------------------------------------");
		for(int i = board.length - 1; i >= 0; i--){
			System.out.print(String.format("\n%d", i+1));
			for(int j = 0; j < board.length; j++){
				System.out.print("\t" + board[i][j]);
			}
			System.out.println();
		}

		System.out.println("\t---------------------------------------------------------");
	}

	public void changeBoard(int letter, int number, char token){
		board[letter][number] = token;
	}

}
