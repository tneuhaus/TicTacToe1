public class Board
{
	private char[][] board;
	private int numMoves;
	
	public Board()
	{
		numMoves = 0;
		board = new char[3][3];
		
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
				board[row][col] = Symbol.EMPTY1;	// space: ' '
		}
	}
	
	// testing method using by testing class
	public void printBoard()
	{
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c < 3; c++)
			{
				System.out.print("[" + board[r][c] + "] ");
			}
			System.out.println();
		}
	}
	
	public Result recordTurn(char player, Location loc)
	{
		if(!isValid(loc))
			return Result.INVALID_LOCATION;
			
		else if(!isEmpty(loc))
			return Result.LOCATION_NOT_EMPTY;
		
		String j = "";
		j += player;
		j = j.toUpperCase();
		player = j.charAt(0);
			
		board[loc.getRow()][loc.getCol()] = player;
		
		numMoves++;
		return checkWinOrTie();

	}
	
	public Result checkWinOrTie()
	{
		Result result;
		
		for(int row = 0; row < 3; row++)
		{
			result = testRow(row);
//			System.out.println("1st: " + result);
			if(result == Result.X_WON || result == Result.O_WON)
				return result;
		}
		
		for(int col = 0; col < 3; col++)
		{
			result = testCol(col);
//			System.out.println("2nd: " + result);
			if(result == Result.X_WON || result == Result.O_WON)
				return result;
		}
		
		result = testDiag1();
//		System.out.println("3rd: " + result);
		if(result == Result.X_WON || result == Result.O_WON)
			return result;
			
		result = testDiag2();
//		System.out.println("4th: " + result);
		if(result == Result.X_WON || result == Result.O_WON)
			return result;	
		
		if(numMoves == 9)
			return Result.TIE;
			
		return Result.GAME_NOT_OVER;		
	}
	
	public boolean isValid(Location loc) 
	{ 
		if (loc == null) {
			System.out.println("null loc");
			return false;
		}
		if((loc.getRow() >= 0 && loc.getRow() <= 2) 
		&& (loc.getCol() >= 0 && loc.getCol() <= 2))
			return true;
		
		else
			return false;
	}
	
	public boolean isEmpty(Location loc) 
	{
		if(board[loc.getRow()][loc.getCol()] == Symbol.EMPTY1)
			return true;
			
		else
			return false;
	}
	
	// check if first char in the section is X or O -> if so, then check remaining ones are the same
	public Result testRow(int row)		// tests if every char in 'row' is all 'X' or all 'O'
	{
		char first = board[row][0];
		
		if(first == Symbol.EMPTY1)
			return Result.GAME_NOT_OVER;
		
		for (int count = 1; count < 3; count++)
		{
			if (board[row][count] != first)
			return Result.GAME_NOT_OVER;
		}
		
		if (first == Symbol.PLAYER_X)
			return Result.X_WON;
			
		else
			return Result.O_WON;
		
	}
	
	public Result testCol(int col)		// tests if every char in 'col' …
	{
		char first = board[0][col];
		
		if(first == Symbol.EMPTY1)
			return Result.GAME_NOT_OVER;
		
		for (int count = 1; count < 3; count++)
		{
			if (board[count][col] != first)
			return Result.GAME_NOT_OVER;
		}
		
		if (first == Symbol.PLAYER_X)
			return Result.X_WON;
			
		else
			return Result.O_WON;
		
	}
	
	public Result testDiag1()		// tests upper-left to lower-right diag
	{
		char first = board[0][0];
		
		if(first == Symbol.EMPTY1)
			return Result.GAME_NOT_OVER;
		
		for (int count = 0; count < 3; count++)
		{
			if (board[count][count] != first)
			return Result.GAME_NOT_OVER;
		}
		
		if (first == Symbol.PLAYER_X)
			return Result.X_WON;
			
		else
			return Result.O_WON;
	}
	public Result testDiag2()		// tests other diag
	{
		char first = board[2][0];
		
		if(first == Symbol.EMPTY1)
			return Result.GAME_NOT_OVER;
		
		int inc = 0;
		for (int count = 2; count >= 0; count--)
		{
			
			if (board[count][inc] != first)
				return Result.GAME_NOT_OVER;
			inc++;
		}
		
		if (first == Symbol.PLAYER_X)
			return Result.X_WON;
			
		else
			return Result.O_WON;
	}	
	
	public char playerAt (Location loc)		// returns X, O or space
	{
		char target = board[loc.getRow()][loc.getCol()];
		return target;
	}

	
}