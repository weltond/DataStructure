## [746. Design Tic-Tac-Toe](https://www.lintcode.com/problem/design-tic-tac-toe/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Design Tic-Tac-Toe game.

- board has fixed size of 3
- X always take the first move
- If a place already got taken, and one player want to take that place,
- an AlreadyTakenException will be thrown
- If one player wins, and somebody try to make another move, a GameEndException will be thrown.
- If all the places got taken,you should print "it's a draw"

Example 1:

```
Input:
move(0, 0) // X turn
move(1, 0) // O trun 
move(1, 1) // X turn
move(2, 0) // O turn
move(2, 2) // X turn and win
move(0, 0)  //throw GameEndException
move(0, 0) // X turn
move(0, 0) // throw AlreadyTakenException
move(1, 0) // O turn
move(1, 1) // X turn
move(2, 0) // o turn
move(2, 2) // X turn and win
Output:
x player wins!
x player wins!
```

## Answer
### [Method 2](https://evelynn.gitbooks.io/google-interview/design_tic-tac-toe.html)
### Method 1 - OOD

```java
public class TicTacToe {
    private char[][] board;
	private char currentPlayerMark;
	private boolean gameEnd;

	public TicTacToe() {
		board = new char[3][3];
		initialize();
	}

	public char getCurrentPlayer() {
		return currentPlayerMark;
	}

	public void initialize() {
		gameEnd = false;
		currentPlayerMark = 'x';

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		gameEnd = true;
		return true;
	}

	public void changePlayer() {
		if (currentPlayerMark == 'x')
			currentPlayerMark = 'o';
		else
			currentPlayerMark = 'x';

	}

	// true means this move wins the game, false means otherwise
	public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {

		if (gameEnd) {
			throw new GameEndException();
		}

		if (board[row][col] != '-') {
			throw new AlreadyTakenException();
		}

		board[row][col] = currentPlayerMark;

		boolean win;

		//check row
		win = true;
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] != currentPlayerMark) {
				win = false;
				break;
			}
		}

		if (win) {
			gameEnd = true;
			return win;
		}

		//check column
		win = true;
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] != currentPlayerMark) {
				win = false;
				break;
			}
		}

		if (win) {
			gameEnd = true;
			return win;
		}

		//check back diagonal
		win = true;
		for (int i = 0; i < board.length; i++) {
			if (board[i][i] != currentPlayerMark) {
				win = false;
				break;
			}
		}

		if (win) {
			gameEnd = true;
			return win;
		}

		//check forward diagonal
		win = true;
		for (int i = 0; i < board.length; i++) {
			if (board[i][board.length - i - 1] != currentPlayerMark) {
				win = false;
				break;
			}
		}

		if (win) {
			gameEnd = true;
            return win;
		}
		changePlayer();
		return win;
	}
}


class GameEndException extends Exception{
	public GameEndException()
	{
		super("Game has been ended, cannot make any more moves");
	}
}

class AlreadyTakenException extends Exception {
	public AlreadyTakenException()
	{
		super("This place has been taken");
	}
}
```
