
public class Game 
{
    private Board board;
    private char whoseTurn;
    private UI myDisplay;
    private boolean pressed;
    private Location pressedLoc;

    public Game()
    {
        board = new Board();
        whoseTurn = Symbol.PLAYER_X;
        pressed= false;
        pressedLoc = null;      
    }

    public void setDisplay(UI display)
    {
        myDisplay = display;
    }

    public void playGame()
    {
        boolean playAgain = false;
        boolean winner = false;
        Result result;
        do          // start a new game
        {
            //added
            winner = false;
            do      // start a new move
            {
                result = processMove();
                if(result == Result.X_WON || result == Result.O_WON || result 
                == Result.TIE)
                    winner = true;
                else
                {
                    if(whoseTurn == Symbol.PLAYER_X)
                        whoseTurn = Symbol.PLAYER_O;
                    else
                        whoseTurn = Symbol.PLAYER_X;
                }                   
            }
            while(!winner);
            playAgain = myDisplay.displayWinner(result);
//            System.out.println("playAgain: " + playAgain);
            if(playAgain)
                resetGame();            
        }
        while(playAgain);
        myDisplay.endProgram();
    }

    public void pressed(Location loc)
    {
        //       if (loc != null)
        {    
            pressedLoc = loc;
//            System.out.println("game detected button " + loc);
            pressed = true;       // important to set this flag *AFTER* assigned
            // a value to 'pressedLoc', not before .... else
            // while(!pressed); ends before 'pressedLoc' gets a value
        }
        //       else
        //          pressed = false;
    }

    private void resetGame()
    {
//        System.out.println("reseting...");
        board = new Board();
        whoseTurn = Symbol.PLAYER_X;
        pressed = false;
        pressedLoc = null;
        // added:
        myDisplay.clearDisplay();
    }

    private Result processMove()
    {
        boolean validMove = false;
        Result result;
        do
        {
            pressed = false;
            while ( !pressed )
            {
                /*     ;   */       
                // INSTEAD OF EMPTY BODY, NEEDED TO CALL sleep()...
                // because of BlueJ? or Java 1.8?
                try {
                    Thread.sleep(100);
                }
                catch (Exception e)
                {}
            }

//            System.out.println("process move: " + whoseTurn + " " + pressedLoc);
            result = board.recordTurn(whoseTurn, pressedLoc);

            if( !(result == Result.INVALID_LOCATION || result == Result.LOCATION_NOT_EMPTY) )
            {
                validMove = true;
                myDisplay.updateDisplay(whoseTurn, pressedLoc);
            }   

        }
        while ( !validMove );

        return result;
    }
}